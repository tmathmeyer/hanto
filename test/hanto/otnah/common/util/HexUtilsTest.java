package hanto.otnah.common.util;

import static org.junit.Assert.*;

import java.util.Collection;

import hanto.common.HantoCoordinate;
import hanto.otnah.common.HexPosition;

import org.junit.Test;

public class HexUtilsTest {

	HantoCoordinate HC0 = new HexPosition(0, 0);
	HantoCoordinate HC1 = new HexPosition(1, 0);
	HantoCoordinate HC2 = new HexPosition(0, 1);
	HantoCoordinate HC3 = new HexPosition(-1, 1);
	HantoCoordinate HC4 = new HexPosition(1, -1);
	HantoCoordinate HC5 = new HexPosition(-1, 0);
	HantoCoordinate HC6 = new HexPosition(0, -1);
	
	@Test
	public void verticalDistance()
	{
		assertEquals(HexUtil.distance(HC2, HC6), 2);
		assertEquals(HexUtil.distance(HC0, HC6), 1);
		assertEquals(HexUtil.distance(HC2, HC0), 1);
		assertEquals(HexUtil.distance(HC6, HC2), 2);
	}
	
	@Test
	public void positiveSlopeDistance()
	{
		assertEquals(HexUtil.distance(HC1, HC5), 2);
		assertEquals(HexUtil.distance(HC0, HC5), 1);
		assertEquals(HexUtil.distance(HC1, HC0), 1);
		assertEquals(HexUtil.distance(HC5, HC1), 2);
	}
	
	@Test
	public void negativeSlopeDistance()
	{
		assertEquals(HexUtil.distance(HC3, HC4), 2);
		assertEquals(HexUtil.distance(HC0, HC4), 1);
		assertEquals(HexUtil.distance(HC3, HC0), 1);
		assertEquals(HexUtil.distance(HC4, HC3), 2);
	}
	
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
