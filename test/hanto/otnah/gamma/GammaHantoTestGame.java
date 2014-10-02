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

import static hanto.otnah.common.Position.asPosition;

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
	
	@Override
	public void initializeBoard(PieceLocationPair[] initialPieces)
	{
		for(final PieceLocationPair plp : initialPieces)
		{
			if (plp.getPieceType() == HantoPieceType.BUTTERFLY)
			{
				getCurrentPlayer().getSelf().skipTo(plp.getPlayer())
				                  .setButterflyPosition(asPosition(plp.getLocation()));
			}
			setPieceAt(new HantoTile(){

				@Override
				public HantoPlayerColor getColor() {
					return plp.getPlayer();
				}

				@Override
				public HantoPieceType getType() {
					return plp.getPieceType();
				}
				
			}, plp.getLocation());
		}
	}

	/*
	 * @see common.HantoTestGame#setTurnNumber(int)
	 */
	@Override
	public void setTurnNumber(int turnNumber)
	{
		for(int i = 0; i < turnNumber; i++)
		{
			getCurrentPlayer().increaseMoveCount();
		}
		for(int i = 0; i < turnNumber; i++)
		{
			getCurrentPlayer().getNextPlayer().increaseMoveCount();
		}
	}

	/*
	 * @see common.HantoTestGame#setPlayerMoving(hanto.common.HantoPlayerColor)
	 */
	@Override
	public void setPlayerMoving(HantoPlayerColor player)
	{
		//TODO: useless method
	}

}
