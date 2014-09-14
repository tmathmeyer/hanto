package hanto.otnah.alpha;

import static org.junit.Assert.*;

import org.junit.Test;

import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;

public class ButterflyTest {

	@Test
	public void isProperColor()
	{
		assertEquals(new Butterfly(HantoPlayerColor.RED).getColor(), HantoPlayerColor.RED);
	}
	
	@Test
	public void isButterfly()
	{
		assertEquals(new Butterfly(HantoPlayerColor.RED).getType(), HantoPieceType.BUTTERFLY);
	}
	
	@Test
	public void isValidMoveDoesNothing()
	{
		assertFalse(new Butterfly(null).isValidMove(null));
	}
	
	@Test
	public void doesSurroundingMovedDoNothing()
	{
		assertNull(new Butterfly(null).getAdjacentPositions());
	}
}
