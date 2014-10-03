/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.otnah.delta;

import static org.junit.Assert.*;
import hanto.common.*;

import org.junit.*;

import hanto.otnah.common.*;
import static hanto.common.HantoPieceType.*;
import static hanto.common.MoveResult.*;
import static hanto.common.HantoPlayerColor.*;

/**
 * Description
 * @version Sep 24, 2014
 */
public class DeltaHantoMasterTest
{
	/**
	 * Internal class for these test cases.
	 * @version Sep 13, 2014
	 */
	public static class TestHantoCoordinate implements HantoCoordinate
	{
		private final int x, y;
		
		/**
		 * this should have been provided to us javadoc'd
		 * @param x no
		 * @param y no
		 */
		public TestHantoCoordinate(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
		
		/*
		 * @see hanto.common.HantoCoordinate#getX()
		 */
		@Override
		public int getX()
		{
			return x;
		}

		/*
		 * @see hanto.common.HantoCoordinate#getY()
		 */
		@Override
		public int getY()
		{
			return y;
		}

	}
	
	private static final HantoTestGameFactory FACTORY = HantoTestGameFactory.getInstance();
	private HantoGame game;
	private HantoTestGame testGame;
	
	/**
	 * should have been provided as javadoc
	 */
	@Before
	public void setup()
	{
		// By default, blue moves first.
		testGame = FACTORY.makeTestHantoGame(HantoGameID.DELTA_HANTO);
		game = testGame;
	}
	
	/**
	 * should have been provided as javadoc
	 * @throws HantoException if fails
	 */
	@Test
	public void bluePlacesButterflyFirst() throws HantoException
	{
		final MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		assertEquals(OK, mr);
		final HantoPiece piece = game.getPieceAt(makeCoordinate(0, 0));
		assertEquals(BLUE, piece.getColor());
		assertEquals(BUTTERFLY, piece.getType());
	}
	
	/**
	 * should have been provided as javadoc
	 * @throws HantoException if fails
	 */
	@Test
	public void redPlacesSparrowFirst() throws HantoException
	{
		final MoveResult mr = game.makeMove(SPARROW, null, makeCoordinate(0, 0));
		assertEquals(OK, mr);
	}
	
	/**
	 * should have been provided as javadoc
	 * @throws HantoException if fails
	 */
	@Test
	public void blueMovesSparrow() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(0, -1));
		game.makeMove(SPARROW, null, makeCoordinate(0, 2));
		final MoveResult mr = game.makeMove(SPARROW, makeCoordinate(0, -1), makeCoordinate(-1, 0));
		assertEquals(OK, mr);
		final HantoPiece piece = game.getPieceAt(makeCoordinate(-1, 0));
		assertEquals(BLUE, piece.getColor());
		assertEquals(SPARROW, piece.getType());
	}
	
	/**
	 * should have been provided as javadoc
	 * @throws HantoException if fails
	 */
	@Test
	public void blueMovesSparrowUsingTestGame() throws HantoException
	{
		final PieceLocationPair[] board = new PieceLocationPair[] {
		    plPair(BLUE, BUTTERFLY, 0, 0), plPair(RED, BUTTERFLY, 0, 1),
		    plPair(BLUE, SPARROW, 0, -1), plPair(RED, SPARROW, 0, 2)
		    
		};
		testGame.initializeBoard(board);
		testGame.setPlayerMoving(BLUE);
		testGame.setTurnNumber(3);
		final MoveResult mr = game.makeMove(SPARROW, makeCoordinate(0, -1), makeCoordinate(-1, 0));
		assertEquals(OK, mr);
		final HantoPiece piece = game.getPieceAt(makeCoordinate(-1, 0));
		assertEquals(BLUE, piece.getColor());
		assertEquals(SPARROW, piece.getType());
	}
	
	/**
	 * should have been provided as javadoc
	 * @throws HantoException if fails
	 */
	@Test
	public void moveButterfly() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		assertEquals(OK, game.makeMove(BUTTERFLY, makeCoordinate(0, 0), makeCoordinate(1, 0)));
		final HantoPiece piece = game.getPieceAt(makeCoordinate(1, 0));
		assertEquals(BLUE, piece.getColor());
		assertEquals(BUTTERFLY, piece.getType());
		assertNull(game.getPieceAt(makeCoordinate(0, 0)));
	}
	
	/**
	 * should have been provided as javadoc
	 * @throws HantoException if fails
	 */
	@Test(expected=HantoException.class)
	public void moveToDisconnectConfiguration() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(BUTTERFLY, makeCoordinate(0, 0), makeCoordinate(0, -1));
	}
	
	/**
	 * should have been provided as javadoc
	 * @throws HantoException if fails
	 */
	@Test(expected=HantoException.class)
	public void attemptToMoveAPieceFromAnEmptyHex() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(BUTTERFLY, makeCoordinate(1, 0), makeCoordinate(0, -1));
	}
	
	/**
	 * should have been provided as javadoc
	 * @throws HantoException if fails
	 */
	@Test(expected=HantoException.class)
	public void attemptToMoveWrongPiece() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, makeCoordinate(0, 0), makeCoordinate(1, 0));
	}
	
	/**
	 * Red wins on blue surrender
	 * @throws HantoException if fails
	 */
	@Test
	public void redWinsOnBlueSurrender() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		assertEquals(RED_WINS, game.makeMove(null, null, null));
	}
	
	
	
	/**
	 * Blue wins on red surrender
	 * @throws HantoException if fails
	 */
	@Test
	public void blueWinsOnRedSurrender() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		assertEquals(BLUE_WINS, game.makeMove(null, null, null));
	}
	
	/**
	 * makes sure crabs move like crabs
	 * @throws HantoException if fails
	 */
	@Test
	public void crabsDoCrabWalk() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
		assertEquals(OK, game.makeMove(CRAB, null, makeCoordinate(-1, 0)));
		assertEquals(OK, game.makeMove(CRAB, null, makeCoordinate(2, 0)));
		assertEquals(OK, game.makeMove(CRAB, makeCoordinate(-1, 0), makeCoordinate(0, -1)));
	}
	
	/**
	 * Can a player win by surrounding
	 * @throws HantoException if something goes wrong
	 */
	@Test
	public void surroundRed() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(0, -1));
		game.makeMove(SPARROW, null, makeCoordinate(2, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(3, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 1));
		game.makeMove(SPARROW, makeCoordinate(3, 0), makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(0, -2));
		assertEquals(BLUE_WINS, game.makeMove(SPARROW, makeCoordinate(2, 0), makeCoordinate(1, -1)));
		
	}
	
	// Helper methods
	private HantoCoordinate makeCoordinate(int x, int y)
	{
		return new TestHantoCoordinate(x, y);
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
		return new PieceLocationPair(player, pieceType, new TestHantoCoordinate(x, y));
	}
}
