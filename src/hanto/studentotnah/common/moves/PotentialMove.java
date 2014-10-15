/*******************************************************************************
 * All sources under the hanto.otnah package were developed by
 * Ted Meyer and Nilesh Patel for the term project in CS4233
 * at Worcester Polytechnic Institute. Since WPI holds all other forms
 * of ownership to this software, we have decided to not make this
 * software available under any license. Evaluation or compilation rights
 * are therefore granted only to course staff.
 *******************************************************************************/

package hanto.studentotnah.common.moves;

import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentotnah.common.GameState;
import hanto.studentotnah.common.InventoryPosition;
import hanto.studentotnah.common.Position;
import hanto.tournament.HantoMoveRecord;
import static hanto.common.HantoPieceType.*;

/**
 * 
 * @author otnah
 *
 */
public class PotentialMove
{
	private final Position to, from;
	private final HantoPlayerColor color;
	private final HantoPieceType type;
	
	/**
	 * 
	 * @param to the position to which the move is made
	 * @param from the position from which the move is made
	 * @param color the color of the piece being moved
	 * @param type the type of piece being moved
	 */
	public PotentialMove(Position to, Position from, HantoPlayerColor color, HantoPieceType type)
	{
		this.to = to;
		this.from = from;
		this.color = color;
		this.type = type;
	}

	/**
	 * @return the to
	 */
	public Position getTo() {
		return to;
	}

	/**
	 * @return the from
	 */
	public Position getFrom() {
		return from;
	}

	/**
	 * @return the color
	 */
	public HantoPlayerColor getColor() {
		return color;
	}

	/**
	 * @return the type
	 */
	public HantoPieceType getType() {
		return type;
	}

	/**
	 * converts internal representation to pollice's external one
	 * @return the converted external representation
	 */
	public HantoMoveRecord asHantoMoveRecord()
	{
		return new HantoMoveRecord(type, from.unwrap(), to.unwrap());
	}

	/**
	 * tests whether this move is valid
	 * @param state
	 * @return
	 * @throws HantoException 
	 */
	public boolean isValid(GameState state) throws HantoException {
		return state.isMovePossible(from, to, type, color);
	}

	/**
	 * Scores a move based on some stupid heuristic thing
	 * @param state the game state
	 * @return a score
	 */
	public int score(GameState state) {
		/*
		if (p.getFrom() instanceof InventoryPosition && p.getType() == HantoPieceType.BUTTERFLY)
		{
			List<PotentialMove> nr = new LinkedList<>();
			nr.add(p);
			return nr;
		}
		*/
		
		int result = 0;
		
		// make the first move
		if (from instanceof InventoryPosition && type == BUTTERFLY)
		{
			result += 30;
		}
		
		// if it juts up to the next players BF, do it, if it's a win, REALLY do it.
		if (state.getCurrentPlayer().getNextPlayer().getButterflyPosition().isAdjacentTo(to))
		{
			result += 5;
			if (state.getCurrentPlayer().getNextPlayer().getButterflyPosition().adjacentTiles(state).size() == 5)
			{
				result += 300;
			}
		}
		
		if (state.getCurrentPlayer().getNextPlayer().getButterflyPosition().isAdjacentTo(from))
		{
			result -= 5;
			if (state.getCurrentPlayer().getNextPlayer().getButterflyPosition().adjacentTiles(state).size() == 5)
			{
				result -= 300;
			}
		}
		
		// if this would border our butterfly, avoid it, if it would sepuku, REALLY dont do it.
		if (state.getCurrentPlayer().getButterflyPosition().isAdjacentTo(to))
		{
			result -= 1;
			if (state.getCurrentPlayer().getButterflyPosition().adjacentTiles(state).size() == 5)
			{
				result -= 300;
			}
		}
		
		// favor new piece plays
		if (from instanceof InventoryPosition)
		{
			if (type == BUTTERFLY)
			{
				result += 30;
			}
			if (type == SPARROW)
			{
				result += 5;
			}
			if (type == HORSE){
				result += 3;
			}
			if (type == CRAB)
			{
				result += 1;
			}
		}
		
		return result;
	}
}
