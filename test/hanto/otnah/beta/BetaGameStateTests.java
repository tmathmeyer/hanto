package hanto.otnah.beta;

import static org.junit.Assert.*;
import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.otnah.common.HantoPlayer;
import hanto.otnah.common.HantoTile;
import hanto.otnah.common.InventoryPosition;

import org.junit.Test;

public class BetaGameStateTests
{
	private int[] inventoryCounts(HantoPlayer p)
	{
		int[] result = new int[3];
		for(HantoTile t : p.getInventory())
		{
			switch(t.getType())
			{
				case BUTTERFLY:
					result[0] ++;
					break;
				case SPARROW:
					result[1] ++;
					break;
				default:
					result[2] ++;
					break;
			}
		}
		return result;
	}
	
	@Test
	public void canStartTest() throws HantoException
	{
		assertNotNull(BetaGameState.createBetaGameState(HantoPlayerColor.RED));
		assertNotNull(BetaGameState.createBetaGameState(HantoPlayerColor.BLUE));
	}

	@Test(expected=HantoException.class)
	public void needsValidPlayerColor() throws HantoException
	{
		BetaGameState.createBetaGameState(null);
	}
	
	@Test
	public void hasProperStartingInventoryTest() throws HantoException
	{
		BetaGameState beta = BetaGameState.createBetaGameState(HantoPlayerColor.BLUE);
		
		int[] inventoryCounts = inventoryCounts(beta.getCurrentPlayer());

		assertEquals(inventoryCounts[0], 1);
		assertEquals(inventoryCounts[1], 5);
		assertEquals(inventoryCounts[2], 0);
	}
	
	@Test
	public void firstPlayerPlaysFirst() throws HantoException
	{
		BetaGameState beta = BetaGameState.createBetaGameState(HantoPlayerColor.BLUE);
		HantoPlayer first = beta.getCurrentPlayer();
		assertEquals(beta.makeMove(HantoPieceType.BUTTERFLY, new InventoryPosition(), new BetaPosition(0, 0)), MoveResult.OK);
		assertEquals(inventoryCounts(first)[0], 0);
	}
	
	@Test
	public void pieceMoveHappens() throws HantoException
	{
		BetaGameState beta = BetaGameState.createBetaGameState(HantoPlayerColor.BLUE);
		beta.makeMove(HantoPieceType.BUTTERFLY, new InventoryPosition(), new BetaPosition(0, 0));
		assertEquals(beta.getPieceAt(new BetaPosition(0, 0)).getType(), HantoPieceType.BUTTERFLY);
	}
	
	@Test(expected=HantoException.class)
	public void disconnectedPieceFail() throws HantoException
	{
		BetaGameState beta = BetaGameState.createBetaGameState(HantoPlayerColor.BLUE);
		beta.makeMove(HantoPieceType.BUTTERFLY, new InventoryPosition(), new BetaPosition(0, 0));
		beta.makeMove(HantoPieceType.SPARROW, new InventoryPosition(), new BetaPosition(1, 1));
	}
	
	
	
}
