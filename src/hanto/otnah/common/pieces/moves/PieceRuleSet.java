package hanto.otnah.common.pieces.moves;

public interface PieceRuleSet
{
	/**
	 * @return the butterfly validator
	 */
	PieceMoveValidator makeButterflyMoveValidator();
	
	/**
	 * @return the sparrow validator
	 */
	PieceMoveValidator makeSparrowMoveValidator();
	
	/**
	 * @return the crab validator
	 */
	PieceMoveValidator makeCrabMoveValidator();
	
	/**
	 * @return the horse validator
	 */
	PieceMoveValidator makeHorseMoveValidator();
}
