package hanto.otnah.common;

import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author otnah
 *
 */
public abstract class HantoPlayer
{
	private final HantoPlayerColor playerColor;
	private final Set<Position> currentPositions = new HashSet<>();
	private final List<HantoTile> currentInventory;
	
	
	/**
	 * 
	 * @param hpc the color of this player
	 */
	public HantoPlayer(HantoPlayerColor hpc, List<HantoTile> defaultInventory)
	{
		playerColor = hpc;
		currentInventory = defaultInventory;
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
	public List<HantoTile> getInventory()
	{
		return currentInventory;
	}

	public HantoTile play(HantoPieceType pieceType)
	{
		int index = 0, assumed = 0;
		
		for(HantoTile ht : currentInventory)
		{
			if (ht.getType().equals(pieceType))
			{
				index = assumed;
			}
			assumed ++;
		}
		
		return currentInventory.remove(index);
	}
	
	/**
	 * @return the player who's turn it is next
	 */
	public abstract HantoPlayer getNextPlayer();
	
	/**
	 * re-arrange the order of players
	 * 
	 * @param next the next player
	 */
	public abstract void setNextPlayer(HantoPlayer next);
}
