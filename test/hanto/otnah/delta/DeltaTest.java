/*******************************************************************************
 * All sources under the hanto.otnah package were developed by
 * Ted Meyer and Nilesh Patel for the term project in CS4233
 * at Worcester Polytechnic Institute. Since WPI holds all other forms
 * of ownership to this software, we have decided to not make this
 * software available under any license. Evaluation or compilation rights
 * are therefore granted only to course staff.
 *******************************************************************************/

package hanto.otnah.delta;

import static org.junit.Assert.*;
import hanto.common.HantoGameID;
import hanto.common.HantoPlayerColor;
import hanto.otnah.HantoGameFactory;
import hanto.otnah.common.GameState;
import hanto.otnah.common.HantoPlayer;
import hanto.otnah.common.HantoTile;

import org.junit.Test;

/**
 * 
 * @author otnah
 *
 * test functions and movment sets in the delta game
 */
public class DeltaTest {

	private int[] inventoryCounts(HantoPlayer<?> p)
	{
		int[] result = new int[3];
		for(HantoTile t : p.getInventory())
		{
			switch(t.getType())
			{
				case BUTTERFLY:
					result[0] ++;
					break;
				case SPARROW:
					result[1] ++;
					break;
				default:
					result[2] ++;
					break;
			}
		}
		return result;
	}
	
	/**
	 * test whether a delta game can be made
	 */
	@Test
	public void canDeltaBeMade()
	{
		GameState red = (GameState) HantoGameFactory.getInstance().makeHantoGame(HantoGameID.DELTA_HANTO, HantoPlayerColor.RED);
		GameState blue = (GameState) HantoGameFactory.getInstance().makeHantoGame(HantoGameID.DELTA_HANTO, HantoPlayerColor.BLUE);
		
		assertTrue(red.getCurrentPlayer().getColor().equals(HantoPlayerColor.RED));
		assertTrue(blue.getCurrentPlayer().getColor().equals(HantoPlayerColor.BLUE));
	}
	
	/**
	 * test whether players have proper starting inventories
	 */
	@Test
	public void properStartingInventories()
	{
		GameState red = new DeltaHantoGame(HantoPlayerColor.RED);
		GameState blue = new DeltaHantoGame(HantoPlayerColor.BLUE);
		
		int[] redInv  = inventoryCounts(red.getCurrentPlayer());
		int[] blueInv = inventoryCounts(blue.getCurrentPlayer());

		assertEquals(redInv[0], 1);
		assertEquals(blueInv[0], 1);
		
		assertEquals(redInv[1], 5);
		assertEquals(blueInv[1], 5);
		
		assertEquals(redInv[2], 0);
		assertEquals(blueInv[2], 0);
	}
	 
	
	
}
