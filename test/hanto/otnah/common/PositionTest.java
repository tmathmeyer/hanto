package hanto.otnah.common;

import static org.junit.Assert.*;
import hanto.otnah.alpha.AlphaPosition;

import org.junit.Test;

public class PositionTest
{

	@Test
	public void testNormaldistances()
	{
		Position hc1 = new AlphaPosition(0, 0);
		Position hc2 = new AlphaPosition(1, 1);
		Position hc3 = new AlphaPosition(2, 2);
		Position hc4 = new AlphaPosition(3, 5);
		
		assertEquals(hc1.getDistanceTo(hc2), 2);
		assertEquals(hc1.getDistanceTo(hc3), 4);
		assertEquals(hc2.getDistanceTo(hc3), 2);
		
		assertEquals(hc3.getDistanceTo(hc1), 4);
		assertEquals(hc1.getDistanceTo(hc4), 8);
		assertEquals(hc4.getDistanceTo(hc1), 8);
		
	}
	
	
	@Test
	public void testInventorydistances()
	{
		Position i = new InventoryPosition();
		Position hc1 = new AlphaPosition(1, 1);
		Position hc2 = new AlphaPosition(2, 2);
		Position hc3 = new AlphaPosition(3, 5);
		
		assertEquals(i.getDistanceTo(hc1), 0);
		assertEquals(i.getDistanceTo(hc2), 0);
		assertEquals(i.getDistanceTo(hc3), 0);
		
		assertEquals(hc1.getDistanceTo(i), -1);
		assertEquals(hc2.getDistanceTo(i), -1);
		assertEquals(hc3.getDistanceTo(i), -1);
		
	}

}
