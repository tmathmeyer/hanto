package hanto.otnah.common;

import hanto.common.HantoPlayerColor;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


public abstract class HantoPlayer
{
	private final HantoPlayerColor playerColor;
	private final Set<Position> currentPositions;
	
	public HantoPlayer(HantoPlayerColor hpc)
	{
		playerColor = hpc;
		currentPositions = new HashSet<>();
	}
	
	public HantoPlayerColor getColor()
	{
		return playerColor;
	}
	
	public Collection<Position> getCurrentPositions()
	{
		return currentPositions;
	}
	
	public abstract boolean canMakeMove();
	
	public abstract Collection<HantoTile> getInventory();
}
