/*******************************************************************************
 * All sources under the hanto.otnah package were developed by
 * Ted Meyer and Nilesh Patel for the term project in CS4233
 * at Worcester Polytechnic Institute. Since WPI holds all other forms
 * of ownership to this software, we have decided to not make this
 * software available under any license. Evaluation or compilation rights
 * are therefore granted only to course staff.
 *******************************************************************************/

package hanto.otnah.tournament;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import hanto.common.HantoException;
import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.otnah.common.InventoryPosition;
import hanto.otnah.common.moves.PotentialMove;
import hanto.otnah.epsilon.EpsilonHantoGame;
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
			
			HantoMoveRecord hmr = rank(allMoves).iterator().next().asHantoMoveRecord();
			
			game.makeMove(hmr.getPiece(), hmr.getFrom(), hmr.getTo());
			
			return hmr;
		}
		catch (HantoException e)
		{
			return new HantoMoveRecord(null, null, null);
		}
	}

	/**
	 * sort the potential moves by value and give them back in that order
	 * @param allMoves all the possible moves
	 * @return the sorted list
	 */
	public Collection<PotentialMove> rank(Collection<PotentialMove> allMoves) {
		
		List<PotentialMove> result = new ArrayList<>(allMoves);
		
		for(PotentialMove p : result)
		{
			if (p.getFrom() instanceof InventoryPosition && p.getType() == HantoPieceType.BUTTERFLY)
			{
				List<PotentialMove> nr = new LinkedList<>();
				nr.add(p);
				return nr;
			}
		}
		
		return result;
	}

}
