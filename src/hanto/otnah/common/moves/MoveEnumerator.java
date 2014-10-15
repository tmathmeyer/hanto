/*******************************************************************************
 * All sources under the hanto.otnah package were developed by
 * Ted Meyer and Nilesh Patel for the term project in CS4233
 * at Worcester Polytechnic Institute. Since WPI holds all other forms
 * of ownership to this software, we have decided to not make this
 * software available under any license. Evaluation or compilation rights
 * are therefore granted only to course staff.
 *******************************************************************************/

package hanto.otnah.common.moves;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;

import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.otnah.common.GameState;
import hanto.otnah.common.HantoPlayer;
import hanto.otnah.common.HantoTile;
import hanto.otnah.common.HexPosition;
import hanto.otnah.common.InventoryPosition;
import hanto.otnah.common.Position;
import hanto.otnah.common.util.CollectionUtils;
import hanto.otnah.common.util.CollectionUtils.Lambda;

/**
 * 
 * @author otnah
 *
 */
public class MoveEnumerator
{
	/**
	 * generates a collection of all positions a player may place a tile onto
	 * from their inventory, in no particular order.
	 * 
	 * @param state the current game state
	 * @return the collection of positions
	 */
	public Collection<Position> generatePiecePlays(GameState state)
	{
		HantoPlayer currentPlayer = state.getCurrentPlayer();
		
		Collection<Position> hasOnBoard = currentPlayer.getCurrentPositions();
		Collection<Position> adjacentToCurrent = new HashSet<>();
		HantoPlayerColor cantTouchThis = currentPlayer.getNextPlayer().getColor();
		if (state.filledLocations().size() == 0)
		{
			adjacentToCurrent.add(new HexPosition(0, 0));
		}
		else if (state.filledLocations().size() == 1)
		{
			adjacentToCurrent.addAll(new HexPosition(0, 0).adjacentPositions());
			cantTouchThis = null;
		}
		
		for(Position p : hasOnBoard)
		{
			adjacentToCurrent.addAll(p.adjacentPositions());
		}
		
		adjacentToCurrent.removeAll(hasOnBoard);
		adjacentToCurrent.removeAll(currentPlayer.getNextPlayer().getCurrentPositions());
		return filterByPlacementAvailibility(adjacentToCurrent, cantTouchThis, state);
		
	}
	
	/**
	 * generate a collection containing all fringe spots, ie, places that a
	 * player could move a piece to, since it borders at least one other piece
	 * and is currently unoccupied.
	 * 
	 * @param state the game state
	 * @return the collection of positions
	 */
	public Collection<Position> generatePieceMoveTos(GameState state)
	{
		Collection<Position> hasOnBoard = new HashSet<>();
		Collection<Position> adjacentToCurrent = new HashSet<>();
		for(HantoPlayer p : state.getCurrentPlayer().collectAllUsers())
		{
			hasOnBoard.addAll(p.getCurrentPositions());
		}
		for(Position p : hasOnBoard)
		{
			adjacentToCurrent.addAll(p.adjacentPositions());
		}
		adjacentToCurrent.removeAll(hasOnBoard);
		return adjacentToCurrent;
	}
	
	public Collection<PotentialMove> getMovesCanMake(GameState state) throws HantoException
	{
		Collection<PotentialMove> result = new HashSet<>();
		Collection<PotentialMove> temp = new HashSet<>();
		Collection<Position> openSpots = generatePieceMoveTos(state);
		Collection<Position> myPieces = state.getCurrentPlayer().getCurrentPositions();
		
		for(Position from : myPieces)
		{
			for(Position to : openSpots)
			{
				HantoPlayerColor color = state.getPieceAt(from).getColor();
				HantoPieceType type = state.getPieceAt(from).getType();
				temp.add(new PotentialMove(to, from, color, type));
			}
		}
		
		for(PotentialMove pm : temp)
		{
			if (pm.isValid(state)) {
				result.add(pm);
			}
		}
		
		return result;
	}
	
	public Collection<PotentialMove> getPlaysCanMake(GameState state) throws HantoException
	{
		Collection<PotentialMove> result = new HashSet<>();
		Collection<PotentialMove> temp = new HashSet<>();
		Collection<Position> openSpots = generatePiecePlays(state);
		Collection<HantoTile> myPieces = state.getCurrentPlayer().getInventory();
		
		for(HantoTile from : myPieces)
		{
			for(Position to : openSpots)
			{
				HantoPlayerColor color = state.getCurrentPlayer().getColor();
				HantoPieceType type = from.getType();
				temp.add(new PotentialMove(to, new InventoryPosition(), color, type));
			}
		}
		
		for(PotentialMove pm : temp)
		{
			if (pm.isValid(state)) {
				result.add(pm);
			}
		}
		
		return result;
	}
	
	/**
	 * gets all the potential moves
	 * 
	 * @param state the gamestate
	 * @return all potential moves
	 * @throws HantoException if there is an error
	 */
	public Collection<PotentialMove> getAllCurrentMoves(GameState state) throws HantoException
	{
		Collection<PotentialMove> newPiecePlays = getPlaysCanMake(state);
		Collection<PotentialMove> movePiecePlays = getMovesCanMake(state);
		
		newPiecePlays.addAll(movePiecePlays);
		return newPiecePlays;
	}
	
	
	private Collection<Position> filterByPlacementAvailibility
	(Collection<Position> plays, final HantoPlayerColor cantTouchThis, final GameState state)
	{
		return CollectionUtils.filter(plays, new Lambda<Position, Boolean>(){
			@Override
			public Boolean apply(Position in) {
				Collection<HantoPiece> tiles = in.adjacentTiles(state);
				for(HantoPiece p : tiles)
				{
					if (p.getColor() == cantTouchThis)
					{
						return false;
					}
				}
				return true;
			}	
		}, new LinkedList<Position>());
	}
}
