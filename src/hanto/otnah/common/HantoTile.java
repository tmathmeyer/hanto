package hanto.otnah.common;

import java.util.Collection;

import hanto.common.HantoPiece;
import hanto.common.HantoCoordinate;

/**
 * Class for handling the things pieces can do and things
 * around them.
 */
public abstract class HantoTile implements HantoPiece{
	Position position;
	boolean played;
	
	/**
	 * Makes the tile move to the given coordinates, regardless
	 * of whether or not it is valid, make sure the position is
	 * valid before calling this.
	 * @param to The position to move to.
	 */
	public abstract void move(HantoCoordinate to);
	
	/**
	 * Checks whether a given position is a valid position for the tile to move to.
	 * @param to The position to check against.
	 * @param game The current GameState object.
	 * @return
	 */
	public abstract boolean isValidMove(Position to);
	
	/**
	 * Returns a collection of the adjacent non-empty tiles.
	 * @return the collection of non-empty tiles
	 */
	public abstract Collection<HantoTile> getAdjacentPieces();
	
	/**
	 * Gets Current Position
	 * @return the Position object
	 */
	public Position getPosition(){
		return position;
	}
	
	/**
	 * Tells whether the tile is in play or not.
	 * @return true if on the board;
	 */
	public boolean inPlay(){
		return played;
	}
	
}
