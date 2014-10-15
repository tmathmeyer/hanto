/*******************************************************************************
 * All sources under the hanto.otnah package were developed by
 * Ted Meyer and Nilesh Patel for the term project in CS4233
 * at Worcester Polytechnic Institute. Since WPI holds all other forms
 * of ownership to this software, we have decided to not make this
 * software available under any license. Evaluation or compilation rights
 * are therefore granted only to course staff.
 *******************************************************************************/

package hanto.studentotnah.common.pieces.moves;

import static hanto.studentotnah.common.util.CollectionUtils.positionsToPieces;
import static hanto.studentotnah.common.util.HexUtil.slideBlockers;

import java.util.Collection;
import java.util.List;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentotnah.common.GameState;
import hanto.studentotnah.common.HantoTile;
import hanto.studentotnah.common.HexPosition;
import hanto.studentotnah.common.Position;
import hanto.studentotnah.common.util.HexUtil;

/**
 * 
 * @author otnah
 *
 * an abstract piece move validator, from which all piece move validators
 * are defined. It contains helper methods that do math and hex magic
 */
public abstract class PieceMoveValidator
{
	
	private GameState latest;
	
	/**
	 * @param to the position from which a piece is moving
	 * @param from the position to which the piece is moving
	 * @param state the current game state
	 * @return whether the move is valid or not
	 */
	public boolean isValidMove(Position to, Position from, GameState state)
	{
		latest = state;
		return isValidMove(to, from);
	}
	
	/**
	 * 
	 * @param to the position to which the piece is moving
	 * @param from the position from which the piece is moving
	 * @return whether that move is valid
	 */
	protected abstract boolean isValidMove(Position to, Position from);
	
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
		boolean myColor = false;
		boolean theirColor = false;
		for(HantoCoordinate each : pos.adjacentPositions())
		{
			HantoPiece tile = latest.getPieceAt(each);
			
			if (tile != null)
			{
				if (tile.getColor() != color)
				{
					theirColor = true;
				} 
				else
				{
					myColor = true;
				}
			}
		}
		return (myColor && !theirColor) || (latest.filledLocations().size() <= 1) || (color == null);
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
		if (type == HantoPieceType.BUTTERFLY || !hasPieceInInventory(HantoPieceType.BUTTERFLY))
		{
			return true;
		}
		else
		{
			return latest.getCurrentPlayer().getMovesPlayed() < limit;
		}
	}
	
	/**
	 * 
	 * @param hpt the piece type
	 * @return whether the player has the piece type in his inventory
	 */
	protected boolean hasPieceInInventory(HantoPieceType hpt)
	{
		for(HantoTile ht : latest.getCurrentPlayer().getInventory())
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
		return latest.filledLocations().size() == 0
				&& check.getDistanceTo(new HexPosition(0, 0)) == 0;
	}
	
	/**
	 * 
	 * @param check the location
	 * @return whether that location is unoccupied
	 */
	protected boolean isLocationUnoccupied(Position check)
	{
		return latest.getPieceAt(check) == null;
	}
	
	/**
	 * 
	 * @param from the location from where the piece originates
	 * @param to the position to where teh piece is walking
	 * @return whether this piece is walking bloked
	 */
	protected boolean isWalkingBlocked(Position from, Position to)
	{	
		return positionsToPieces(slideBlockers(from, to), latest).size() < 2;
	}
	
	/**
	 * 
	 * @param check the position
	 * @return check that there is a piece next to us
	 */
	protected boolean piecePlaceContinuityCheck(Position check)
	{	
		return check.adjacentTiles(latest).size() != 0;
	}
	
	/**
	 * 
	 * @return the color of the current player
	 */
	protected HantoPlayerColor currentPlayerColor()
	{
		return latest.getCurrentPlayer().getColor();
	}
	
	/**
	 * 
	 * @param from the position to which the piece is being moved
	 * @param to the position to which the piece is being moves
	 * @return whether the continuity is perserved without from and with to
	 */
	protected boolean isGraphContinuityPreservedAfter(Position from, Position to)
	{
		return latest.isGraphContinuityPreservedAfter(from, to);
	}
	
	/**
	 * 
	 * @param pos the position to check
	 * @param type the assumed type
	 * @return whether the type of piece at this location is the provided type
	 */
	protected boolean isPieceAtPositionCorrectType(Position pos, HantoPieceType type)
	{
		return pos.hasPieceType(latest, type);
	}
	
	/**
	 * @param to one end position
	 * @param from the other end
	 * @return whether every spot between these two has a piece in it
	 */
	protected boolean areAllPiecesBetweenFilled(Position to, Position from)
	{
		Collection<Position> positionsInBetween = HexUtil.getLinearSlide(to, from);
		
		List<HantoPiece> piecesInBetween = positionsToPieces(positionsInBetween, latest);
		
		return positionsInBetween.size() != 0 &&
			   positionsInBetween.size() == piecesInBetween.size();
	}
}
