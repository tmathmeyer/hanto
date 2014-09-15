package hanto.otnah.beta;

import java.util.Collection;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.otnah.common.HantoTile;
import hanto.otnah.common.Position;

public class Sparrow extends HantoTile
{

	@Override
	public HantoPlayerColor getColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HantoPieceType getType() {
		return HantoPieceType.SPARROW;
	}

	@Override
	public void move(HantoCoordinate to) {
		// Empty, since the sparrow can't actually move
		
	}

	@Override
	public boolean isValidMove(Position to) {
		// Always return false, since the sparrow can never move
		return false;
	}

	@Override
	public Collection<HantoCoordinate> getAdjacentPositions() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
