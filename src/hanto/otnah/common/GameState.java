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
import hanto.otnah.common.pieces.moves.PieceMoveValidatorFactory;
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
	
	@Override
	public HantoPiece getPieceAt(HantoCoordinate where)
	{
		return gameBoard.get(Position.asPosition(where));
	}
	
	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate fromHC, HantoCoordinate toHC) throws HantoException
	{
		if (checkResignation(pieceType, fromHC, toHC))
		{
			return tryResignation();
		}
		if(isGameOver())
		{
			throw new HantoException("Game is over, no moves are allowed.");
		}
		
		Position   to = Position.asPosition(toHC);
		Position from = Position.asPosition(fromHC);
		
		if(isMovePossible(from, to, pieceType, getCurrentPlayer().getColor()))
		{
			//remove piece from inventory
			HantoTile played = from.removePieceAt(this, pieceType);
			
			//put piece on board
			setPieceAt(played, to);
			
			//increment player count
			getCurrentPlayer().increaseMoveCount();
			
			if(pieceType == HantoPieceType.BUTTERFLY)
			{
				getCurrentPlayer().getSelf().setButterflyPosition(to);
			}
			
			//switch player
			skipTo(getCurrentPlayer().getNextPlayer().getColor());
			return gameState();
		}
		
		throw new HantoException("invalid move!");
	}
	
	
	
	/**
	 * get the player who's turn it is, IE, that they have not yet made a move
	 * @return the current player
	 */
	public abstract HantoPlayer<LinkedHantoPlayer> getCurrentPlayer();
	
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
	
	/**
	 * a method to abstract away the movement limit to child classes
	 * @return whether this game has had the max number of moves
	 */
	public abstract boolean isOverMaxAllottedMoves(int currentMoveCount);
	
	/**
	 * @return the move validator that this game uses
	 * @throws HantoException if something goes wrong
	 */
	public abstract PieceMoveValidatorFactory getValidatorFactory() throws HantoException;
	
	/**
	 * sets this players current move
	 * @param player the color of the player to skip to
	 */
	public abstract void skipTo(HantoPlayerColor player);
	
	/**
	 * 
	 * @return return the resigning players losing state
	 * @throws HantoException if the resignation is invalid
	 */
	public abstract MoveResult tryResignation() throws HantoException;
	
	/**
	 * @param a something
	 * @param b something
	 * @param c something
	 * @return whether they are all null
	 */
	public boolean checkResignation(Object a, Object b, Object c)
	{
		return a == null && b == null && c == null;
	}
	
	
	
	/**
	 * Gets the current state of the game (OK, DRAW, WINNER)
	 * @return the current state of the game
	 */
	public MoveResult gameState()
	{
		MoveResult returnValue = checkSurrounded();
		if (isOverMaxAllottedMoves(getCurrentPlayer().getSelf().getTotalMoves()))
		{
			returnValue = MoveResult.DRAW;
		}
		return returnValue;
	}
	
	private MoveResult checkSurrounded()
	{
		MoveResult result = MoveResult.OK;
		for(LinkedHantoPlayer player : getCurrentPlayer().getSelf().collectAllUsers())
		{
			if (player.isSurrounded(this))
			{
				gameOver();
				if (result != MoveResult.OK)
				{
					result = MoveResult.DRAW;
				}
				else
				{
					result = player.getLosingState();
				}
			}
		}
		return result;
	}
}
