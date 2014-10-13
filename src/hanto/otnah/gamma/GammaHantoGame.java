/*******************************************************************************
 * All sources under the hanto.otnah package were developed by
 * Ted Meyer and Nilesh Patel for the term project in CS4233
 * at Worcester Polytechnic Institute. Since WPI holds all other forms
 * of ownership to this software, we have decided to not make this
 * software available under any license. Evaluation or compilation rights
 * are therefore granted only to course staff.
 *******************************************************************************/


package hanto.otnah.gamma;

import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.otnah.common.GameState;
import hanto.otnah.common.HantoPlayer;
import hanto.otnah.common.Position;
import hanto.otnah.common.pieces.moves.PieceMoveValidatorFactory;
import static hanto.common.HantoPlayerColor.RED;
import static hanto.common.HantoPlayerColor.BLUE;
import static hanto.otnah.common.HantoPlayerFactory.makeGammaPlayers;


/**
 * 
 * @author otnah
 *
 */
public class GammaHantoGame extends GameState
{
	private final GammaRuleSet pieceRules = new GammaRuleSet();
	
	/**
	 * default gamma game
	 * @param player the color of the first player
	 */
	public GammaHantoGame(HantoPlayerColor player) {
		skipTo(player);
	}
	
	@Override
	public HantoPlayer makePlayers()
	{
		return makeGammaPlayers(BLUE, RED);
	}

	@Override
	public boolean isMovePossible(Position from, Position to, HantoPieceType type, HantoPlayerColor color)
	{
		return getValidatorFactory().getMoveValidator(type).isValidMove(to, from, this);
	}

	@Override
	public boolean isOverMaxAllottedMoves(int currentMoveCount)
	{
		return currentMoveCount >= 40;
	}

	@Override
	public PieceMoveValidatorFactory getValidatorFactory()
	{
		return new PieceMoveValidatorFactory(pieceRules);
	}

	@Override
	public MoveResult tryResignation() {
		return getCurrentPlayer().getLosingState();
	}
}
