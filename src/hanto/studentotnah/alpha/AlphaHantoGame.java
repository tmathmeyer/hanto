/*******************************************************************************
 * All sources under the hanto.otnah package were developed by
 * Ted Meyer and Nilesh Patel for the term project in CS4233
 * at Worcester Polytechnic Institute. Since WPI holds all other forms
 * of ownership to this software, we have decided to not make this
 * software available under any license. Evaluation or compilation rights
 * are therefore granted only to course staff.
 *******************************************************************************/

package hanto.studentotnah.alpha;

import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.HantoPrematureResignationException;
import hanto.common.MoveResult;
import hanto.studentotnah.common.GameState;
import hanto.studentotnah.common.HantoPlayer;
import hanto.studentotnah.common.HexPosition;
import hanto.studentotnah.common.Position;
import hanto.studentotnah.common.pieces.moves.PieceMoveValidatorFactory;
import static hanto.common.HantoPlayerColor.BLUE;
import static hanto.common.HantoPlayerColor.RED;
import static hanto.studentotnah.common.HantoPlayerFactory.makeAlphaPlayers;

/**
 * 
 * @author otnah
 *
 */
public class AlphaHantoGame extends GameState
{	
	@Override
	public HantoPlayer makePlayers()
	{
		return makeAlphaPlayers(BLUE, RED);
	}

	@Override
	public boolean isMovePossible(Position from, Position to, HantoPieceType type, HantoPlayerColor color)
	{
		final int distance = Position.asPosition(from).getDistanceTo(Position.asPosition(to));
		boolean result = false;
		if (distance == 0)
		{
			final int whichDistance = new HexPosition(0, 0).getDistanceTo(Position.asPosition(to));
			if (getCurrentPlayer().getColor() == HantoPlayerColor.BLUE)
			{ // color is blue
				result = (whichDistance == 0);
			}
			else
			{ // color is red
				result = (whichDistance == 1);
			}
		}
		return result;
	}

	@Override
	public boolean isOverMaxAllottedMoves(int currentMoveCount) {
		return currentMoveCount >= 2;
	}

	@Override
	public PieceMoveValidatorFactory getValidatorFactory()
			throws HantoException {
		throw new HantoException("you shouldn't be using validators!!!");
	}

	@Override
	public MoveResult tryResignation() throws HantoPrematureResignationException
	{
		throw new HantoPrematureResignationException();
	}
}
