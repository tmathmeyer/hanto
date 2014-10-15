package hanto.otnah.epsilon;

import static org.junit.Assert.*;
import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.otnah.common.moves.MoveEnumerator;

import static hanto.common.HantoPieceType.*;

import org.junit.Test;

public class EpsilonHantoTests {

	public EpsilonHantoGame newGame()
	{
		return new EpsilonHantoGame(HantoPlayerColor.RED);
	}
	
	private static class C implements HantoCoordinate {
		final int x,y;
		public C (int x, int y) {
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
	
	
	@Test
	public void testTryPlayingButterfly() throws HantoException
	{
		EpsilonHantoGame g = newGame();
		assertEquals(g.makeMove(BUTTERFLY, null, new C(0, 0)), MoveResult.OK);
	}
	
	
	@Test
	public void testMovesAvailible() throws HantoException
	{
		EpsilonHantoGame g = newGame();
		assertEquals(new MoveEnumerator().getAllCurrentMoves(g).size(), 13);
		
		g.makeMove(BUTTERFLY, null, new C(0, 0));
		assertEquals(new MoveEnumerator().getAllCurrentMoves(g).size(), 13*6);
		
		g.makeMove(BUTTERFLY, null, new C(0, 1));
		assertEquals(new MoveEnumerator().getAllCurrentMoves(g).size(), 38);
	}
	
	@Test
	public void testHorse() throws HantoException
	{
		EpsilonHantoGame g = newGame();
		assertEquals(new MoveEnumerator().getAllCurrentMoves(g).size(), 13);
		
		g.makeMove(BUTTERFLY, null, new C(0, 0));
		assertEquals(new MoveEnumerator().getAllCurrentMoves(g).size(), 13*6);
		
		g.makeMove(BUTTERFLY, null, new C(0, 1));
		assertEquals(new MoveEnumerator().getAllCurrentMoves(g).size(), 38);
	}

}
