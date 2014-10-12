package hanto.otnah.beta;

import hanto.common.HantoPieceType;
import hanto.otnah.common.Position;
import hanto.otnah.common.pieces.moves.PieceMoveValidator;
import hanto.otnah.common.pieces.moves.PieceRuleSet;

public class BetaHantoRules extends PieceMoveValidator implements PieceRuleSet
{
	private HantoPieceType type;
	
	public BetaHantoRules()
	{
		this(HantoPieceType.BUTTERFLY);
	}
	
	public BetaHantoRules(HantoPieceType hpt)
	{
		type = hpt;
	}
	
	@Override
	public PieceMoveValidator makeButterflyMoveValidator() {
		return new BetaHantoRules();
	}

	@Override
	public PieceMoveValidator makeSparrowMoveValidator() {
		return new BetaHantoRules(HantoPieceType.SPARROW);
	}

	@Override
	public PieceMoveValidator makeCrabMoveValidator() {
		return new BetaHantoRules(HantoPieceType.CRAB);
	}

	@Override
	public PieceMoveValidator makeHorseMoveValidator() {
		return new BetaHantoRules(HantoPieceType.HORSE);
	}

	@Override
	protected boolean isValidMove(Position to, Position from) {
		Position toPos = Position.asPosition(to);
		boolean movePossible = true;
		if (!hasPieceInInventory(type))
		{
			return false;
		}
		
		movePossible = checkButterflyLegality(type, 3);
		
		return movePossible && (isFirstMove(toPos) || 
				(piecePlaceContinuityCheck(toPos) && isLocationUnoccupied(toPos)));
	}

}
