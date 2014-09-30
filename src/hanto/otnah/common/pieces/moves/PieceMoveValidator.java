/*******************************************************************************
 * All sources under the hanto.otnah package were developed by
 * Ted Meyer and Nilesh Patel for the term project in CS4233
 * at Worcester Polytechnic Institute. Since WPI holds all other forms
 * of ownership to this software, we have decided to not make this
 * software available under any license. Evaluation or compilation rights
 * are therefore granted only to course staff.
 *******************************************************************************/

package hanto.otnah.common.pieces.moves;

import static hanto.otnah.common.util.CollectionUtils.map;
import static hanto.otnah.common.util.HexUtil.slideBlockers;
import static hanto.otnah.common.GameStateSingleton.gameInstance;

import java.util.LinkedList;
import java.util.List;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.otnah.common.HantoTile;
import hanto.otnah.common.HexPosition;
import hanto.otnah.common.Position;
import hanto.otnah.common.util.CollectionUtils.Lambda;

/**
 * 
 * @author otnah
 *
 * an abstract piece move validator, from which all piece move validators
 * are defined. It contains helper methods that do math and hex magic
 */
public abstract class PieceMoveValidator
{
	/**
	 * 
	 * @param to the position to which the piece is moving
	 * @param from the position from which the piece is moving
	 * @return whether that move is valid
	 */
	public abstract boolean isValidMove(Position to, Position from);
	
	/**
	 * 
	 * @param pos the position to check
	 * @param color the color of the player
	 * @return whether the piece can be played here
	 */
	protected boolean isValidNewPlaceLocation(Position pos, HantoPlayerColor color)
	{
		/*
		 * the piece must be next to a piece of it's own color, and not next to
		 * a piece of the opposing color
		 */
		boolean result = isFirstMove(pos);
		for(HantoCoordinate each : pos.adjacentPositions())
		{
			HantoPiece tile = gameInstance().getPieceAt(each);
			
			if (tile != null)
			{
				if (tile.getColor() != color)
				{
					return false;
				} 
				else
				{
					result = true;
				}
			}
		}
		return result;
	}
	
	/**
	 * Check whether the butterfly has been played by a certain turn number
	 * @param type the type of piece being played
	 * @param limit the limit number of moves during which the player must play the butterfly
	 * @return whether or not this move is legal with respect to the move count
	 */
	protected boolean checkButterflyLegality(HantoPieceType type, int limit)
	{
		/*
		 * if the butterfly has been played, or is the current piece to be played, all good
		 * otherwise, the move count must be less than the limit
		 */
		if (type == HantoPieceType.BUTTERFLY || hasPieceInInventory(HantoPieceType.BUTTERFLY))
		{
			return true;
		}
		else
		{
			return gameInstance().getCurrentPlayer().getMovesPlayed() < limit;
		}
	}
	
	/**
	 * 
	 * @param hpt the piece type
	 * @return whether the player has the piece type in his inventory
	 */
	protected boolean hasPieceInInventory(HantoPieceType hpt)
	{
		for(HantoTile ht : gameInstance().getCurrentPlayer().getInventory())
		{
			if (ht.getType().equals(hpt))
			{
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * 
	 * @param check the position being played
	 * @return whether this is the first move
	 */
	protected boolean isFirstMove(Position check)
	{
		return gameInstance().filledLocations().size() == 0
				&& check.getDistanceTo(new HexPosition(0, 0)) == 0;
	}
	
	/**
	 * 
	 * @param check the location
	 * @return whether that location is unoccupied
	 */
	protected boolean isLocationUnoccupied(Position check)
	{
		return gameInstance().getPieceAt(check) == null;
	}
	
	/**
	 * 
	 * @param from the location from where the piece originates
	 * @param to the position to where teh piece is walking
	 * @return whether this piece is walking bloked
	 */
	protected boolean isWalkingBlocked(Position from, Position to)
	{
		List<HantoPiece> blockingPieces = map(slideBlockers(from, to), new Lambda<HantoCoordinate, HantoPiece>(){
			@Override
			public HantoPiece apply(HantoCoordinate in) {
				return gameInstance().getPieceAt(in);
			}
		}, new LinkedList<HantoPiece>());
		
		return blockingPieces.size() < 2;
	}
	
	/**
	 * 
	 * @return the color of the current player
	 */
	protected HantoPlayerColor currentPlayerColor()
	{
		return gameInstance().getCurrentPlayer().getColor();
	}
	
	/**
	 * 
	 * @param from the position to which the piece is being moved
	 * @param to the position to which the piece is being moves
	 * @return whether the continuity is perserved without from and with to
	 */
	protected boolean isGraphContinuityPreservedAfter(Position from, Position to)
	{
		return gameInstance().isGraphContinuityPreservedAfter(from, to);
	}
}
