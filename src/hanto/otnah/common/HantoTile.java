package hanto.otnah.common;

import java.util.Collection;

import hanto.common.HantoPiece;
import hanto.otnah.common.Position;
import hanto.common.HantoCoordinate;

public abstract class HantoTile implements HantoPiece{
	Position position;
	boolean played;
	
	public abstract void move(HantoCoordinate to);
	
	public abstract boolean isValidMove(Position to);
	
	public abstract Collection<HantoCoordinate> getValidMoves();
	
	public abstract Collection<HantoTile> getAdjacentPieces();
	
	public Position getPosition(){
		return position;
	}
	
	public boolean inPlay(){
		return played;
	}
	
}
