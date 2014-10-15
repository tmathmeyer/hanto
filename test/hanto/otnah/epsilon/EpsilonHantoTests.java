/*******************************************************************************
 * All sources under the hanto.otnah package were developed by
 * Ted Meyer and Nilesh Patel for the term project in CS4233
 * at Worcester Polytechnic Institute. Since WPI holds all other forms
 * of ownership to this software, we have decided to not make this
 * software available under any license. Evaluation or compilation rights
 * are therefore granted only to course staff.
 *******************************************************************************/

package hanto.otnah.epsilon;

import static org.junit.Assert.*;
import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.otnah.common.moves.MoveEnumerator;

import static hanto.common.HantoPieceType.*;

import org.junit.Test;

/**
 * 
 * @author otnah
 *
 * Some tests for the special behavior of the epsilon game
 */
public class EpsilonHantoTests {

	/**
	 * default constructor caller thing
	 * @return a new game
	 */
	public EpsilonHantoGame newGame()
	{
		return new EpsilonHantoGame(HantoPlayerColor.RED);
	}
	
	/**
	 * 
	 * @author otnah
	 *
	 * A hantoCoordinate for testing
	 */
	private static class C implements HantoCoordinate {
		final int x, y;
		private C (int x, int y) {
			this.x = x;
			this.y = y;
		}
		@Override
		public int getX() {
			return x;
		}
		@Override
		public int getY() {
			return y;
		}
	}
	
	/**
	 * try to place a butterfly
	 * @throws HantoException
	 */
	@Test
	public void testTryPlayingButterfly() throws HantoException
	{
		EpsilonHantoGame g = newGame();
		assertEquals(g.makeMove(BUTTERFLY, null, new C(0, 0)), MoveResult.OK);
	}
	
	/**
	 * try to place both butterflies
	 * @throws HantoException
	 */
	@Test
	public void testMovesAvailible() throws HantoException
	{
		EpsilonHantoGame g = newGame();
		assertEquals(new MoveEnumerator().getAllCurrentMoves(g).size(), 13);
		
		g.makeMove(BUTTERFLY, null, new C(0, 0));
		assertEquals(new MoveEnumerator().getAllCurrentMoves(g).size(), 78);
		
		g.makeMove(BUTTERFLY, null, new C(0, 1));
		assertEquals(new MoveEnumerator().getAllCurrentMoves(g).size(), 38);
	}
	
	/**
	 * try to place and jump a horse
	 * TODO: do this
	 * @throws HantoException
	 */
	@Test
	public void testHorse() throws HantoException
	{
		EpsilonHantoGame g = newGame();
		assertEquals(new MoveEnumerator().getAllCurrentMoves(g).size(), 13);
		
		g.makeMove(BUTTERFLY, null, new C(0, 0));
		assertEquals(new MoveEnumerator().getAllCurrentMoves(g).size(), 78);
		
		g.makeMove(BUTTERFLY, null, new C(0, 1));
		assertEquals(new MoveEnumerator().getAllCurrentMoves(g).size(), 38);
	}

}
