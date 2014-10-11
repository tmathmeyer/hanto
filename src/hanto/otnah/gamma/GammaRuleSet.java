package hanto.otnah.gamma;

import hanto.otnah.common.pieces.moves.ButterflyMoveValidator;
import hanto.otnah.common.pieces.moves.CrabMoveValidator;
import hanto.otnah.common.pieces.moves.HorseMoveValidator;
import hanto.otnah.common.pieces.moves.PieceMoveValidator;
import hanto.otnah.common.pieces.moves.PieceRuleSet;

public class GammaRuleSet implements PieceRuleSet
{

	@Override
	public PieceMoveValidator makeButterflyMoveValidator()
	{
		return new ButterflyMoveValidator();
	}
	
	@Override
	public PieceMoveValidator makeSparrowMoveValidator()
	{
		return new GammaSparrowMoveValidator();
	}
	
	@Override
	public PieceMoveValidator makeCrabMoveValidator()
	{
		return new CrabMoveValidator();
	}
	
	@Override
	public PieceMoveValidator makeHorseMoveValidator()
	{
		return new HorseMoveValidator();
	}

}
