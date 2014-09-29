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
import hanto.otnah.common.HantoTile;
import hanto.otnah.common.pieces.Butterfly;
import hanto.otnah.common.pieces.Sparrow;
import hanto.common.HantoPlayerColor;

import org.junit.Test;

/**
 * 
 * @author otnah
 *
 */
public class HantoTileTest {
	HantoTile butterfly = new Butterfly(HantoPlayerColor.BLUE);
	HantoTile sparrow = new Sparrow(HantoPlayerColor.RED);
	
	/**
	 * test the position is null by default
	 */
	@Test
	public void getPositionTest() {
		assertNull(butterfly.getPosition());
	}
	
}
