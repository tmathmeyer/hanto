package hanto.otnah.beta;

import java.util.Collection;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.otnah.common.HantoTile;
import hanto.otnah.common.Position;

public class Butterfly extends HantoTile
{

	@Override
	public HantoPlayerColor getColor() {
		return null;
	}

	@Override
	public HantoPieceType getType() {
		return HantoPieceType.BUTTERFLY;
	}

	@Override
	public void move(HantoCoordinate to) {
		// Does nothing since the Butterfly can't actually move
		
	}

	@Override
	public boolean isValidMove(Position to) {
		return false;
	}

	@Override
	public Collection<HantoCoordinate> getAdjacentPositions() {
		return null;
	}

}
