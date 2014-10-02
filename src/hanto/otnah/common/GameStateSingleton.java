/*******************************************************************************
 * All sources under the hanto.otnah package were developed by
 * Ted Meyer and Nilesh Patel for the term project in CS4233
 * at Worcester Polytechnic Institute. Since WPI holds all other forms
 * of ownership to this software, we have decided to not make this
 * software available under any license. Evaluation or compilation rights
 * are therefore granted only to course staff.
 *******************************************************************************/

package hanto.otnah.common;

/**
 * 
 * @author ontnah
 *
 */
public class GameStateSingleton
{
	private final GameState gameState;
	private static GameStateSingleton INSTANCE = null;
	
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
}
