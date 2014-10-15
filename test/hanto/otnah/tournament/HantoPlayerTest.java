/*******************************************************************************
 * All sources under the hanto.otnah package were developed by
 * Ted Meyer and Nilesh Patel for the term project in CS4233
 * at Worcester Polytechnic Institute. Since WPI holds all other forms
 * of ownership to this software, we have decided to not make this
 * software available under any license. Evaluation or compilation rights
 * are therefore granted only to course staff.
 *******************************************************************************/

package hanto.otnah.tournament;

import static org.junit.Assert.*;
import static hanto.common.HantoPlayerColor.*;
import static hanto.common.HantoPieceType.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import hanto.common.HantoCoordinate;
import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.otnah.common.GameState;
import hanto.otnah.common.HexPosition;
import hanto.otnah.common.InventoryPosition;
import hanto.otnah.common.Position;
import hanto.otnah.common.moves.MoveEnumerator;
import hanto.otnah.common.moves.PotentialMove;
import hanto.otnah.epsilon.EpsilonHantoGame;
import hanto.tournament.HantoMoveRecord;

import org.junit.Test;

/**
 * 
 * @author otnah
 *
 * Tests for the AI
 */
public class HantoPlayerTest {

	/**
	 * maks a new hex position
	 * @param x x
	 * @param y y
	 * @return a hex position
	 */
	private Position m(int x, int y)
	{
		return new HexPosition(x, y);
	}
	
	/**
	 * test to make sure that the colors are set up properly
	 * 
	 * @throws NoSuchFieldException err
	 * @throws SecurityException err
	 * @throws IllegalArgumentException err
	 * @throws IllegalAccessException err
	 */
	@Test
	public void testGameCreation() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field f = HantoPlayer.class.getDeclaredField("game");
		f.setAccessible(true);
		EpsilonHantoGame game;
		
		
		
		HantoPlayer hp1 = new HantoPlayer();
		hp1.startGame(HantoGameID.EPSILON_HANTO, RED, false);
		game = (EpsilonHantoGame) f.get(hp1);
		assertTrue(game.getCurrentPlayer().getColor() == BLUE);
		
