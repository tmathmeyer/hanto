/*******************************************************************************
 * All sources under the hanto.otnah package were developed by
 * Ted Meyer and Nilesh Patel for the term project in CS4233
 * at Worcester Polytechnic Institute. Since WPI holds all other forms
 * of ownership to this software, we have decided to not make this
 * software available under any license. Evaluation or compilation rights
 * are therefore granted only to course staff.
 *******************************************************************************/

package hanto.otnah.common;

import java.util.Collection;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.otnah.common.util.HexUtil;

/**
 * 
 * @author otnah
 *
 */
public class HexPosition extends Position
{
	private final int x, y;
	
	/**
	 * Default Constructor
	 * 
	 * @param xLoc the x coord
	 * @param yLoc the y coord
	 */
	public HexPosition(int xLoc, int yLoc)
	{
		x = xLoc;
		y = yLoc;
	}
	
	@Override
	public int getX()
	{
		return x;
	}

	@Override
	public int getY()
	{
		return y;
	}

	@Override
	public int getDistanceTo(Position other)
	{
		return other.distanceFrom(this);
	}

	@Override
	public int distanceFrom(Position other) {
		return HexUtil.distance(this, other);
	}
	
	@Override
	public Collection<HantoCoordinate> adjacentCoordinates(){
		return HexUtil.locationsSurrounding(this);
	}
	
	@Override
	public boolean hasPieceType(GameState state, HantoPieceType type)
	{
		HantoPiece piece = state.getPieceAt(this);
		return piece != null && piece.getType() == type;
	}

}