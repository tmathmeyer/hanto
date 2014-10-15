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
import static hanto.otnah.common.HantoPlayerFactory.makeBetaPlayers;
import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.otnah.common.GameState;
import hanto.otnah.common.HantoPlayer;
import hanto.otnah.common.Position;
import hanto.otnah.common.pieces.moves.PieceMoveValidatorFactory;

/**
 * 
 * @author otnah
 *
 */
public class BetaHantoGame extends GameState
{
	/**
	 * @param player the color of the first player
	 */
	public BetaHantoGame(HantoPlayerColor player)
	{
		skipTo(player);
	}

	@Override
	public HantoPlayer makePlayers()
	{
		return makeBetaPlayers(BLUE, RED);
	}

	@Override
	public boolean isMovePossible(Position from, Position to, HantoPieceType type, HantoPlayerColor color) throws HantoException {
		return getValidatorFactory().getMoveValidator(type).isValidMove(to, from, this);
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

	@Override
	public boolean isOverMaxAllottedMoves(int currentMoveCount) {
		return currentMoveCount >= 12;
	}

	@Override
	public PieceMoveValidatorFactory getValidatorFactory()
	{
		return new PieceMoveValidatorFactory(new BetaHantoRules());
	}

	@Override
	public MoveResult tryResignation() throws HantoException
	{
		throw new HantoException("you can't do that in a beta game");
	}
}
