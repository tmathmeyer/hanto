package hanto.otnah.common;

import static org.junit.Assert.*;
import hanto.common.HantoPlayerColor;
import hanto.otnah.common.pieces.Butterfly;
import hanto.otnah.gamma.GammaHantoGame;
import hanto.otnah.gamma.GammaPlayer;

import org.junit.Test;

public class MiscelaneousTests {

	@Test
	public void inventoryPositionHash()
	{
		assertEquals(new InventoryPosition().hashCode(), 655678901);
	}

	@Test
	public void gameStatePieceOperations()
	{
		GammaHantoGame ghs = new GammaHantoGame(HantoPlayerColor.RED);
		Butterfly b = new Butterfly(HantoPlayerColor.RED);
		ghs.setPieceAt(b, new HexPosition(0, 0));
		assertEquals(b, ghs.removePieceFrom(new HexPosition(0, 0)));
	}
	
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
	
	@Test
	public void testCurrentPositions()
	{
		GammaPlayer ghp = new GammaPlayer(HantoPlayerColor.RED);
		
		assertEquals(ghp.getCurrentPositions().size(), 0);
		
		Position b = new HexPosition(0, 0);
		
		assertEquals(b.adjacentPositions().size(), 0);
	}
	
	@Test
	public void testPositionEquality()
	{
		Position a = new HexPosition(0, 0);
		Position b = new HexPosition(0, 0);
		Position c = new HexPosition(1, 0);
		Position d = new HexPosition(0, 1);
		
		assertFalse(a.equals(null));
		assertTrue(a.equals(a));
		assertTrue(a.equals(b));
		assertFalse(a.equals(c));
		assertFalse(a.equals(d));
	}
}
