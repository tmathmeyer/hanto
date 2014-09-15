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

public class AlphaGameStateTests {
	private GameState freshGame;
	private GameState unfreshGame;

	@Before
	public void init() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		freshGame = AlphaHantoGame.defaultAGS();
		unfreshGame = AlphaHantoGame.defaultAGS();
		
		Field f = unfreshGame.getClass().getDeclaredField("which");
		f.setAccessible(true);
		f.setBoolean(unfreshGame, false);
	}

	@Test
	public void firstPlayerGoesFirst() {
		assertEquals(freshGame.getCurrentPlayer().getColor(),
				HantoPlayerColor.BLUE);
	}
	
	@Test
	public void secondPlayerGoesSecond() {
		assertEquals(unfreshGame.getCurrentPlayer().getColor(),
				HantoPlayerColor.RED);
	}
	
	
	

	@Test
	public void isFirstMovePossible() {
		assertTrue(freshGame.isMovePossible(new InventoryPosition(),
				new HexPosition(0, 0), HantoPieceType.BUTTERFLY));
	}
	
	@Test
	public void isSecondMovePossible() {
		assertTrue(unfreshGame.isMovePossible(new InventoryPosition(),
				new HexPosition(1, 0), HantoPieceType.BUTTERFLY));
	}
	
	@Test
	public void isFirstStandardMovePossible() {
		assertTrue(freshGame.isMovePossible(null,
				new HexPosition(0, 0), HantoPieceType.BUTTERFLY));
	}
	
	@Test
	public void isSecondStandardMovePossible() {
		assertTrue(unfreshGame.isMovePossible(null,
				new HexPosition(1, 0), HantoPieceType.BUTTERFLY));
	}

	
	
	
	@Test
	public void failOnBadFirstMove() {
		assertFalse(freshGame.isMovePossible(new InventoryPosition(),
				new HexPosition(1, 0), HantoPieceType.BUTTERFLY));
	}
	
	@Test
	public void failOnBadSecondMove() {
		assertFalse(unfreshGame.isMovePossible(new InventoryPosition(),
				new HexPosition(0, 0), HantoPieceType.BUTTERFLY));
	}
	
	@Test
	public void failOnBadStandardFirstMove() {
		assertFalse(freshGame.isMovePossible(null,
				new HexPosition(1, 0), HantoPieceType.BUTTERFLY));
	}
	
	@Test
	public void failOnBadStandardSecondMove() {
		assertFalse(unfreshGame.isMovePossible(null,
				new HexPosition(0, 0), HantoPieceType.BUTTERFLY));
	}
	
	
	

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
	
	
	
	

	@Test(expected=HantoException.class)
	public void doInvalidFirstMove()  throws HantoException{
			freshGame.makeMove(HantoPieceType.BUTTERFLY, new InventoryPosition(), new HexPosition(1, 0));
	}
	
	@Test(expected=HantoException.class)
	public void doInvalidSecondMove() throws HantoException{
			freshGame.makeMove(HantoPieceType.BUTTERFLY, new InventoryPosition(), new HexPosition(0, 0));
	}
	
	@Test(expected=HantoException.class)
	public void doInvalidStandardFirstMove() throws HantoException{
			freshGame.makeMove(HantoPieceType.BUTTERFLY, null, new HexPosition(1, 0));
	}
	
	@Test(expected=HantoException.class)
	public void doInvalidStandardSecondMove() throws HantoException{
			freshGame.makeMove(HantoPieceType.BUTTERFLY, null, new HexPosition(0, 0));
	}
	
	
	
	
	@Test(expected=HantoException.class)
	public void veryInvalidMove() throws HantoException
	{
		AlphaHantoGame.defaultAGS().makeMove(null, null, null);
	}
}
