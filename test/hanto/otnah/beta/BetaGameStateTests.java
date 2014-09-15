package hanto.otnah.beta;

import static org.junit.Assert.*;
import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.otnah.common.HantoPlayer;
import hanto.otnah.common.HantoTile;
import hanto.otnah.common.InventoryPosition;
import hanto.otnah.common.HexPosition;
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
		assertNotNull(BetaHantoGame.createBetaGameState(HantoPlayerColor.RED));
		assertNotNull(BetaHantoGame.createBetaGameState(HantoPlayerColor.BLUE));
	}

	@Test(expected=HantoException.class)
	public void needsValidPlayerColor() throws HantoException
	{
		BetaHantoGame.createBetaGameState(null);
	}
	
	@Test
	public void hasProperStartingInventoryTest() throws HantoException
	{
		BetaHantoGame beta = BetaHantoGame.createBetaGameState(HantoPlayerColor.BLUE);
		
		int[] inventoryCounts = inventoryCounts(beta.getCurrentPlayer());

		assertEquals(inventoryCounts[0], 1);
		assertEquals(inventoryCounts[1], 5);
		assertEquals(inventoryCounts[2], 0);
	}
	
	@Test
	public void firstPlayerPlaysFirst() throws HantoException
	{
		BetaHantoGame beta = BetaHantoGame.createBetaGameState(HantoPlayerColor.BLUE);
		HantoPlayer first = beta.getCurrentPlayer();
		assertEquals(beta.makeMove(HantoPieceType.BUTTERFLY, new InventoryPosition(), new HexPosition(0, 0)), MoveResult.OK);
		assertEquals(inventoryCounts(first)[0], 0);
	}
	
	@Test
	public void pieceMoveHappens() throws HantoException
	{
		BetaHantoGame beta = BetaHantoGame.createBetaGameState(HantoPlayerColor.BLUE);
		beta.makeMove(HantoPieceType.BUTTERFLY, new InventoryPosition(), new HexPosition(0, 0));
		assertEquals(beta.getPieceAt(new HexPosition(0, 0)).getType(), HantoPieceType.BUTTERFLY);
	}
	
	@Test(expected=HantoException.class)
	public void disconnectedPieceFail() throws HantoException
	{
		BetaHantoGame beta = BetaHantoGame.createBetaGameState(HantoPlayerColor.BLUE);
		beta.makeMove(HantoPieceType.BUTTERFLY, new InventoryPosition(), new HexPosition(0, 0));
		beta.makeMove(HantoPieceType.SPARROW, new InventoryPosition(), new HexPosition(1, 1));
	}
	
	@Test(expected=HantoException.class)
	public void doublePlayButterfly() throws HantoException
	{
		BetaHantoGame beta = BetaHantoGame.createBetaGameState(HantoPlayerColor.BLUE);
		assertEquals(beta.makeMove(HantoPieceType.BUTTERFLY, new InventoryPosition(), new HexPosition(0, 0)), MoveResult.OK);
		assertEquals(beta.makeMove(HantoPieceType.BUTTERFLY, new InventoryPosition(), new HexPosition(0, 1)), MoveResult.OK);
		beta.makeMove(HantoPieceType.BUTTERFLY, new InventoryPosition(), new HexPosition(1, 1));
	}
	
	@Test(expected=HantoException.class)
	public void playOnSameLocation() throws HantoException
	{
		BetaHantoGame beta = BetaHantoGame.createBetaGameState(HantoPlayerColor.BLUE);
		assertEquals(beta.makeMove(HantoPieceType.BUTTERFLY, new InventoryPosition(), new HexPosition(0, 0)), MoveResult.OK);
		assertEquals(beta.makeMove(HantoPieceType.BUTTERFLY, new InventoryPosition(), new HexPosition(0, 1)), MoveResult.OK);
		beta.makeMove(HantoPieceType.SPARROW, new InventoryPosition(), new HexPosition(0, 0));
	}
	
	@Test
	public void playFourMoves() throws HantoException
	{
		BetaHantoGame beta = BetaHantoGame.createBetaGameState(HantoPlayerColor.BLUE);
		beta.makeMove(HantoPieceType.BUTTERFLY, new InventoryPosition(), new HexPosition(0, 0));
		beta.makeMove(HantoPieceType.BUTTERFLY, new InventoryPosition(), new HexPosition(1, 1));
		assertEquals(beta.makeMove(HantoPieceType.BUTTERFLY, new InventoryPosition(), new HexPosition(0, 0)), MoveResult.OK);
		assertEquals(beta.makeMove(HantoPieceType.BUTTERFLY, new InventoryPosition(), new HexPosition(0, 1)), MoveResult.OK);
		assertEquals(beta.makeMove(HantoPieceType.SPARROW, new InventoryPosition(), new HexPosition(1, 1)), MoveResult.OK);
		assertEquals(beta.makeMove(HantoPieceType.SPARROW, new InventoryPosition(), new HexPosition(1, 0)), MoveResult.OK);
	}
	
	
	public void forcePlayButterflyOnFourthMove()
	{
		
	}
}
