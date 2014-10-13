/*******************************************************************************
 * All sources under the hanto.otnah package were developed by
 * Ted Meyer and Nilesh Patel for the term project in CS4233
 * at Worcester Polytechnic Institute. Since WPI holds all other forms
 * of ownership to this software, we have decided to not make this
 * software available under any license. Evaluation or compilation rights
 * are therefore granted only to course staff.
 *******************************************************************************/

package hanto.otnah.common.pieces;

import static org.junit.Assert.*;

import org.junit.Test;

import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;

/**
 * 
 * @author otnah
 *
 */
public class HorseTest {

	/**
	 * test that a color is correct
	 */
	@Test
	public void isProperColor()
	{
		assertEquals(new Horse(HantoPlayerColor.RED).getColor(), HantoPlayerColor.RED);
	}
	
	/**
	 * make sure a butterfly is really a butterfly
	 */
	@Test
	public void isButterfly()
	{
		assertEquals(new Horse(HantoPlayerColor.RED).getType(), HantoPieceType.HORSE);
	}
}
