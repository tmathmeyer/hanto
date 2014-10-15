/*******************************************************************************
 * All sources under the hanto.otnah package were developed by
 * Ted Meyer and Nilesh Patel for the term project in CS4233
 * at Worcester Polytechnic Institute. Since WPI holds all other forms
 * of ownership to this software, we have decided to not make this
 * software available under any license. Evaluation or compilation rights
 * are therefore granted only to course staff.
 *******************************************************************************/

package hanto.otnah.common.util;

import static org.junit.Assert.*;
import static hanto.studentotnah.common.util.HexUtil.*;

import java.util.Collection;

import hanto.studentotnah.common.HexPosition;
import hanto.studentotnah.common.Position;
import hanto.studentotnah.common.util.HexUtil;

import org.junit.Test;

/**
 * 
 * @author otnah
 *
 */
public class HexUtilsTest {

	Position HC0 = new HexPosition(0, 0);
	Position HC1 = new HexPosition(1, 0);
	Position HC2 = new HexPosition(0, 1);
	Position HC3 = new HexPosition(-1, 1);
	Position HC4 = new HexPosition(1, -1);
	Position HC5 = new HexPosition(-1, 0);
	Position HC6 = new HexPosition(0, -1);
	
	Position HC7 = new HexPosition(-3, -3);
	Position HC8 = new HexPosition(-2, -2);
	Position HC9 = new HexPosition(-1, -1);
	Position HCA = new HexPosition(0, 0);
	Position HCB = new HexPosition(1, 1);
	Position HCC = new HexPosition(2, 2);
	Position HCD = new HexPosition(3, 3);
	Position HCE = new HexPosition(3, 9);
	
	/**
	 * test that vertical distances are correct
	 */
	@Test
	public void verticalDistance()
	{
		assertEquals(distance(HC2, HC6), 2);
		assertEquals(distance(HC0, HC6), 1);
		assertEquals(distance(HC2, HC0), 1);
		assertEquals(distance(HC6, HC2), 2);
	}
	
	/**
	 * test positive slope distances
	 */
	@Test
	public void positiveSlopeDistance()
	{
		assertEquals(distance(HC1, HC5), 2);
		assertEquals(distance(HC0, HC5), 1);
		assertEquals(distance(HC1, HC0), 1);
		assertEquals(distance(HC5, HC1), 2);
	}
	
	/**
	 * test negative slope distances
	 */
	@Test
	public void negativeSlopeDistance()
	{
		assertEquals(distance(HC3, HC4), 2);
		assertEquals(distance(HC0, HC4), 1);
		assertEquals(distance(HC3, HC0), 1);
		assertEquals(distance(HC4, HC3), 2);
	}
	
	/**
	 * test surrounding code
	 */
	@Test
	public void testSurrounding()
	{
		Collection<Position> coords = HexUtil.locationsSurrounding(HC0);
		
		assertTrue(coords.contains(HC1));
		assertTrue(coords.contains(HC2));
		assertTrue(coords.contains(HC3));
		assertTrue(coords.contains(HC4));
		assertTrue(coords.contains(HC5));
		assertTrue(coords.contains(HC6));
	}
	
	/**
	 * test to make sure linear checking works
	 */
	@Test
	public void testLinearality()
	{
		assertFalse(checkLinearality(HC7, HCD));
		assertTrue(checkLinearality(HCD, HCE));
		assertFalse(checkLinearality(HC7, HCE));
	}
	
	/**
	 * test to make sure that it correctly picks up the right pieces
	 */
	@Test
	public void testLinearPieceAccumulation()
	{
		Collection<Position> co1 = getLinearSlide(HC7, HCD);
		assertTrue(co1.contains(HC8));
		
		Collection<Position> co2 = getLinearSlide(HCD, HC7);
		assertTrue(co2.contains(HC8));
	}

}
