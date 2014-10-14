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
import java.util.Set;

import hanto.otnah.common.HexPosition;
import hanto.otnah.common.Position;

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
	public static int distance(Position from, Position to)
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
	public static Collection<Position> locationsSurrounding(Position position)
	{
		Set<Position> surrounding = new HashSet<>();
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
	public static Collection<Position> slideBlockers(Position a, Position b)
	{
		Collection<Position> aSurrounds = locationsSurrounding(a);
		Collection<Position> bSurrounds = locationsSurrounding(b);
		
		aSurrounds.retainAll(bSurrounds);
		return aSurrounds;
	}
	
	/**
	 * 
	 * @param a the first point
	 * @param b the second point
	 * @return whether the pieces are in a straight line
	 */
	public static boolean checkLinearality(Position a, Position b)
	{
		int dx = a.getX() - b.getX();
		int dy = a.getY() - b.getY();
		
		if (dx == 0 || dy == 0)
		{
			return true;
		}
		
		return dx == -dy;
	}
	
	/**
	 * 
	 * @param a the first point
	 * @param b the second point
	 * @return the set of pieces in a striaght line between the two provided end points, non inclusive
	 */
	public static Collection<Position> getLinearSlide(Position a, Position b)
	{
		Set<Position> result = new HashSet<Position>();
		
		int dx = a.getX() - b.getX();
		int dy = a.getY() - b.getY();
		
		int distance = Math.max(Math.abs(dx), Math.abs(dy));
		
		if (distance > 1)
		{
			int adx = dx / distance;
			int ady = dy / distance;
			
			for(int i = 1; i < distance; i++)
			{
				result.add(new HexPosition(a.getX() - (i * adx), a.getY() - (i * ady)));
			}
		}
		
		return result;
	}
}
