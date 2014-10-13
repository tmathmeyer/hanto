/*******************************************************************************
 * All sources under the hanto.otnah package were developed by
 * Ted Meyer and Nilesh Patel for the term project in CS4233
 * at Worcester Polytechnic Institute. Since WPI holds all other forms
 * of ownership to this software, we have decided to not make this
 * software available under any license. Evaluation or compilation rights
 * are therefore granted only to course staff.
 *******************************************************************************/

package hanto.otnah.common;

import static org.junit.Assert.*;
import hanto.common.HantoPlayerColor;
import hanto.otnah.common.pieces.Butterfly;
import hanto.otnah.gamma.GammaHantoGame;

import org.junit.Test;

/**
 * 
 * @author otnah
 *
 *
 * test misc things, like equals methods, in order to have good coverage
 */
public class MiscelaneousTests {

	/**
	 * test the inventory positions
	 */
	@Test
	public void inventoryPositionHash()
	{
		assertEquals(new InventoryPosition().hashCode(), 655678901);
	}

	/**
	 * test whether pieces can be set and added to the graph
	 */
	@Test
	public void gameStatePieceOperations()
	{
		GammaHantoGame ghs = new GammaHantoGame(HantoPlayerColor.RED);
		Butterfly b = new Butterfly(HantoPlayerColor.RED);
		ghs.setPieceAt(b, new HexPosition(0, 0));
		assertEquals(b, ghs.removePieceFrom(new HexPosition(0, 0)));
	}
	
	/**
	 * test whether pieces are continuous during a move
	 */
	@Test
	public void continuous()
	{
		GammaHantoGame ghs = new GammaHantoGame(HantoPlayerColor.RED);
		Butterfly b = new Butterfly(HantoPlayerColor.RED);
		ghs.setPieceAt(b, new HexPosition(0, 0));
		System.out.println(ghs.getPrintableBoard());
		assertTrue(ghs.isGraphContinuityPreservedAfter(new HexPosition(0, 0),
													   new HexPosition(1, 0)));
	}
	
	/**
	 * test base cases for surrounding positions and for starting game states
	 */
	@Test
	public void testCurrentPositions()
	{
		HantoPlayer ghp = HantoPlayerFactory.makeGammaPlayers(HantoPlayerColor.RED);
		
		assertEquals(ghp.getCurrentPositions().size(), 0);
		
		Position b = new HexPosition(0, 0);
		
		assertEquals(b.adjacentPositions().size(), 6);
	}
	
	/**
	 *  test whether the equals method in positions is tested.
	 *  most of the cases arent called in general use, but it's
	 *  a proper equals method.
	 */
	@Test
	public void testPositionEquality()
	{
		Position a = new HexPosition(0, 0);
		Position b = new HexPosition(0, 0);
		Position c = new HexPosition(1, 0);
		Position d = new HexPosition(0, 1);
		
		Object test = null;
		
		assertFalse(a.equals(test));
		assertTrue(a.equals(a));
		assertTrue(a.equals(b));
		assertFalse(a.equals(c));
		assertFalse(a.equals(d));
	}
}