		HantoPlayer hp2 = new HantoPlayer();
		hp2.startGame(HantoGameID.EPSILON_HANTO, RED, true);
		game = (EpsilonHantoGame) f.get(hp2);
		assertTrue(game.getCurrentPlayer().getColor() == RED);
		
	}
	
	/**
	 * test to make sure the other color algo works
	 */
	@Test
	public void testOtherColor()
	{
		HantoPlayer hp = new HantoPlayer();
		assertEquals(hp.other(RED), BLUE);
		assertEquals(hp.other(BLUE), RED);
	}
	
	/**
	 * test to make sure that the butterfly placement is the first thing done
	 */
	@Test
	public void testButterflyComesFirst()
	{
		PotentialMove[] arr = {
			new PotentialMove(m(0, 0), m(0, 1), RED, SPARROW),
			new PotentialMove(m(0, 0), m(0, 2), RED, SPARROW),
			new PotentialMove(m(0, 0), m(0, 3), RED, SPARROW),
			new PotentialMove(m(0, 0), new InventoryPosition(), RED, SPARROW),
			new PotentialMove(m(0, 0), new InventoryPosition(), RED, BUTTERFLY),	
			new PotentialMove(m(0, 0), m(1, 1), RED, SPARROW),
			new PotentialMove(m(0, 0), m(1, 2), RED, SPARROW),
		};
		
		List<PotentialMove> col = new ArrayList<>(Arrays.asList(arr));
		HantoPlayer hp1 = new HantoPlayer();
		EpsilonHantoGame ehg = new EpsilonHantoGame(RED);
		PotentialMove res = hp1.rank(col, ehg);
		
		// make the butterfly move
		assertEquals(res.getClass(), PotentialMove.class);
	}
	
	/**
	 * test that moving actually calls move the proper number of times with the proper args
	 * 
	 * @throws NoSuchFieldException err
	 * @throws SecurityException err
	 * @throws IllegalArgumentException err
	 * @throws IllegalAccessException err
	 */
	@Test
	public void testMovingWorks() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
	{
		Field f = HantoPlayer.class.getDeclaredField("game");
		f.setAccessible(true);
		HantoPlayer ai = new HantoPlayer();
		ai.startGame(HantoGameID.EPSILON_HANTO, RED, true);
		
		MockEpsilonGame meg = new MockEpsilonGame(RED, new TestTrigger(){
			@Override
			public void pull(String from, Object ... rest)
			{
				if (from.equals("getAllCurrentMoves"))
				{
					assertTrue(rest[0] instanceof MockEpsilonGame);
				}
				if (from.equals("makeMove"))
				{
					assertEquals(rest.length, 3);
					assertTrue(rest[0] == BUTTERFLY);
					assertTrue(rest[1] == null);
					assertTrue(rest[2].equals(new HexPosition(0, 0)));
				}
			}

			@Override
			public boolean option5() {
				return true;
			}
		});
		
		f.set(ai, meg);
		
		ai.makeMove(null);
	}
	
	/**
	 * test to make sure that when no more moves are availible, the proper result is returned
	 * 
	 * @throws NoSuchFieldException err
	 * @throws SecurityException err
	 * @throws IllegalArgumentException err
	 * @throws IllegalAccessException err
	 */
	@Test
	public void testNoMoreMoves() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
	{
		Field f = HantoPlayer.class.getDeclaredField("game");
		f.setAccessible(true);
		HantoPlayer ai = new HantoPlayer();
		ai.startGame(HantoGameID.EPSILON_HANTO, RED, true);
		
		MockEpsilonGame meg = new MockEpsilonGame(RED, new TestTrigger(){
			@Override
			public void pull(String from, Object ... rest)
			{
				if (from.equals("getAllCurrentMoves"))
				{
					assertTrue(rest[0] instanceof MockEpsilonGame);
				}
				if (from.equals("makeMove"))
				{
					assertEquals(rest.length, 3);
					assertTrue(rest[0] == BUTTERFLY);
					assertTrue(rest[1] == null);
					assertTrue(rest[2].equals(new HexPosition(0, 0)));
				}
			}

			@Override
			public boolean option5() {
				return false;
			}
		});
		
		f.set(ai, meg);
		
		HantoMoveRecord hmr = ai.makeMove(null);
		
		assertTrue(hmr.getFrom() == null);
		assertTrue(hmr.getTo() == null);
		assertTrue(hmr.getPiece() == null);
	}
	
	/**
	 * 
	 * @author otnah
	 *
	 * a mock move enumerator, for returning tainted data
	 */
	private class MockMoveEnumerator extends MoveEnumerator
	{
		private final TestTrigger t;
		
		/**
		 * constructor for mock
		 * @param tt the trigger for tests
		 */
		private MockMoveEnumerator(TestTrigger tt) {
			t = tt;
		}
		
		@Override
		public Collection<PotentialMove> getAllCurrentMoves(GameState state)
		{
			t.pull("getAllCurrentMoves", state);
			if (t.option5()) {
				PotentialMove[] arr = {
					new PotentialMove(m(0, 0), m(0, 1), RED, SPARROW),
					new PotentialMove(m(0, 0), m(0, 2), RED, SPARROW),
					new PotentialMove(m(0, 0), m(0, 3), RED, SPARROW),
					new PotentialMove(m(0, 0), new InventoryPosition(), RED, SPARROW),
					new PotentialMove(m(0, 0), new InventoryPosition(), RED, BUTTERFLY),	
					new PotentialMove(m(0, 0), m(1, 1), RED, SPARROW),
					new PotentialMove(m(0, 0), m(1, 2), RED, SPARROW),
				};
				return new ArrayList<>(Arrays.asList(arr));
			}
			return new ArrayList<>();
		}
		
	}
	
	/**
	 * 
	 * @author otnah
	 *
	 * A mock epsilon game that tweaks parts of its movement to read
	 * events from the AI
	 */
	private class MockEpsilonGame extends EpsilonHantoGame
	{
		private final TestTrigger t;
		
		/**
		 * default cons for mock
		 * @param player the Color of the first player
		 * @param tt the trigger for testing
		 */
		private MockEpsilonGame(HantoPlayerColor player, TestTrigger tt) {
			super(player);
			t = tt;
		}
		
		@Override
		public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate fromHC, HantoCoordinate toHC)
		{
			t.pull("makeMove", pieceType, fromHC, toHC);
			return MoveResult.OK;
		}
		
		@Override
		public MoveEnumerator getMoveEnumerator()
		{
			return new MockMoveEnumerator(t);
		}
	}
	
	/**
	 * 
	 * @author otnah
	 *
	 * what the tests use to capture input from the mock objects
	 */
	private interface TestTrigger
	{
		/**
		 * continuation passing, essentially
		 * @param from method name, for identification
		 * @param rest any optional arguments
		 */
		void pull(String from, Object ... rest);
		
		/**
		 * throwback to FRC in highschool, gotta love option 5
		 * @return who?
		 */
		boolean option5();
	}

}
