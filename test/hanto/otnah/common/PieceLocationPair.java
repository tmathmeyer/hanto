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