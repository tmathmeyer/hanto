/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentotnah;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import hanto.common.*;
import hanto.studentotnah.alpha.AlphaHantoGame;
import hanto.studentotnah.beta.BetaHantoGame;
import hanto.studentotnah.delta.DeltaHantoGame;
import hanto.studentotnah.epsilon.EpsilonHantoGame;
import hanto.studentotnah.gamma.GammaHantoGame;

/**
 * This is a singleton class that provides a factory to create an instance of any version
 * of a Hanto game.
 * 
 * @author gpollice
 * @version Feb 5, 2013
 */
public class HantoGameFactory
{
	private static final HantoGameFactory INSTANCE = new HantoGameFactory();
	
	/**
	 * Default private descriptor.
	 */
	private HantoGameFactory()
	{
		// Empty, but the private constructor is necessary for the singleton.
	}

	/**
	 * @return the instance
	 */
	public static HantoGameFactory getInstance()
	{
		return INSTANCE;
	}
	
	/**
	 * Create the specified Hanto game version with the Blue player moving
	 * first.
	 * @param gameId the version desired.
	 * @return the game instance
	 */
	public HantoGame makeHantoGame(HantoGameID gameId)
	{
		return makeHantoGame(gameId, HantoPlayerColor.BLUE);
	}
	
	/**
	 * Factory method that returns the appropriately configured Hanto game.
	 * @param gameId the version desired.
	 * @param movesFirst the player color that moves first
	 * @return the game instance
	 */
	public HantoGame makeHantoGame(HantoGameID gameId, HantoPlayerColor movesFirst) {
		final HantoGame game;
		switch (gameId) {
			case ALPHA_HANTO:
				game = new AlphaHantoGame();
				break;
			case BETA_HANTO:
				game = new BetaHantoGame(movesFirst);
				break;
			case GAMMA_HANTO:
				game = new GammaHantoGame(movesFirst);
				break;
			case DELTA_HANTO:
				game = new DeltaHantoGame(movesFirst);
				break;
			case EPSILON_HANTO:
				game = new EpsilonHantoGame(movesFirst);
				break;
			default:
				throw new NotImplementedException();
		}
		return game;
	}
}
