/*******************************************************************************
 * All sources under the hanto.otnah package were developed by
 * Ted Meyer and Nilesh Patel for the term project in CS4233
 * at Worcester Polytechnic Institute. Since WPI holds all other forms
 * of ownership to this software, we have decided to not make this
 * software available under any license. Evaluation or compilation rights
 * are therefore granted only to course staff.
 *******************************************************************************/

package hanto.otnah.common.moves;

import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.otnah.common.Position;

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
}
