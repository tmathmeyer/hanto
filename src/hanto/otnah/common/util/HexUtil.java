/*******************************************************************************
 * All sources under the hanto.otnah package were developed by
 * Ted Meyer and Nilesh Patel for the term project in CS4233
 * at Worcester Polytechnic Institute. Since WPI holds all other forms
 * of ownership to this software, we have decided to not make this
 * software available under any license. Evaluation or compilation rights
 * are therefore granted only to course staff.
 *******************************************************************************/


package hanto.otnah.common.util;

import java.util.HashSet;
import java.util.Set;

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
	public static Set<HantoCoordinate> locationsSurrounding(HantoCoordinate position)
	{
		Set<HantoCoordinate> surrounding = new HashSet<>();
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
	
	/**
	 * 
	 * @param a a position
	 * @param b another position
	 * @return the two pieces that would block sliding from a to b if they were occupied
	 */
	public static Set<HantoCoordinate> slideBlockers(HantoCoordinate a, HantoCoordinate b)
	{
		Set<HantoCoordinate> aSurrounds = locationsSurrounding(a);
		Set<HantoCoordinate> bSurrounds = locationsSurrounding(b);
		
		aSurrounds.retainAll(bSurrounds);
		return aSurrounds;
	}
}
