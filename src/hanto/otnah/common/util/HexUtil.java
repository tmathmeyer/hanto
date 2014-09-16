/*******************************************************************************
 * All sources under the hanto.otnah package were developed by
 * Ted Meyer and Nilesh Patel for the term project in CS4233
 * at Worcester Polytechnic Institute. Since WPI holds all other forms
 * of ownership to this software, we have decided to not make this
 * software available under any license. Evaluation or compilation rights
 * are therefore granted only to course staff.
 *******************************************************************************/


package hanto.otnah.common.util;

import java.util.Collection;
import java.util.HashSet;

import hanto.common.HantoCoordinate;
import hanto.otnah.common.HexPosition;

/**
 * 
 * @author otnah
 *
 */
public class HexUtil
{
	/**
	 * 
	 * @param from start
	 * @param to end
	 * @return the destance between the start and end
	 */
	public static int distance(HantoCoordinate from, HantoCoordinate to)
	{
		int deltax = from.getX() - to.getX();
		int deltay = from.getY() - to.getY();
		if (deltax * deltay > 0) // test if signs are equal
		{
			return Math.abs(deltax + deltay);
		}
		return Math.max(Math.abs(deltax), Math.abs(deltay));
	}
	
	/**
	 * 
	 * @param position the position
	 * @return the positions surrounding the provided position
	 */
	public static Collection<HantoCoordinate> locationsSurrounding(HantoCoordinate position)
	{
		Collection<HantoCoordinate> surrounding = new HashSet<>();
		if (position == null)
		{
			return surrounding;
		}
		
		int x = position.getX();
		int y = position.getY();
		
		surrounding.add(new HexPosition(x+1, y));
		surrounding.add(new HexPosition(x-1, y));
		surrounding.add(new HexPosition(x-1, y+1));
		surrounding.add(new HexPosition(x, y+1));
		surrounding.add(new HexPosition(x+1, y-1));
		surrounding.add(new HexPosition(x, y-1));
		
		return surrounding;
	}
}
