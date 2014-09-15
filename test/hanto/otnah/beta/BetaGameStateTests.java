package hanto.otnah.beta;

import static org.junit.Assert.*;
import hanto.common.HantoException;
import hanto.common.HantoPlayerColor;
import hanto.otnah.common.HantoTile;

import org.junit.Test;

public class BetaGameStateTests
{
	@Test
	public void canStartTest() throws HantoException
	{
		assertNotNull(BetaGameState.createBetaGameState(HantoPlayerColor.RED));
		assertNotNull(BetaGameState.createBetaGameState(HantoPlayerColor.BLUE));
	}

	@Test(expected=HantoException.class)
	public void needsValidPlayerColor() throws HantoException
	{
		assertNotNull(BetaGameState.createBetaGameState(null));
	}
	
	@Test
	public void hasProperStartingInventoryTest() throws HantoException
	{
		BetaGameState beta = BetaGameState.createBetaGameState(HantoPlayerColor.BLUE);
		
		int butterflies = 0, sparrows = 0, other = 0;
		
		for(HantoTile t : beta.getCurrentPlayer().getInventory())
		{
			switch(t.getType())
			{
				case BUTTERFLY:
					butterflies ++;
					break;
				case SPARROW:
					sparrows ++;
					break;
				default:
					other ++;
					break;
			}
		}

		assertEquals(butterflies, 1);
		assertEquals(sparrows, 5);
		assertEquals(other, 0);
	}
	
	
	
}
