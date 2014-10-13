/*******************************************************************************
 * All sources under the hanto.otnah package were developed by
 * Ted Meyer and Nilesh Patel for the term project in CS4233
 * at Worcester Polytechnic Institute. Since WPI holds all other forms
 * of ownership to this software, we have decided to not make this
 * software available under any license. Evaluation or compilation rights
 * are therefore granted only to course staff.
 *******************************************************************************/

package hanto.otnah.epsilon;

import static hanto.common.HantoPlayerColor.BLUE;
import static hanto.common.HantoPlayerColor.RED;
import static hanto.otnah.common.HantoPlayerFactory.makeEpsilonPlayers;
import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.HantoPrematureResignationException;
import hanto.common.MoveResult;
import hanto.otnah.common.GameState;
import hanto.otnah.common.HantoPlayer;
import hanto.otnah.common.Position;
import hanto.otnah.common.moves.MoveEnumerator;
import hanto.otnah.common.pieces.moves.PieceMoveValidatorFactory;

/**
 * 
 * @author otnah
 *
 */
public class EpsilonHantoGame extends GameState
{
	/**
	 * default epsilon game
	 * @param player the color of the first player
	 */
	public EpsilonHantoGame(HantoPlayerColor player) {
		skipTo(player);
	}

	@Override
	public HantoPlayer makePlayers()
	{
		return makeEpsilonPlayers(BLUE, RED);
	}
	
	@Override
	public MoveResult tryResignation() throws HantoException
	{
		if (new MoveEnumerator().getAllCurrentMoves(this).size() != 0)
		{
			throw new HantoPrematureResignationException();
		}
		
		return getCurrentPlayer().getLosingState();
	}

	@Override
	public boolean isMovePossible(Position from, Position to,
			HantoPieceType type, HantoPlayerColor color) {
		return getValidatorFactory().getMoveValidator(type).isValidMove(to, from, this);
	}

	@Override
	public boolean isOverMaxAllottedMoves(int currentMoveCount) {
		return false;
	}

	@Override
	public PieceMoveValidatorFactory getValidatorFactory()
	{
		return new PieceMoveValidatorFactory();
	}
}
