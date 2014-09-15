package hanto.otnah.beta;

import hanto.common.HantoException;
import hanto.common.HantoPlayerColor;
import hanto.otnah.common.HantoPlayer;

/**
 * @author otnah
 *
 * a simple enum for checking player's turns, instead of a boolean
 */
public enum PlayerTurnState
{
	
	
	RED(new RedPlayer()),
	BLUE(new BluePlayer());
	
	// the wrapped player
	private final HantoPlayer hp;
	
	PlayerTurnState(HantoPlayer h)
	{
		hp = h;
	}
	
	/**
	 * 
	 * @return the next player (alternating);
	 */
	public PlayerTurnState getNext()
	{
		if (this == RED)
		{
			return BLUE;
		}
		return RED;
	}
	
	/**
	 * get the current player
	 * @return the hanto player wrapped by this enum
	 */
	public HantoPlayer getHantoPlayer()
	{
		return hp;
	}

	public static PlayerTurnState fromColor(HantoPlayerColor firstPlayer) throws HantoException {
		PlayerTurnState result;
		if (firstPlayer == null)
		{
			throw new HantoException("invalid first player color");
		}
		switch(firstPlayer)
		{
			case BLUE:
				result = BLUE;
				break;
			case RED:
				result = RED;
				break;
			default:
				throw new HantoException("invalid first player color");
		}
		
		return result;
	}
}
