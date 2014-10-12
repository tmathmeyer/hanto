/*******************************************************************************
 * All sources under the hanto.otnah package were developed by
 * Ted Meyer and Nilesh Patel for the term project in CS4233
 * at Worcester Polytechnic Institute. Since WPI holds all other forms
 * of ownership to this software, we have decided to not make this
 * software available under any license. Evaluation or compilation rights
 * are therefore granted only to course staff.
 *******************************************************************************/


package hanto.otnah.beta;

import static hanto.common.HantoPlayerColor.BLUE;
import static hanto.common.HantoPlayerColor.RED;
import static hanto.otnah.common.LinkedHantoPlayerFactory.makeBetaPlayers;

import java.util.Set;

import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.otnah.common.GameState;
import hanto.otnah.common.HantoPlayer;
import hanto.otnah.common.HantoTile;
import hanto.otnah.common.LinkedHantoPlayer;
import hanto.otnah.common.Position;
import hanto.otnah.common.HexPosition;
import hanto.otnah.common.pieces.moves.PieceMoveValidatorFactory;

/**
 * 
 * @author otnah
 *
 */
public class BetaHantoGame extends GameState
{
	private LinkedHantoPlayer current = makeBetaPlayers(RED, BLUE);

	public BetaHantoGame(HantoPlayerColor player) {
		skipTo(player);
	}

	@Override
	public void skipTo(HantoPlayerColor player)
	{
		current = current.skipTo(player);
	}

	@Override
	public HantoPlayer<LinkedHantoPlayer> getCurrentPlayer() {
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

	@Override
	public boolean isOverMaxAllottedMoves(int currentMoveCount) {
		return currentMoveCount >= 12;
	}

	@Override
	public PieceMoveValidatorFactory getValidatorFactory() throws HantoException
	{
		throw new HantoException("you shouldn't be using a validator!!!");
	}

	@Override
	public MoveResult tryResignation() {
		// TODO Auto-generated method stub
		return null;
	}
}
