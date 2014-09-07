package hanto.otnah.common;

/**
 * 
 * @author ontnah
 *
 */
public class GameStateSingleton
{
	private final GameState gameState;
	private static GameStateSingleton INSTANCE;
	
	/**
	 * @param state the only state that can exist
	 */
	public GameStateSingleton(GameState state)
	{
		gameState = state;
		if (INSTANCE == null)
		{
			INSTANCE = this;
		}
	}
	
	/**
	 * @return the one and only game state
	 */
	public static GameState getInstance()
	{
		return getSingleton().getGameState();
	}
	
	/**
	 * @return the instance of this class
	 */
	public static GameStateSingleton getSingleton()
	{
		return INSTANCE;
	}

	/**
	 * @return the gameState
	 */
	public GameState getGameState() {
		return gameState;
	}
}
