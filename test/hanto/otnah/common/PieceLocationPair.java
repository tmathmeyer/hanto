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

import hanto.common.HantoCoordinate;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;

/**
 * A data structure used in setting up the the initial configuration of a game
 * for testing.
 * @version Sep 21, 2014
 */
public class PieceLocationPair {
	private final HantoPlayerColor player;
	private final HantoPieceType pieceType;
	private final HantoCoordinate location;
	
	/**
	 * Default constructor
	 * @param player the player color
	 * @param pieceType the piece type
	 * @param location the coordinate where the piece is at the beginning of the test
	 */
	public PieceLocationPair(HantoPlayerColor player, HantoPieceType pieceType,
			HantoCoordinate location)
	{
		this.player = player;
		this.pieceType = pieceType;
		this.location = location;
	}

	/**
	 * @return the player
	 */
	public HantoPlayerColor getPlayer() {
		return player;
	}

	/**
	 * @return the pieceType
	 */
	public HantoPieceType getPieceType() {
		return pieceType;
	}

	/**
	 * @return the location
	 */
	public HantoCoordinate getLocation() {
		return location;
	}
}