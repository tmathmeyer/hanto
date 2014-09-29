/*******************************************************************************
 * All sources under the hanto.otnah package were developed by
 * Ted Meyer and Nilesh Patel for the term project in CS4233
 * at Worcester Polytechnic Institute. Since WPI holds all other forms
 * of ownership to this software, we have decided to not make this
 * software available under any license. Evaluation or compilation rights
 * are therefore granted only to course staff.
 *******************************************************************************/


package hanto.otnah.gamma;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Collection;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.otnah.common.GameState;
import hanto.otnah.common.HantoPlayer;
import hanto.otnah.common.HantoTile;
import hanto.otnah.common.Position;
import hanto.otnah.common.HexPosition;
import static hanto.otnah.common.util.CollectionUtils.map;
import static hanto.otnah.common.util.HexUtil.slideBlockers;
import static hanto.otnah.common.util.CollectionUtils.Lambda;


/**
 * 
 * @author otnah
 *
 */
public class GammaHantoGame extends GameState
{
	private GammaPlayer current;
	private final GammaPlayer red = new GammaPlayer(HantoPlayerColor.RED);
	private final GammaPlayer blue = new GammaPlayer(HantoPlayerColor.BLUE);

	/**
	 * default constructor
	 * 
	 * @param firstPlayer the color pf the player that goes first
	 */
	public GammaHantoGame(HantoPlayerColor firstPlayer)
	{
		red.setNextPlayer(blue);
		blue.setNextPlayer(red);
		switch (firstPlayer)
		{
			case BLUE:
				current = blue;
				break;
			case RED:
				current = red;
				break;	
		}
	}

	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate fromHC,
			HantoCoordinate toHC) throws HantoException {
		
		Position   to = Position.asPosition(toHC);
		Position from = Position.asPosition(fromHC);
		
		if(isMovePossible(from, to, pieceType, getCurrentPlayer().getColor()))
		{
			//remove piece from inventory
			HantoTile played = removeFrom(from, pieceType);
			
			//put piece on board
			setPieceAt(played, to);
			
			//increment player count
			getCurrentPlayer().increaseMoveCount();
			
			if(pieceType == HantoPieceType.BUTTERFLY)
			{
				getCurrentPlayer().getSelf().setButterflyPosition(to);
			}
			
			//switch player
			current = current.getNextPlayer();
			return gameState();
		}
		
		throw new HantoException("invalid move!");
	}
	
	/**
	 * 
	 * @param pos the position from which to remove the piece
	 * @param type the type of piece to remove
	 * @return the piece that has been removed
	 */
	public HantoTile removeFrom(Position pos, HantoPieceType type)
	{
		return pos.removePieceAt(this, type);
	}

	@Override
	public HantoPlayer<GammaPlayer> getCurrentPlayer()
	{
		return current;
	}

	@Override
	public boolean isMovePossible(Position from, Position to, HantoPieceType type, HantoPlayerColor color)
	{
		/*
		 * the rules for moving are as follows:
		 * 		if the piece is moving from the inventory, it must be placed
		 * 		next to a piece of the same color, and not next to a piece of
		 * 		the opposing player's color. If the player has not played the
		 * 		butterfly by the fourth move, then that player will be forced to.
		 * 
		 * 		if the move is from one board location to another, then the
		 * 		continuity of the board must be preserved, the distance moved
		 * 		must be exactly one, and the move must not be blocked from
		 *  	sliding by other pieces, including allied ones
		 */
		int moveDistance = from.getDistanceTo(to);
		boolean result = isLocationUnoccupied(to);
		if (moveDistance == 1)
		{
			// the move is on the board
			result &= (isWalkingBlocked(to, from) &&
					   isGraphContinuityPreservedSans(from));
		}
		else if (moveDistance == 0)
		{
			// the move is onto the board from the player's inventory
			
			result &= (hasPieceInInventory(type) &&
					   checkButterflyLegality(type, 4) &&
					   isValidNewPlaceLocation(to, color));
		}
		
		return result;
	}
	
	private boolean isValidNewPlaceLocation(Position pos, HantoPlayerColor color)
	{
		/*
		 * the piece must be next to a piece of it's own color, and not next to
		 * a piece of the opposing color
		 */
		boolean result = isFirstMove(pos);
		for(HantoCoordinate each : pos.adjacentPositions())
		{
			HantoPiece tile = getPieceAt(each);
			
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
	
	private boolean checkButterflyLegality(HantoPieceType type, int limit)
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
			return getCurrentPlayer().getMovesPlayed() < limit;
		}
	}
	
	private boolean hasPieceInInventory(HantoPieceType hpt)
	{
		for(HantoTile ht : getCurrentPlayer().getInventory())
		{
			if (ht.getType().equals(hpt))
			{
				return true;
			}
		}
		
		return false;
	}
	
	private boolean isFirstMove(Position check)
	{
		return filledLocations().size() == 0
				&& check.getDistanceTo(new HexPosition(0, 0)) == 0;
	}
	
	private boolean isLocationUnoccupied(Position check)
	{
		return getPieceAt(check) == null;
	}
	
	private boolean isWalkingBlocked(Position from, Position to)
	{
		List<HantoPiece> blockingPieces = map(slideBlockers(from, to), new Lambda<HantoCoordinate, HantoPiece>(){
			@Override
			public HantoPiece apply(HantoCoordinate in) {
				return getPieceAt(in);
			}
		}, new LinkedList<HantoPiece>());
		
		return blockingPieces.size() < 2;
	}
	
	
	
	
	
	
	
	
	
	
	
	

	
	private MoveResult gameState()
	{
		MoveResult returnValue = MoveResult.OK;
		if(isSurrounded(red.getButterflyPosition()))
		{
			returnValue = MoveResult.BLUE_WINS;
		}
		if(isSurrounded(blue.getButterflyPosition()))
		{
			if(returnValue == MoveResult.BLUE_WINS)
			{
				returnValue = MoveResult.DRAW;
			}
			else
			{
				returnValue = MoveResult.RED_WINS;
			}
		}
		if(red.getMovesPlayed() + blue.getMovesPlayed() == 12)
		{
			returnValue = MoveResult.DRAW;
		}
		return returnValue;
	}
	
	private boolean isSurrounded(Position toCheck)
	{
		Collection<HantoCoordinate> positions = toCheck.adjacentPositions();
		if(positions.size() < 6)
		{
			return false;
		}
		for(HantoCoordinate c : positions)
		{
			if(getPieceAt(c) == null)
			{
				return false;
			}
		}
		return true;
	}
	
}
