/*******************************************************************************
 * All sources under the hanto.otnah package were developed by
 * Ted Meyer and Nilesh Patel for the term project in CS4233
 * at Worcester Polytechnic Institute. Since WPI holds all other forms
 * of ownership to this software, we have decided to not make this
 * software available under any license. Evaluation or compilation rights
 * are therefore granted only to course staff.
 *******************************************************************************/

package hanto.otnah.beta;

import static org.junit.Assert.*;
import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentotnah.beta.BetaHantoGame;
import hanto.studentotnah.beta.BetaHantoRules;
import hanto.studentotnah.common.HantoPlayer;
import hanto.studentotnah.common.HantoTile;
import hanto.studentotnah.common.HexPosition;
import hanto.studentotnah.common.InventoryPosition;

import org.junit.Test;

/**
 * 
 * @author otnah
 *
 */
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
	
	/**
	 * make sure Beta isn't null
	 * @throws HantoException on failure
	 */
	@Test
	public void canStartTest() throws HantoException
	{
		assertNotNull(BetaHantoGame.createBetaGameState(HantoPlayerColor.RED));
		assertNotNull(BetaHantoGame.createBetaGameState(HantoPlayerColor.BLUE));
	}

	/**
	 * fail on lack of color
	 * @throws HantoException on failure
	 */
	@Test(expected=HantoException.class)
	public void needsValidPlayerColor() throws HantoException
	{
		BetaHantoGame.createBetaGameState(null);
	}
	
	/**
	 * check inventories
	 * @throws HantoException on failure
	 */
	@Test
	public void hasProperStartingInventoryTest() throws HantoException
	{
		BetaHantoGame beta = BetaHantoGame.createBetaGameState(HantoPlayerColor.BLUE);
		
		int[] inventoryCounts = inventoryCounts(beta.getCurrentPlayer());

		assertEquals(inventoryCounts[0], 1);
		assertEquals(inventoryCounts[1], 5);
		assertEquals(inventoryCounts[2], 0);
	}
	
	/**
	 * make sure teh first player is first
	 * @throws HantoException
	 */
	@Test
	public void firstPlayerPlaysFirst() throws HantoException
	{
		BetaHantoGame beta = BetaHantoGame.createBetaGameState(HantoPlayerColor.BLUE);
		HantoPlayer first = beta.getCurrentPlayer();
		assertEquals(beta.makeMove(HantoPieceType.BUTTERFLY, new InventoryPosition(), new HexPosition(0, 0)), MoveResult.OK);
		assertEquals(inventoryCounts(first)[0], 0);
	}
	
	/**
	 * make sure a move actually happens on move
	 * @throws HantoException
	 */
	@Test
	public void pieceMoveHappens() throws HantoException
	{
		BetaHantoGame beta = BetaHantoGame.createBetaGameState(HantoPlayerColor.BLUE);
		beta.makeMove(HantoPieceType.BUTTERFLY, new InventoryPosition(), new HexPosition(0, 0));
		assertEquals(beta.getPieceAt(new HexPosition(0, 0)).getType(), HantoPieceType.BUTTERFLY);
		assertEquals(beta.getPieceAt(new HexPosition(0, 0)).getColor(), HantoPlayerColor.BLUE);
	}
	
	/**
	 * make sure a move happens for red too
	 * @throws HantoException
	 */
	@Test
	public void pieceMoveHappensRed() throws HantoException
	{
		BetaHantoGame beta = BetaHantoGame.createBetaGameState(HantoPlayerColor.RED);
		beta.makeMove(HantoPieceType.SPARROW, new InventoryPosition(), new HexPosition(0, 0));
		assertEquals(beta.getPieceAt(new HexPosition(0, 0)).getType(), HantoPieceType.SPARROW);
		assertEquals(beta.getPieceAt(new HexPosition(0, 0)).getColor(), HantoPlayerColor.RED);
	}
	
	/**
	 * make sure that you cant play disconnected pieces
	 * @throws HantoException
	 */
	@Test(expected=HantoException.class)
	public void disconnectedPieceFail() throws HantoException
	{
		BetaHantoGame beta = BetaHantoGame.createBetaGameState(HantoPlayerColor.BLUE);
		beta.makeMove(HantoPieceType.BUTTERFLY, new InventoryPosition(), new HexPosition(0, 0));
		beta.makeMove(HantoPieceType.SPARROW, new InventoryPosition(), new HexPosition(1, 1));
	}
	
	/**
	 * make sure a player cant play more of any tile than they have
	 * @throws HantoException
	 */
	@Test(expected=HantoException.class)
	public void doublePlayButterfly() throws HantoException
	{
		BetaHantoGame beta = BetaHantoGame.createBetaGameState(HantoPlayerColor.BLUE);
		assertEquals(beta.makeMove(HantoPieceType.BUTTERFLY, new InventoryPosition(), new HexPosition(0, 0)), MoveResult.OK);
		assertEquals(beta.makeMove(HantoPieceType.BUTTERFLY, new InventoryPosition(), new HexPosition(0, 1)), MoveResult.OK);
		beta.makeMove(HantoPieceType.BUTTERFLY, new InventoryPosition(), new HexPosition(1, 1));
	}
	
	/**
	 * make sure that two plays cant be to the same location
	 * @throws HantoException
	 */
	@Test(expected=HantoException.class)
	public void playOnSameLocation() throws HantoException
	{
		BetaHantoGame beta = BetaHantoGame.createBetaGameState(HantoPlayerColor.BLUE);
		assertEquals(beta.makeMove(HantoPieceType.BUTTERFLY, new InventoryPosition(), new HexPosition(0, 0)), MoveResult.OK);
		assertEquals(beta.makeMove(HantoPieceType.BUTTERFLY, new InventoryPosition(), new HexPosition(0, 1)), MoveResult.OK);
		beta.makeMove(HantoPieceType.SPARROW, new InventoryPosition(), new HexPosition(0, 0));
	}
	
	/**
	 * play four correct moves and make sure they works
	 * @throws HantoException
	 */
	@Test
	public void playFourMoves() throws HantoException
	{
		BetaHantoGame beta = BetaHantoGame.createBetaGameState(HantoPlayerColor.BLUE);
		beta.makeMove(HantoPieceType.BUTTERFLY, new InventoryPosition(), new HexPosition(0, 0));
		beta.makeMove(HantoPieceType.BUTTERFLY, new InventoryPosition(), new HexPosition(1, 0));
		assertEquals(beta.makeMove(HantoPieceType.SPARROW, new InventoryPosition(), new HexPosition(0, 1)), MoveResult.OK);
		assertEquals(beta.makeMove(HantoPieceType.SPARROW, new InventoryPosition(), new HexPosition(1, 1)), MoveResult.OK);
		assertEquals(beta.makeMove(HantoPieceType.SPARROW, new InventoryPosition(), new HexPosition(2, 1)), MoveResult.OK);
		assertEquals(beta.makeMove(HantoPieceType.SPARROW, new InventoryPosition(), new HexPosition(2, 2)), MoveResult.OK);
	}
	
	/**
	 * make sure that a butterfly is forced to play on the fourth move
	 * @throws HantoException
	 */
	@Test
	public void forcePlayButterflyOnFourthMove() throws HantoException
	{
		BetaHantoGame beta = BetaHantoGame.createBetaGameState(HantoPlayerColor.BLUE);
		//move 1
		assertEquals(beta.makeMove(HantoPieceType.SPARROW, new InventoryPosition(), new HexPosition(0, 0)), MoveResult.OK);
		assertEquals(beta.makeMove(HantoPieceType.SPARROW, new InventoryPosition(), new HexPosition(0, 1)), MoveResult.OK);
		
		//move 2
		assertEquals(beta.makeMove(HantoPieceType.SPARROW, new InventoryPosition(), new HexPosition(1, 1)), MoveResult.OK);
		assertEquals(beta.makeMove(HantoPieceType.SPARROW, new InventoryPosition(), new HexPosition(1, 0)), MoveResult.OK);
		
		//move 3
		assertEquals(beta.makeMove(HantoPieceType.SPARROW, new InventoryPosition(), new HexPosition(2, 1)), MoveResult.OK);
		assertEquals(beta.makeMove(HantoPieceType.SPARROW, new InventoryPosition(), new HexPosition(2, 0)), MoveResult.OK);
		
		//move 4
		try {
			beta.makeMove(HantoPieceType.SPARROW, new InventoryPosition(), new HexPosition(2, 2));
			fail();
		}
		catch(HantoException e)
		{
			assertEquals(beta.makeMove(HantoPieceType.BUTTERFLY, new InventoryPosition(), new HexPosition(2, 2)), MoveResult.OK);
		}
	}
	
	/**
	 * make sure that the game ends in a draw when it is supposed to
	 * @throws HantoException
	 */
	@Test
	public void gameEndsInDraw() throws HantoException
	{
		BetaHantoGame beta = BetaHantoGame.createBetaGameState(HantoPlayerColor.BLUE);
		//move 1
		assertEquals(beta.makeMove(HantoPieceType.SPARROW, new InventoryPosition(), new HexPosition(0, 0)), MoveResult.OK);
		assertEquals(beta.makeMove(HantoPieceType.SPARROW, new InventoryPosition(), new HexPosition(0, 1)), MoveResult.OK);
		
		//move 2
		assertEquals(beta.makeMove(HantoPieceType.SPARROW, new InventoryPosition(), new HexPosition(1, 1)), MoveResult.OK);
		assertEquals(beta.makeMove(HantoPieceType.SPARROW, new InventoryPosition(), new HexPosition(1, 0)), MoveResult.OK);
		
		//move 3
		assertEquals(beta.makeMove(HantoPieceType.SPARROW, new InventoryPosition(), new HexPosition(2, 1)), MoveResult.OK);
		assertEquals(beta.makeMove(HantoPieceType.SPARROW, new InventoryPosition(), new HexPosition(2, 0)), MoveResult.OK);
		
		//move 4
		assertEquals(beta.makeMove(HantoPieceType.BUTTERFLY, new InventoryPosition(), new HexPosition(0, -1)), MoveResult.OK);
		assertEquals(beta.makeMove(HantoPieceType.BUTTERFLY, new InventoryPosition(), new HexPosition(-1, 0)), MoveResult.OK);
		
		//move 5
		assertEquals(beta.makeMove(HantoPieceType.SPARROW, new InventoryPosition(), new HexPosition(0, -2)), MoveResult.OK);
		assertEquals(beta.makeMove(HantoPieceType.SPARROW, new InventoryPosition(), new HexPosition(0, 2)), MoveResult.OK);
		
		//move 6
		assertEquals(beta.makeMove(HantoPieceType.SPARROW, new InventoryPosition(), new HexPosition(-1, 1)), MoveResult.OK);
		assertEquals(beta.makeMove(HantoPieceType.SPARROW, new InventoryPosition(), new HexPosition(2, -1)), MoveResult.DRAW);

	}
	
	/**
	 * make sure a red win is a red win
	 * @throws HantoException
	 */
	@Test
	public void gameEndsInRedWin() throws HantoException
	{
		BetaHantoGame beta = BetaHantoGame.createBetaGameState(HantoPlayerColor.BLUE);
		//move 1
		assertEquals(beta.makeMove(HantoPieceType.BUTTERFLY, new InventoryPosition(), new HexPosition(0, 0)), MoveResult.OK);
		assertEquals(beta.makeMove(HantoPieceType.SPARROW, new InventoryPosition(), new HexPosition(0, 1)), MoveResult.OK);
		
		//move 2
		assertEquals(beta.makeMove(HantoPieceType.SPARROW, new InventoryPosition(), new HexPosition(1, 0)), MoveResult.OK);
		assertEquals(beta.makeMove(HantoPieceType.SPARROW, new InventoryPosition(), new HexPosition(1, -1)), MoveResult.OK);
		
		//move 3
		assertEquals(beta.makeMove(HantoPieceType.SPARROW, new InventoryPosition(), new HexPosition(0, -1)), MoveResult.OK);
		assertEquals(beta.makeMove(HantoPieceType.SPARROW, new InventoryPosition(), new HexPosition(-1, 0)), MoveResult.OK);
		
		//move 4
		assertEquals(beta.makeMove(HantoPieceType.SPARROW, new InventoryPosition(), new HexPosition(-1, 1)), MoveResult.RED_WINS);
	}
	
	/**
	 * make sure a blue win is a blue win
	 * @throws HantoException
	 */
	@Test
	public void gameEndsInBlueWin() throws HantoException
	{
		BetaHantoGame beta = BetaHantoGame.createBetaGameState(HantoPlayerColor.BLUE);
		//move 1
		assertEquals(beta.makeMove(HantoPieceType.SPARROW, new InventoryPosition(), new HexPosition(0, 0)), MoveResult.OK);
		assertEquals(beta.makeMove(HantoPieceType.BUTTERFLY, new InventoryPosition(), new HexPosition(0, 1)), MoveResult.OK);
		
		//move 2
		assertEquals(beta.makeMove(HantoPieceType.SPARROW, new InventoryPosition(), new HexPosition(-1, 1)), MoveResult.OK);
		assertEquals(beta.makeMove(HantoPieceType.SPARROW, new InventoryPosition(), new HexPosition(-1, 2)), MoveResult.OK);
		
		//move 3
		assertEquals(beta.makeMove(HantoPieceType.SPARROW, new InventoryPosition(), new HexPosition(0, 2)), MoveResult.OK);
		assertEquals(beta.makeMove(HantoPieceType.SPARROW, new InventoryPosition(), new HexPosition(1, 1)), MoveResult.OK);
		
		//move 4
		assertEquals(beta.makeMove(HantoPieceType.BUTTERFLY, new InventoryPosition(), new HexPosition(1, 0)), MoveResult.BLUE_WINS);
	}
	
	/**
	 * get coverage on the stupid things
	 */
	@Test
	public void unusedFactoryTests()
	{
		assertTrue(new BetaHantoRules().makeCrabMoveValidator() instanceof BetaHantoRules);
		
		assertTrue(new BetaHantoRules().makeHorseMoveValidator() instanceof BetaHantoRules);
	}
	
	/**
	 * make sure that two plays cant be to the same location
	 * @throws HantoException
	 */
	@Test(expected=HantoException.class)
	public void attemptResign() throws HantoException
	{
		BetaHantoGame beta = BetaHantoGame.createBetaGameState(HantoPlayerColor.BLUE);
		beta.tryResignation();
	}
}
