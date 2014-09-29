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

public abstract class PieceMoveValidator
{
	public abstract boolean isValidMove(Position to, Position from);
	
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
	
	protected boolean isFirstMove(Position check)
	{
		return gameInstance().filledLocations().size() == 0
				&& check.getDistanceTo(new HexPosition(0, 0)) == 0;
	}
	
	protected boolean isLocationUnoccupied(Position check)
	{
		return gameInstance().getPieceAt(check) == null;
	}
	
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
	
	protected HantoPlayerColor currentPlayerColor()
	{
		return gameInstance().getCurrentPlayer().getColor();
	}
	
	protected boolean isGraphContinuityPreservedSans(Position pos)
	{
		return gameInstance().isGraphContinuityPreservedSans(pos);
	}
}
