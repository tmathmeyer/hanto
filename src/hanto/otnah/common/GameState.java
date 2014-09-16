/*******************************************************************************
 * All sources under the hanto.otnah package were developed by
 * Ted Meyer and Nilesh Patel for the term project in CS4233
 * at Worcester Polytechnic Institute. Since WPI holds all other forms
 * of ownership to this software, we have decided to not make this
 * software available under any license. Evaluation or compilation rights
 * are therefore granted only to course staff.
 *******************************************************************************/


package hanto.otnah.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import hanto.common.HantoCoordinate;
import hanto.common.HantoGame;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;

/**
 * 
 * @author otnah
 *
 */
public abstract class GameState implements HantoGame
{
	private final Map<Position, HantoTile> gameBoard = new HashMap<>();
	
	/**
	 * Dont let this class be created from outside (not that it can anyways)
	 */
	protected GameState()
	{
		new GameStateSingleton(this);
	}
	
	@Override
	public HantoPiece getPieceAt(HantoCoordinate where)
	{
		return gameBoard.get(Position.asPosition(where));
	}
	
	/**
	 * get the player who's turn it is, IE, that they have not yet made a move
	 * @return the current player
	 */
	public abstract HantoPlayer<?> getCurrentPlayer();
	
	/**
	 * 
	 * @param from the place the piece is moving from (null in the case of 
	 * @param to the location to where the piece might go
	 * @param type the type of piece
	 * @return if there a move is possible
	 */
	public abstract boolean isMovePossible(HantoCoordinate from, HantoCoordinate to, HantoPieceType type);
	
	@Override
	public String getPrintableBoard()
	{
		return "";
	}
	
	/**
	 * Silently fail if the piece can't be added :)
	 * @param piece the piece to add
	 * @param location the location of the piece
	 */
	public void setPieceAt(HantoTile piece, HantoCoordinate location)
	{
		gameBoard.put(Position.asPosition(location), piece);
	}
	
	/**
	 * 
	 * @return the set of all occupied locations on the board
	 */
	protected Set<Position> filledLocations()
	{
		return gameBoard.keySet();
	}
}
