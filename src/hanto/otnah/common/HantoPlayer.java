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
import hanto.common.MoveResult;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author otnah
 *
 */
public class HantoPlayer
{
	private final HantoPlayerColor playerColor;
	private final Set<Position> currentPositions = new HashSet<>();
	private final List<HantoTile> currentInventory;
	private int movesPlayed;
	private HantoPlayer nextPlayer;
	private Position butterflyPosition = new InventoryPosition();
	
	
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
	 * @param to the place the piece is being played to
	 * @return the piece removed
	 */
	public HantoTile play(HantoPieceType pieceType, Position to)
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
		
		currentPositions.add(to);
		
		return currentInventory.remove(index);
	}
	
	/**
	 * @return the player who's turn it is next
	 */
	public HantoPlayer getNextPlayer()
	{
		return nextPlayer;
	}
	
	/**
	 * re-arrange the order of players
	 * 
	 * @param next the next player
	 */
	public void setNextPlayer(HantoPlayer next)
	{
		nextPlayer = next;
	}

	/**
	 * 
	 * @return how many moves this player has made
	 */
	public int getMovesPlayed()
	{
		return movesPlayed;
	}
	
	/**
	 * 
	 * @return the location of this players butterfly
	 */
	public Position getButterflyPosition()
	{
		return butterflyPosition;
	}
	
	/**
	 * 
	 * @param p the location at which the player has placed his butterfly
	 */
	public void setButterflyPosition(Position p)
	{
		butterflyPosition = p;
	}
	
	/**
	 * @param hpc the color
	 * @param defaultInventory the default inventory
	 * @return a linked hanto player with those types
	 */
	public static HantoPlayer makePlayer(HantoPlayerColor hpc,
			List<HantoTile> defaultInventory) {
		return new HantoPlayer(hpc, defaultInventory);
	}
	
	/**
	 * 
	 * @param firstPlayer the color of the first player
	 * @return the player with that colors
	 */
	public HantoPlayer skipTo(HantoPlayerColor firstPlayer) {
		if (getColor() == firstPlayer)
		{
			return this;
		}
		return getNextPlayer().skipTo(firstPlayer);
	}
	
	/**
	 * @param state the board state to check on
	 * @return whether this players butterfly is surrounded
	 */
	public boolean isSurrounded(GameState state)
	{
		return getButterflyPosition().adjacentTiles(state).size() == 6;
	}

	/**
	 * @return the total number of moves played by all players
	 */
	public int getTotalMoves() {
		int count = 0;
		for(HantoPlayer each : collectAllUsers())
		{
			count += each.getMovesPlayed();
		}
		return count;
	}
	
	/**
	 * @return the winning state of this player
	 */
	public MoveResult getWinningState()
	{
		MoveResult result;
		switch(getColor())
		{
			case BLUE:
				result = MoveResult.BLUE_WINS;
				break;
			case RED:
				result = MoveResult.RED_WINS;
				break;
			default:
				result = null;
		}
		return result;
	}
	
	/**
	 * gets the state resulting from a loss by this player
	 * @return the state the game would be in if this player lost
	 */
	public MoveResult getLosingState()
	{
		MoveResult result;
		switch(getColor())
		{
			case BLUE:
				result = MoveResult.RED_WINS;
				break;
			case RED:
				result = MoveResult.BLUE_WINS;
				break;
			default:
				result = null;
		}
		return result;
	}
	
	/**
	 * @return the total number of moves
	 */
	public List<HantoPlayer> collectAllUsers() {
		return getNextPlayer().collectAllUsers(getColor());
	}
	
	private List<HantoPlayer> collectAllUsers(HantoPlayerColor color) {
		List<HantoPlayer> all = new ArrayList<>();
		if (color != getColor())
		{
			all = getNextPlayer().collectAllUsers(color);
		}
		all.add(this);
		return all;	
	}

	/**
	 * update the held locations
	 * @param from the old place
	 * @param to the new place
	 */
	public void updateHeldLocations(Position from, Position to) {
		currentPositions.remove(from);
		currentPositions.add(to);
	}
}
