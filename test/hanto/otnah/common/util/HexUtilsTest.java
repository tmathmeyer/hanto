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

import java.util.Collection;

import hanto.common.HantoCoordinate;
import hanto.otnah.common.HexPosition;

import org.junit.Test;

/**
 * 
 * @author otnah
 *
 */
public class HexUtilsTest {

	HantoCoordinate HC0 = new HexPosition(0, 0);
	HantoCoordinate HC1 = new HexPosition(1, 0);
	HantoCoordinate HC2 = new HexPosition(0, 1);
	HantoCoordinate HC3 = new HexPosition(-1, 1);
	HantoCoordinate HC4 = new HexPosition(1, -1);
	HantoCoordinate HC5 = new HexPosition(-1, 0);
	HantoCoordinate HC6 = new HexPosition(0, -1);
	
	/**
	 * test that vertical distances are correct
	 */
	@Test
	public void verticalDistance()
	{
		assertEquals(HexUtil.distance(HC2, HC6), 2);
		assertEquals(HexUtil.distance(HC0, HC6), 1);
		assertEquals(HexUtil.distance(HC2, HC0), 1);
		assertEquals(HexUtil.distance(HC6, HC2), 2);
	}
	
	/**
	 * test positive slope distances
	 */
	@Test
	public void positiveSlopeDistance()
	{
		assertEquals(HexUtil.distance(HC1, HC5), 2);
		assertEquals(HexUtil.distance(HC0, HC5), 1);
		assertEquals(HexUtil.distance(HC1, HC0), 1);
		assertEquals(HexUtil.distance(HC5, HC1), 2);
	}
	
	/**
	 * test negative slope distances
	 */
	@Test
	public void negativeSlopeDistance()
	{
		assertEquals(HexUtil.distance(HC3, HC4), 2);
		assertEquals(HexUtil.distance(HC0, HC4), 1);
		assertEquals(HexUtil.distance(HC3, HC0), 1);
		assertEquals(HexUtil.distance(HC4, HC3), 2);
	}
	
	/**
	 * test surrounding code
	 */
	@Test
	public void testSurrounding()
	{
		Collection<HantoCoordinate> coords = HexUtil.locationsSurrounding(HC0);
		
		assertTrue(coords.contains(HC1));
		assertTrue(coords.contains(HC2));
		assertTrue(coords.contains(HC3));
		assertTrue(coords.contains(HC4));
		assertTrue(coords.contains(HC5));
		assertTrue(coords.contains(HC6));
	}

}
