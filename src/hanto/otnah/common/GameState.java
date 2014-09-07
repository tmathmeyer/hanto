package hanto.otnah.common;

import java.util.HashMap;
import java.util.Map;

import hanto.common.HantoCoordinate;
import hanto.common.HantoGame;
import hanto.common.HantoPieceType;

/**
 * 
 * @author otnah
 *
 */
public abstract class GameState implements HantoGame
{
	private final Map<HantoCoordinate, HantoTile> gameBoard = new HashMap<>();
	
	protected GameState()
	{
		new GameStateSingleton(this);
	}
	
	/**
	 * get the player who's turn it is, IE, that they have not yet made a move
	 * @return the current player
	 */
	public abstract HantoPlayer getCurrentPlayer();
	
	/**
	 * 
	 * @param from the place the piece is moving from (null in the case of 
	 * @param to
	 * @param type
	 * @return
	 */
	public abstract boolean isMovePossible(HantoCoordinate from, HantoCoordinate to, HantoPieceType type);
	
	@Override
	public String getPrintableBoard()
	{
		StringBuilder builder = new StringBuilder();
		for(HantoTile tile : gameBoard.values())
		{
			builder.append(tile.toString());
		}
		
		return builder.toString();
	}
}
