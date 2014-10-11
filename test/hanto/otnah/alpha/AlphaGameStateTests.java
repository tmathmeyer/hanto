/*******************************************************************************
 * All sources under the hanto.otnah package were developed by
 * Ted Meyer and Nilesh Patel for the term project in CS4233
 * at Worcester Polytechnic Institute. Since WPI holds all other forms
 * of ownership to this software, we have decided to not make this
 * software available under any license. Evaluation or compilation rights
 * are therefore granted only to course staff.
 *******************************************************************************/

package hanto.otnah.alpha;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;

import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.otnah.common.GameState;
import hanto.otnah.common.InventoryPosition;
import hanto.otnah.common.HexPosition;

/**
 * 
 * @author otnah
 *
 */
public class AlphaGameStateTests {
	private GameState freshGame;
	private GameState unfreshGame;

	/**
	 * setup for tests
	 * @throws SecurityException reflection issue
	 * @throws NoSuchFieldException reflection issue
	 * @throws IllegalArgumentException reflection issue
	 * @throws IllegalAccessException reflection issue
	 */
	@Before
	public void init() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		freshGame = new AlphaHantoGame();
		unfreshGame = new AlphaHantoGame();
		
		Field f = unfreshGame.getClass().getDeclaredField("which");
		f.setAccessible(true);
		f.setBoolean(unfreshGame, false);
	}

	/**
	 * test that Blue goes first
	 */
	@Test
	public void firstPlayerGoesFirst() {
		assertEquals(freshGame.getCurrentPlayer().getColor(),
				HantoPlayerColor.BLUE);
	}
	
	/**
	 * test that Red goes second
	 */
	@Test
	public void secondPlayerGoesSecond() {
		assertEquals(unfreshGame.getCurrentPlayer().getColor(),
				HantoPlayerColor.RED);
	}
	
	
	

	/**
	 * test whether the first move that must be played is in fact a legal move
	 */
	@Test
	public void isFirstMovePossible() {
		assertTrue(freshGame.isMovePossible(new InventoryPosition(),
				new HexPosition(0, 0), HantoPieceType.BUTTERFLY, null));
	}
	
	/**
	 * test whether the second move that must be played is legal
	 */
	@Test
	public void isSecondMovePossible() {
		assertTrue(unfreshGame.isMovePossible(new InventoryPosition(),
				new HexPosition(1, 0), HantoPieceType.BUTTERFLY, null));
	}
	
	/**
	 * test whether the first move is legal, using "standard" args
	 */
	@Test
	public void isFirstStandardMovePossible() {
		assertTrue(freshGame.isMovePossible(null,
				new HexPosition(0, 0), HantoPieceType.BUTTERFLY, null));
	}
	
	/**
	 * test whether the second move is legal, using "standard args"
	 */
	@Test
	public void isSecondStandardMovePossible() {
		assertTrue(unfreshGame.isMovePossible(null,
				new HexPosition(1, 0), HantoPieceType.BUTTERFLY, null));
	}

	
	
	/**
	 * test that a bad move fails
	 */
	@Test
	public void failOnBadFirstMove() {
		assertFalse(freshGame.isMovePossible(new InventoryPosition(),
				new HexPosition(1, 0), HantoPieceType.BUTTERFLY, null));
	}
	
	/**
	 * test that a bad move fails
	 */
	@Test
	public void failOnBadSecondMove() {
		assertFalse(unfreshGame.isMovePossible(new InventoryPosition(),
				new HexPosition(0, 0), HantoPieceType.BUTTERFLY, null));
	}
	
	/**
	 * test that a "standard" bad move fails
	 */
	@Test
	public void failOnBadStandardFirstMove() {
		assertFalse(freshGame.isMovePossible(null,
				new HexPosition(1, 0), HantoPieceType.BUTTERFLY, null));
	}
	
	/**
	 * test that a "standard" bad move fails
	 */
	@Test
	public void failOnBadStandardSecondMove() {
		assertFalse(unfreshGame.isMovePossible(null,
				new HexPosition(0, 0), HantoPieceType.BUTTERFLY, null));
	}
	
	
	
	/**
	 * make a first move, and succede
	 */
	@Test
	public void doFirstMove() {
		try {
			assertEquals(MoveResult.OK, freshGame.makeMove(
					HantoPieceType.BUTTERFLY, new InventoryPosition(),
					new HexPosition(0, 0)));
		} catch (HantoException e) {
			assertTrue(false);
		}
	}
	
	/**
	 * make a first "standard" move, and succede
	 */
	@Test
	public void doStandardFirstMove() {
		try {
			assertEquals(MoveResult.OK, freshGame.makeMove(
					HantoPieceType.BUTTERFLY, null,
					new HexPosition(0, 0)));
		} catch (HantoException e) {
			assertTrue(false);
		}
	}
	
	/**
	 * make second move, and succede
	 */
	@Test
	public void doSecondMove() {
		try {
			assertEquals(MoveResult.DRAW, unfreshGame.makeMove(
					HantoPieceType.BUTTERFLY, new InventoryPosition(),
					new HexPosition(1, 0)));
		} catch (HantoException e) {
			assertTrue(false);
		}
	}
	
	/**
	 * make second "standard" move, and succede
	 */
	@Test
	public void doStandardSecondMove() {
		try {
			assertEquals(MoveResult.DRAW, unfreshGame.makeMove(
					HantoPieceType.BUTTERFLY, null,
					new HexPosition(1, 0)));
		} catch (HantoException e) {
			assertTrue(false);
		}
	}
	
	
	
	
	/**
	 * make invalid move, and fail
	 * @throws HantoException on failure
	 */
	@Test(expected=HantoException.class)
	public void doInvalidFirstMove()  throws HantoException{
			freshGame.makeMove(HantoPieceType.BUTTERFLY, new InventoryPosition(), new HexPosition(1, 0));
	}
	
	/**
	 * make invalid move, and fail
	 * @throws HantoException on failure
	 */
	@Test(expected=HantoException.class)
	public void doInvalidSecondMove() throws HantoException{
			unfreshGame.makeMove(HantoPieceType.BUTTERFLY, new InventoryPosition(), new HexPosition(0, 0));
	}
	
	/**
	 * make invalid move and fail
	 * @throws HantoException on failure
	 */
	@Test(expected=HantoException.class)
	public void doInvalidStandardFirstMove() throws HantoException{
			freshGame.makeMove(HantoPieceType.BUTTERFLY, null, new HexPosition(1, 0));
	}
	
	/**
	 * make invalid move and fail
	 * @throws HantoException on failure
	 */
	@Test(expected=HantoException.class)
	public void doInvalidStandardSecondMove() throws HantoException{
			unfreshGame.makeMove(HantoPieceType.BUTTERFLY, null, new HexPosition(0, 0));
	}
	
	
	
	/**
	 * test that a very bad move can't happen
	 * @throws HantoException on failure
	 */
	@Test(expected=HantoException.class)
	public void veryInvalidMove() throws HantoException
	{
		new AlphaHantoGame().makeMove(null, null, null);
	}
	
	/**
	 * test printable boards
	 */
	@Test
	public void testPrintableBoardIsEmpty()
	{
		assertEquals("", freshGame.getPrintableBoard());
		assertEquals("", unfreshGame.getPrintableBoard());
	}
}
