/*******************************************************************************
 * All sources under the hanto.otnah package were developed by
 * Ted Meyer and Nilesh Patel for the term project in CS4233
 * at Worcester Polytechnic Institute. Since WPI holds all other forms
 * of ownership to this software, we have decided to not make this
 * software available under any license. Evaluation or compilation rights
 * are therefore granted only to course staff.
 *******************************************************************************/

package hanto.otnah.alpha;

import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.HantoPrematureResignationException;
import hanto.common.MoveResult;
import hanto.otnah.common.GameState;
import hanto.otnah.common.HantoPlayer;
import hanto.otnah.common.Position;
import hanto.otnah.common.HexPosition;
import hanto.otnah.common.pieces.moves.PieceMoveValidatorFactory;
import static hanto.common.HantoPlayerColor.BLUE;
import static hanto.common.HantoPlayerColor.RED;
import static hanto.otnah.common.HantoPlayerFactory.makeAlphaPlayers;

/**
 * 
 * @author otnah
 *
 */
public class AlphaHantoGame extends GameState
{
	private HantoPlayer current = makeAlphaPlayers(BLUE, RED);
	
	@Override
	public void skipTo(HantoPlayerColor player)
	{
		current = current.skipTo(player);
	}

	@Override
	public HantoPlayer getCurrentPlayer()
	{
		return current;
	}

	@Override
	public boolean isMovePossible(Position from, Position to, HantoPieceType type, HantoPlayerColor color)
	{
		final int distance = Position.asPosition(from).getDistanceTo(Position.asPosition(to));
		boolean result = false;
		if (distance == 0)
		{
			final int whichDistance = new HexPosition(0, 0).getDistanceTo(Position.asPosition(to));
			if (current.getColor() == HantoPlayerColor.BLUE)
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
	public MoveResult tryResignation() throws HantoException
	{
		throw new HantoPrematureResignationException();
	}
}
