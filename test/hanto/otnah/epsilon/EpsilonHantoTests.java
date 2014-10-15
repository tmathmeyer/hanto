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
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.otnah.common.PieceLocationPair;
import hanto.studentotnah.common.moves.MoveEnumerator;
import hanto.studentotnah.epsilon.EpsilonHantoGame;
import static hanto.common.HantoPieceType.*;
import static hanto.common.HantoPlayerColor.BLUE;
import static hanto.common.HantoPlayerColor.RED;

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
	 * test an example game
	 * @throws HantoException uh oh
	 */
	@Test
	public void testExampleGame() throws HantoException
	{
		EpsilonHantoTestGame g = new EpsilonHantoTestGame(HantoPlayerColor.BLUE);
		
		PieceLocationPair[] plp = {
				plPair(BLUE, BUTTERFLY, 0, 0), plPair(BLUE, HORSE, 1, 0),
				plPair(BLUE, HORSE, -1, 2),    plPair(BLUE, HORSE, 0, 2),
				plPair(BLUE, CRAB, 0, 1), plPair(BLUE, CRAB, 1, 2),
				plPair(BLUE, SPARROW, 1, 1), plPair(RED, BUTTERFLY, -1, 0),
				plPair(RED, CRAB, -2, 1), plPair(RED, CRAB, 0, -2),
				plPair(RED, CRAB, 1, -3), plPair(RED, HORSE, -1, -1),
				plPair(RED, HORSE, 0, -3)};
		
		g.initializeBoard(plp);
		
		assertTrue(g.getCurrentPlayer().getColor() == BLUE);
		assertEquals(g.getCurrentPlayer().getInventory().size(), 6);
		assertTrue(g.getMoveEnumerator().getAllCurrentMoves(g).size() > 0);
		
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

	
	/**
	 * Factory method to create a piece location pair.
	 * @param player the player color
	 * @param pieceType the piece type
	 * @param x starting location
	 * @param y end location
	 * @return
	 */
	private PieceLocationPair plPair(HantoPlayerColor player, HantoPieceType pieceType, 
			int x, int y)
	{
		return new PieceLocationPair(player, pieceType, new C(x, y));
	}
}
