package hanto.otnah.common.util;

import java.util.Collection;
import java.util.HashSet;

import hanto.common.HantoCoordinate;
import hanto.otnah.alpha.AlphaPosition;

public class HexUtil
{
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
	
	public static Collection<HantoCoordinate> locationsSurrounding(HantoCoordinate position)
	{
		Collection<HantoCoordinate> surrounding = new HashSet<>();
		if (position == null)
		{
			return surrounding;
		}
		
		int x = position.getX();
		int y = position.getY();
		
		surrounding.add(new AlphaPosition(x+1, y));
		surrounding.add(new AlphaPosition(x-1, y));
		surrounding.add(new AlphaPosition(x-1, y+1));
		surrounding.add(new AlphaPosition(x, y+1));
		surrounding.add(new AlphaPosition(x+1, y-1));
		surrounding.add(new AlphaPosition(x, y-1));
		
		return surrounding;
	}
}
