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
import hanto.studentotnah.common.HexPosition;
import hanto.studentotnah.common.InventoryPosition;
import hanto.studentotnah.common.Position;

import java.util.Collection;

import org.junit.Test;

/**
 * 
 * @author otnah
 *
 */
public class PositionTest
{
	
	/**
	 * test distances work
	 */
	@Test
	public void testNormaldistances()
	{
		Position hc1 = new HexPosition(0, 0);
		Position hc2 = new HexPosition(1, 1);
		Position hc3 = new HexPosition(2, 2);
		Position hc4 = new HexPosition(3, 5);
		
		assertEquals(hc1.getDistanceTo(hc2), 2);
		assertEquals(hc1.getDistanceTo(hc3), 4);
		assertEquals(hc2.getDistanceTo(hc3), 2);
		
		assertEquals(hc3.getDistanceTo(hc1), 4);
		assertEquals(hc1.getDistanceTo(hc4), 8);
		assertEquals(hc4.getDistanceTo(hc1), 8);
		
	}
	
	/**
	 * test inventory distances work
	 */
	@Test
	public void testInventorydistances()
	{
		Position i = new InventoryPosition();
		Position hc1 = new HexPosition(1, 1);
		Position hc2 = new HexPosition(2, 2);
		Position hc3 = new HexPosition(3, 5);
		
		assertEquals(i.getDistanceTo(hc1), 0);
		assertEquals(i.getDistanceTo(hc2), 0);
		assertEquals(i.getDistanceTo(hc3), 0);
		
		assertEquals(hc1.getDistanceTo(i), -1);
		assertEquals(hc2.getDistanceTo(i), -1);
		assertEquals(hc3.getDistanceTo(i), -1);
		
	}
	
	/**
	 * make sure that inventories fail cood checks
	 * @throws UnsupportedOperationException
	 */
	@Test(expected=UnsupportedOperationException.class)
	public void testInventoryHasNoX() throws UnsupportedOperationException
	{
		Position i = new InventoryPosition();
		i.getX();
	}
	
	/**
	 * make sure inventories fail coord checks
	 * @throws UnsupportedOperationException
	 */
	@Test(expected=UnsupportedOperationException.class)
	public void testInventoryHasNoY() throws UnsupportedOperationException
	{
		Position i = new InventoryPosition();
		i.getY();
	}

	/**
	 * check adjacent positions
	 */
	@Test
	public void testAdjacentPositions(){
		Position around = new HexPosition(0, 0);
		Collection<Position> col = around.adjacentPositions();
		assertTrue(col.contains(new HexPosition(0, 1)));
		assertTrue(col.contains(new HexPosition(1, -1)));
		assertTrue(col.contains(new HexPosition(1, 0)));
		assertTrue(col.contains(new HexPosition(0, -1)));
		assertTrue(col.contains(new HexPosition(-1, 0)));
		assertTrue(col.contains(new HexPosition(-1, 1)));
		assertEquals(col.size(), 6);
	}
	
	/**
	 * check all inventories are the same
	 */
	@Test
	public void allInventoryPositionsEqualTest(){
		Position one = new InventoryPosition();
		Position two = new InventoryPosition();
		assertTrue(one.equals(two));
	}
}
