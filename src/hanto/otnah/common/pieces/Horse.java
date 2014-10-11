package hanto.otnah.common.pieces;

import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.otnah.common.HantoTile;

public class Horse extends HantoTile
{

	private final HantoPlayerColor myColor;
	
	/**
	 * The default constructor
	 * @param hpc the color of this piece
	 */
	public Horse(HantoPlayerColor hpc)
	{
		myColor = hpc;
	}
	
	@Override
	public HantoPlayerColor getColor() {
		return myColor;
	}

	@Override
	public HantoPieceType getType() {
		return HantoPieceType.CRAB;
	}

}
