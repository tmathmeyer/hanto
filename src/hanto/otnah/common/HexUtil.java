package hanto.otnah.common;

import hanto.common.HantoCoordinate;

public class HexUtil
{
	public static int distance(HantoCoordinate from, HantoCoordinate to)
	{
		int deltax = from.getX() - to.getX();
		int deltay = from.getY() - to.getY();
		int deltad = deltax - deltay;
		
		return absMax(deltax, deltay, deltad);
	}
	
	public static int absMax(int ... list)
	{
		int max = 0;
		for(int i : list)
		{
			max = Math.max(max, Math.abs(i));
		}
		return max;
	}
}
