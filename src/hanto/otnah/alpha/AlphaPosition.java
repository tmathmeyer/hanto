package hanto.otnah.alpha;

import hanto.common.HantoCoordinate;
import hanto.otnah.common.Position;

public class AlphaPosition extends Position
{
	private final int x, y;
	
	public AlphaPosition(int xLoc, int yLoc)
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
	public int getDistanceTo(HantoCoordinate other)
	{
		return -1; //TODO: fix
	}

}
