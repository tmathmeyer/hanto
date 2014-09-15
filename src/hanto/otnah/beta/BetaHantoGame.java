package hanto.otnah.beta;

import java.util.Set;
import java.util.Collection;
import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.otnah.common.GameState;
import hanto.otnah.common.HantoPlayer;
import hanto.otnah.common.HantoTile;
import hanto.otnah.common.Position;
import hanto.otnah.common.HexPosition;

public class BetaHantoGame extends GameState
{
	private HantoPlayer current;
	private final HantoPlayer red = new BetaPlayer(HantoPlayerColor.RED);
	private final HantoPlayer blue = new BetaPlayer(HantoPlayerColor.BLUE);

	public BetaHantoGame(HantoPlayerColor firstPlayer) throws HantoException {
		red.setNextPlayer(blue);
		blue.setNextPlayer(red);
		switch (firstPlayer)
		{
			case BLUE:
				current = blue;
				break;
			case RED:
				current = red;
				break;
			default:
				throw new HantoException("invalid starting user");	
		}
	}

	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from,
			HantoCoordinate to) throws HantoException {
		if(isMovePossible(from, to, pieceType))
		{
			//remove piece from inventory
			HantoTile played = getCurrentPlayer().play(pieceType);
			
			//put piece on board
			this.setPieceAt(played, to);
			
			//increment player count
			current.increaseMoveCount();
			
			if(pieceType == HantoPieceType.BUTTERFLY)
			{
				((BetaPlayer)current).setButterflyPosition(new HexPosition(to));
			}
			//switch player
			current = current.getNextPlayer();
			return gameState();
		}
		
		throw new HantoException("invalid move!");
	}

	@Override
	public HantoPlayer getCurrentPlayer() {
		return current;
	}

	@Override
	public boolean isMovePossible(HantoCoordinate from, HantoCoordinate to, HantoPieceType type) {
		Position toPos = Position.asPosition(to);
		boolean movePossible = true;
		if (!hasPieceInInventory(type))
		{
			return false;
		}
		
		if (forcedToPlayButterfly())
		{
			movePossible = HantoPieceType.BUTTERFLY == type;
		}
		
		return movePossible && (isFirstMove(toPos) || 
				(piecePlaceContinuityCheck(toPos) && isLocationUnoccupied(toPos)));
	}

	/**
	 * 
	 * @param firstPlayer the color of the first player to go
	 * @return the BetaGameState with firstPlayer going first
	 * @throws HantoException if there is a problem creating the game
	 */
	public static BetaHantoGame createBetaGameState(HantoPlayerColor firstPlayer) throws HantoException
	{
		if (firstPlayer == null)
		{
			throw new HantoException("invalid starting user color");
		}
		return new BetaHantoGame(firstPlayer);
	}
	
	
	private boolean piecePlaceContinuityCheck(Position check)
	{
		Set<Position> occupied = this.filledLocations();
		
		for(Position p : occupied)
		{
			if (p.isAdjacentTo(check))
			{
				return true;
			}
		}
		
		return false;
	}
	
	private boolean isFirstMove(Position check)
	{
		return filledLocations().size() == 0
				&& check.getDistanceTo(new HexPosition(0, 0)) == 0;
	}
	
	private boolean isLocationUnoccupied(Position check)
	{
		return this.getPieceAt(check) == null;
	}
	
	private boolean hasPieceInInventory(HantoPieceType hpt)
	{
		for(HantoTile ht : getCurrentPlayer().getInventory())
		{
			if (ht.getType().equals(hpt))
			{
				return true;
			}
		}
		
		return false;
	}
	
	private boolean forcedToPlayButterfly()
	{
		return getCurrentPlayer().getMovesPlayed() >= 3
				&& hasPieceInInventory(HantoPieceType.BUTTERFLY);
	}
	
	private MoveResult gameState()
	{
		if(isSurrounded(((BetaPlayer)red).getButterflyPosition()))
		{
			return MoveResult.BLUE_WINS;
		}
		if(isSurrounded(((BetaPlayer)blue).getButterflyPosition()))
		{
			return MoveResult.RED_WINS;
		}
		if(red.getMovesPlayed() + blue.getMovesPlayed() == 12)
		{
			return MoveResult.DRAW;
		}
		return MoveResult.OK;
	}
	
	private boolean isSurrounded(Position toCheck)
	{
		Collection<HantoCoordinate> positions = toCheck.adjacentPositions();
		if(positions.size() < 6)
			return false;
		for(HantoCoordinate c : positions)
		{
			if(this.getPieceAt(c) == null)
				return false;
		}
		return true;
	}
	
}
