package hanto.otnah.beta;

import java.util.Set;

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
	private final HantoPlayer red = new RedPlayer();
	private final HantoPlayer blue = new BluePlayer();
	
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
			
			//switch player
			current = current.getNextPlayer();
			
			return MoveResult.OK;
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

		if (!hasPieceInInventory(type))
		{
			return false;
		}
		
		if (forcedToPlayButterfly())
		{
			return HantoPieceType.BUTTERFLY == type;
		}
		
		return isFirstMove(toPos) || (piecePlaceContinuityCheck(toPos) && isLocationUnoccupied(toPos));
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
}
