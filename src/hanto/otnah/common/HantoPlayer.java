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
public abstract class HantoPlayer<T extends HantoPlayer<T>>
{
	private final HantoPlayerColor playerColor;
	private final Set<Position> currentPositions = new HashSet<>();
	private final List<HantoTile> currentInventory;
	private int movesPlayed;
	
	
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
	
	/**
	 * increase the number of times the player has played
	 */
	public void increaseMoveCount()
	{
		movesPlayed ++;
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
	public abstract T getNextPlayer();
	
	/**
	 * @return implicitly casted self reference
	 */
	public abstract T getSelf();
	
	/**
	 * re-arrange the order of players
	 * 
	 * @param next the next player
	 */
	public abstract void setNextPlayer(T next);

	/**
	 * 
	 * @return how many moves this player has made
	 */
	public int getMovesPlayed()
	{
		return movesPlayed;
	}
}
