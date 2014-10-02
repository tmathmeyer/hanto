/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.otnah.gamma;

import hanto.common.*;
import hanto.otnah.common.*;
import hanto.otnah.common.HantoTestGame;

/**
 * Test Gamma Hanto Game
 * @version Sep 22, 2014
 */
public class GammaHantoTestGame extends GammaHantoGame implements HantoTestGame
{
	public GammaHantoTestGame(HantoPlayerColor movesFirst)
	{
		super(movesFirst);
	}
	
	/*
	 * @see common.HantoTestGame#initializeBoard(common.HantoTestGame.PieceLocationPair[])
	 */
	@Override
	public void initializeBoard(PieceLocationPair[] initialPieces)
	{
		board.clear();
		for (PieceLocationPair plp : initialPieces) {
			board.put(HantoCoordinateFactory.makeCoordinate(plp.location),
					HantoPieceFactory.makePiece(plp.player, plp.pieceType));
			final HantoPlayerState hps = plp.player == HantoPlayerColor.BLUE 
					? bluePlayerState : redPlayerState;
			try {
				hps.getPieceFromInventory(plp.pieceType);
			} catch (HantoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/*
	 * @see common.HantoTestGame#setTurnNumber(int)
	 */
	@Override
	public void setTurnNumber(int turnNumber)
	{
		gameTurn = turnNumber;
	}

	/*
	 * @see common.HantoTestGame#setPlayerMoving(hanto.common.HantoPlayerColor)
	 */
	@Override
	public void setPlayerMoving(HantoPlayerColor player)
	{
		playerOnMove = player;
	}

}
