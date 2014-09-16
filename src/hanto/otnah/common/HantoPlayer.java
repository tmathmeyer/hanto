/*******************************************************************************
 * All sources under the hanto.otnah package were developed by
 * Ted Meyer and Nilesh Patel for the term project in CS4233
 * at Worcester Polytechnic Institute. Since WPI holds all other forms
 * of ownership to this software, we have decided to not make this
 * software available under any license. Evaluation or compilation rights
 * are therefore granted only to course staff.
 *******************************************************************************/

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
 * @param <T> the subclass of HantoPlayer
 */
public abstract class HantoPlayer<T extends HantoPlayer<T>>
{
	private final HantoPlayerColor playerColor;
	private final Set<Position> currentPositions = new HashSet<>();
	private final List<HantoTile> currentInventory;
	private int movesPlayed;
	
	
	/**
	 * 
	 * @param hpc the player color of this player
	 * @param defaultInventory the default inventory of the player
	 */
	protected HantoPlayer(HantoPlayerColor hpc, List<HantoTile> defaultInventory)
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

	/**
	 * removes a single piece of this type from the inventory
	 * @param pieceType the piece type to remove from the inventory
	 * @return the piece removed
	 */
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
