/*******************************************************************************
 * All sources under the hanto.otnah package were developed by
 * Ted Meyer and Nilesh Patel for the term project in CS4233
 * at Worcester Polytechnic Institute. Since WPI holds all other forms
 * of ownership to this software, we have decided to not make this
 * software available under any license. Evaluation or compilation rights
 * are therefore granted only to course staff.
 *******************************************************************************/

package hanto.studentotnah.tournament;

import java.util.Collection;

import hanto.common.HantoException;
import hanto.common.HantoGameID;
import hanto.common.HantoPlayerColor;
import hanto.studentotnah.common.GameState;
import hanto.studentotnah.common.moves.PotentialMove;
import hanto.studentotnah.epsilon.EpsilonHantoGame;
import hanto.tournament.HantoGamePlayer;
import hanto.tournament.HantoMoveRecord;

/**
 * 
 * @author otnah
 *
 *	The AI for the game
 */
public class HantoPlayer implements HantoGamePlayer
{
	private EpsilonHantoGame game;
	
	@Override
	public void startGame(HantoGameID version, HantoPlayerColor myColor, boolean doIMoveFirst)
	{
		if (doIMoveFirst)
		{
			game = new EpsilonHantoGame(myColor);
		}
		else
		{
			game = new EpsilonHantoGame(other(myColor));
		}
	}
	
	/**
	 * get the other color
	 * @param col the current color
	 * @return the other color
	 */
	public HantoPlayerColor other(HantoPlayerColor col)
	{
		HantoPlayerColor result = null;
		switch(col)
		{
			case BLUE:
				result = HantoPlayerColor.RED;
				break;
			case RED:
				result = HantoPlayerColor.BLUE;
				break;
		}
		return result;
	}
	

	@Override
	public HantoMoveRecord makeMove(HantoMoveRecord opponentsMove)
	{
		try
		{
			if (opponentsMove != null)
			{
				game.makeMove(opponentsMove.getPiece(), opponentsMove.getFrom(), opponentsMove.getTo());
			}
			
			Collection<PotentialMove> allMoves = game.getMoveEnumerator().getAllCurrentMoves(game);
			
			if (allMoves.size() == 0)
			{
				System.out.println("resigning");
				throw new HantoException("there are no more moves, resign");
			}
			
			HantoMoveRecord hmr = rank(allMoves, game).asHantoMoveRecord();
			
			game.makeMove(hmr.getPiece(), hmr.getFrom(), hmr.getTo());
			
			return hmr;
		}
		catch (HantoException e)
		{
			System.out.println(e.getMessage());
			System.out.println(game.getPrintableBoard());
			return new HantoMoveRecord(null, null, null);
		}
	}

	/**
	 * sort the potential moves by value and give them back in that order
	 * @param allMoves all the possible moves
	 * @return the sorted list
	 */
	public PotentialMove rank(Collection<PotentialMove> allMoves, GameState state) {
		
		int max = -99999;
		PotentialMove result = null;
		
		for(PotentialMove p : allMoves)
		{
			int score = p.score(state);
			if (score > max)
			{
				result = p;
				max = score;
			}
			
		}
		
		return result;
	}
}
