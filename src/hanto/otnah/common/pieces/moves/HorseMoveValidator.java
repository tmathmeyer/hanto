package hanto.otnah.common.pieces.moves;

import hanto.otnah.common.Position;

public class HorseMoveValidator extends PieceMoveValidator {

	@Override
	protected boolean isValidMove(Position to, Position from)
	{
		return false;
	}

}
