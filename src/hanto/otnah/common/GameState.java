package hanto.otnah.common;

import java.util.HashMap;
import java.util.Map;

import hanto.common.HantoCoordinate;
import hanto.common.HantoGame;
import hanto.common.HantoPieceType;

public abstract class GameState implements HantoGame
{
	private final Map<HantoCoordinate, HantoTile> gameBoard = new HashMap<>();
	
	public abstract HantoPlayer getCurrentPlayer();
	
	public abstract boolean isMovePossible(HantoCoordinate from, HantoCoordinate to, HantoPieceType type);
	
	public String getPrintableBoard()
	{
		for(HantoTile tile : gameBoard.getValues())
		{
			// meh
		}
		
		return "";
	}
}
