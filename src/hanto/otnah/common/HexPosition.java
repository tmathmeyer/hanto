package hanto.otnah.common;

import java.util.Collection;
import hanto.common.HantoCoordinate;
import hanto.otnah.common.Position;
import hanto.otnah.common.util.HexUtil;

public class HexPosition extends Position
{
	private final int x, y;
	
	public HexPosition(int xLoc, int yLoc)
	{
		x = xLoc;
		y = yLoc;
	}
	
	public HexPosition(HantoCoordinate c)
	{
		this(c.getX(),c.getY());
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
	public Collection<HantoCoordinate> adjacentPositions(){
		return HexUtil.locationsSurrounding(this);
	}

}