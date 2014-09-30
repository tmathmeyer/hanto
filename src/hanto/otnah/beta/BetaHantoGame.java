/*******************************************************************************
 * All sources under the hanto.otnah package were developed by
 * Ted Meyer and Nilesh Patel for the term project in CS4233
 * at Worcester Polytechnic Institute. Since WPI holds all other forms
 * of ownership to this software, we have decided to not make this
 * software available under any license. Evaluation or compilation rights
 * are therefore granted only to course staff.
 *******************************************************************************/


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

/**
 * 
 * @author otnah
 *
 */
public class BetaHantoGame extends GameState
{
	private BetaPlayer current;
	private final BetaPlayer red = new BetaPlayer(HantoPlayerColor.RED);
	private final BetaPlayer blue = new BetaPlayer(HantoPlayerColor.BLUE);

	/**
	 * default constructor
	 * 
	 * @param firstPlayer the color pf the player that goes first
	 */
	public BetaHantoGame(HantoPlayerColor firstPlayer)
	{
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
		}
	}

	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from,
			HantoCoordinate to) throws HantoException {
		if(isMovePossible(Position.asPosition(from), Position.asPosition(to), pieceType, null))
		{
			//remove piece from inventory
			HantoTile played = getCurrentPlayer().play(pieceType);
			
			//put piece on board
			setPieceAt(played, to);
			
			//increment player count
			current.increaseMoveCount();
			
			if(pieceType == HantoPieceType.BUTTERFLY)
			{
				current.setButterflyPosition(Position.asPosition(to));
			}
			
			//switch player
			current = current.getNextPlayer();
			return gameState();
		}
		
		throw new HantoException("invalid move!");
	}

	@Override
	public HantoPlayer<BetaPlayer> getCurrentPlayer() {
		return current;
	}

	@Override
	public boolean isMovePossible(Position from, Position to, HantoPieceType type, HantoPlayerColor color) {
		Position toPos = Position.asPosition(to);
		boolean movePossible = true;
		if (!hasPieceInInventory(type))
		{
			return false;
		}
		
		if (forcedToPlayButterfly())
		{
			movePossible = (HantoPieceType.BUTTERFLY == type);
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
		Set<Position> occupied = filledLocations();
		
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
		return getPieceAt(check) == null;
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
		MoveResult returnValue = MoveResult.OK;
		if(isSurrounded(red.getButterflyPosition()))
		{
			returnValue = MoveResult.BLUE_WINS;
		}
		if(isSurrounded(blue.getButterflyPosition()))
		{
			if(returnValue == MoveResult.BLUE_WINS)
			{
				returnValue = MoveResult.DRAW;
			}
			else
			{
				returnValue = MoveResult.RED_WINS;
			}
		}
		if(red.getMovesPlayed() + blue.getMovesPlayed() == 12)
		{
			returnValue = MoveResult.DRAW;
		}
		return returnValue;
	}
	
	private boolean isSurrounded(Position toCheck)
	{
		Collection<HantoCoordinate> positions = toCheck.adjacentCoordinates();
		if(positions.size() < 6)
		{
			return false;
		}
		for(HantoCoordinate c : positions)
		{
			if(getPieceAt(c) == null)
			{
				return false;
			}
		}
		return true;
	}
	
}
