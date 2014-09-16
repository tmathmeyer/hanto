/*******************************************************************************
 * All sources under the hanto.otnah package were developed by
 * Ted Meyer and Nilesh Patel for the term project in CS4233
 * at Worcester Polytechnic Institute. Since WPI holds all other forms
 * of ownership to this software, we have decided to not make this
 * software available under any license. Evaluation or compilation rights
 * are therefore granted only to course staff.
 *******************************************************************************/

package hanto.otnah.alpha;

import static org.junit.Assert.*;

import org.junit.Test;

import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;

/**
 * 
 * @author otnah
 *
 */
public class ButterflyTest {

	/**
	 * test that a color is correct
	 */
	@Test
	public void isProperColor()
	{
		assertEquals(new Butterfly(HantoPlayerColor.RED).getColor(), HantoPlayerColor.RED);
	}
	
	/**
	 * make sure a butterfly is really a butterfly
	 */
	@Test
	public void isButterfly()
	{
		assertEquals(new Butterfly(HantoPlayerColor.RED).getType(), HantoPieceType.BUTTERFLY);
	}
	
	/**
	 * make sure that a move does nothing
	 */
	@Test
	public void isValidMoveDoesNothing()
	{
		assertFalse(new Butterfly(null).isValidMove(null));
	}
	
	/**
	 * make sure that surrounging does nothing
	 */
	@Test
	public void doesSurroundingMovedDoNothing()
	{
		assertNull(new Butterfly(null).getAdjacentPositions());
	}
}
