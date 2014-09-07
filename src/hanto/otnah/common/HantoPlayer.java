package hanto.otnah.common;

import hanto.common.HantoPlayerColor;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author otnah
 *
 */
public abstract class HantoPlayer
{
	private final HantoPlayerColor playerColor;
	private final Set<Position> currentPositions;
	
	
	/**
	 * 
	 * @param hpc the color of this player
	 */
	public HantoPlayer(HantoPlayerColor hpc, Set<Position> defaultInventory)
	{
		playerColor = hpc;
		currentPositions = defaultInventory;
	}
	
	/**
	 * PlayerColor Accessor
	 * @return the color of this player
	 */
	public HantoPlayerColor getColor()
	{
		return playerColor;
	}
	
	/**
	 * 
	 * @return get a collection of positions where this player has pieces
	 */
	public Collection<Position> getCurrentPositions()
	{
		return currentPositions;
	}
	
	/**
	 * 
	 * @return all the tiles in this players inventory
	 */
	public Set<Position> getInventory()
	{
		return currentPositions;
	}
}
