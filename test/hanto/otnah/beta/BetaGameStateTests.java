package hanto.otnah.beta;

import static org.junit.Assert.*;
import hanto.common.HantoException;
import hanto.common.HantoPlayerColor;

import org.junit.Test;

public class BetaGameStateTests
{
	@Test
	public void ProperStartingTest() throws HantoException
	{
		assertNotNull(BetaGameState.createBetaGameState(HantoPlayerColor.RED));
		assertNotNull(BetaGameState.createBetaGameState(HantoPlayerColor.BLUE));
	}
}
