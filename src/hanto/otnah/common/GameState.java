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
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.otnah.common.util.graph.HexGraph;
import static hanto.otnah.common.Position.asPosition;
/**
 * 
 * @author otnah
 *
 */
public abstract class GameState implements HantoGame
{
	private final Map<Position, HantoTile> gameBoard = new HashMap<>();
	private final HexGraph gameGraph = new HexGraph();
	private boolean gameOver = false;
	/**
	 * Don't let this class be created from outside (not that it can anyways)
	 */
	protected GameState()
	{}
	
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
	 * @param color the color of the piece
	 * @return if there a move is possible
	 */
	public abstract boolean isMovePossible(Position from, Position to, HantoPieceType type, HantoPlayerColor color);
	
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
		gameBoard.put(asPosition(location), piece);
		gameGraph.insertNodeAt(asPosition(location));
	}
	
	/**
	 * remove a piece from the board and return it
	 * @param location the location to return from
	 * @return the piece removed from that lcoation
	 */
	public HantoTile removePieceFrom(HantoCoordinate location)
	{
		gameGraph.removeNodeAt(asPosition(location));
		return gameBoard.remove(asPosition(location));
	}
	
	/**
	 * 
	 * @return the set of all occupied locations on the board
	 */
	public Set<Position> filledLocations()
	{
		return gameBoard.keySet();
	}
	
	/**
	 * checks a graph to make sure it is continuous
	 * @param from the position from which the piece is moving
	 * @param to the position to which the piece is moving
	 * @return whether the graph is still continuous without that piece
	 */
	public boolean isGraphContinuityPreservedAfter(Position from, Position to)
	{
		return gameGraph.isContinuousAfter(from, to);
	}
	
	/**
	 * Marks the game complete
	 */
	public void gameOver()
	{
		gameOver = true;
	}
	
	/**
	 * Returns whether the game is over or not.
	 * @return whether the game is over or not.
	 */
	public boolean isGameOver()
	{
		return gameOver;
	}
}
