package hanto.otnah.beta;

import hanto.otnah.common.Position;
import hanto.otnah.common.util.HexUtil;

public class BetaPosition extends Position
{
	private final int x, y;
	
	public BetaPosition(int xLoc, int yLoc)
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

}