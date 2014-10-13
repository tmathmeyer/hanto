/*******************************************************************************
 * All sources under the hanto.otnah package were developed by
 * Ted Meyer and Nilesh Patel for the term project in CS4233
 * at Worcester Polytechnic Institute. Since WPI holds all other forms
 * of ownership to this software, we have decided to not make this
 * software available under any license. Evaluation or compilation rights
 * are therefore granted only to course staff.
 *******************************************************************************/


package hanto.otnah.delta;

import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.otnah.common.GameState;
import hanto.otnah.common.HantoPlayer;
import hanto.otnah.common.Position;
import hanto.otnah.common.pieces.moves.PieceMoveValidatorFactory;
import static hanto.common.HantoPlayerColor.RED;
import static hanto.common.HantoPlayerColor.BLUE;
import static hanto.otnah.common.HantoPlayerFactory.makeDeltaPlayers;


/**
 * 
 * @author otnah
 *
 */
public class DeltaHantoGame extends GameState
{
	/**
	 * a default delta hanto game
	 * @param player the color of the first player
	 */
	public DeltaHantoGame(HantoPlayerColor player) {
		skipTo(player);
	}

	@Override
	public HantoPlayer makePlayers()
	{
		return makeDeltaPlayers(BLUE, RED);
	}

	@Override
	public boolean isMovePossible(Position from, Position to, HantoPieceType type, HantoPlayerColor color)
	{
		return getValidatorFactory().getMoveValidator(type).isValidMove(to, from, this);
	}

	@Override
	public boolean isOverMaxAllottedMoves(int currentMoveCount)
	{
		return false;
	}

	@Override
	public PieceMoveValidatorFactory getValidatorFactory()
	{
		return new PieceMoveValidatorFactory();
	}

	@Override
	public MoveResult tryResignation() {
		return getCurrentPlayer().getNextPlayer().getWinningState();
	}
}
