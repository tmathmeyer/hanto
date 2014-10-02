/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.otnah.common;

import hanto.common.*;

/**
 * Description
 * @version Sep 21, 2014
 */
public interface HantoTestGame extends HantoGame
{
	/**
	 * Initialize the pieces on the board. This will put the pieces on the board as
	 * well as updating the store of pieces that are off the board.
	 * @param initialPieces an array of initial pieces
	 */
	void initializeBoard(PieceLocationPair[] initialPieces);
	
	/**
	 * Set the current turn number (whole turn number) beginning at 1
	 * @param turnNumber
	 */
	void setTurnNumber(int turnNumber);
	
	/**
	 * Set the player on the move. This is the player who will next call makeMove().
	 * @param player the player who will make the next move
	 */
	void setPlayerMoving(HantoPlayerColor player);
}
