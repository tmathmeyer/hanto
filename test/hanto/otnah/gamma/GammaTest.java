package hanto.otnah.gamma;

import static org.junit.Assert.*;
import hanto.common.HantoGameID;
import hanto.common.HantoPlayerColor;
import hanto.otnah.HantoGameFactory;
import hanto.otnah.common.GameState;
import hanto.otnah.common.HantoPlayer;
import hanto.otnah.common.HantoTile;

import org.junit.Test;

public class GammaTest {

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
	
	
	@Test
	public void canGammaBeMade()
	{
		GameState red = (GameState) HantoGameFactory.getInstance().makeHantoGame(HantoGameID.GAMMA_HANTO, HantoPlayerColor.RED);
		GameState blue = (GameState) HantoGameFactory.getInstance().makeHantoGame(HantoGameID.GAMMA_HANTO, HantoPlayerColor.BLUE);
		
		assertTrue(red.getCurrentPlayer().getColor().equals(HantoPlayerColor.RED));
		assertTrue(blue.getCurrentPlayer().getColor().equals(HantoPlayerColor.BLUE));
	}
	
	@Test
	public void properStartingInventories()
	{
		GameState red = new GammaHantoGame(HantoPlayerColor.RED);
		GameState blue = new GammaHantoGame(HantoPlayerColor.BLUE);
		
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
