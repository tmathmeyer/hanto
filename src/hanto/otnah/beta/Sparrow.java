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
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValidMove(Position to) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<HantoCoordinate> getAdjacentPositions() {
		// TODO Auto-generated method stub
		return null;
	}
	
}