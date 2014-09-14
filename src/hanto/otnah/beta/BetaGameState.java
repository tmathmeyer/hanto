package hanto.otnah.beta;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.otnah.common.GameState;
import hanto.otnah.common.HantoPlayer;

public class BetaGameState extends GameState
{
	private PlayerTurnState current;
	
	public BetaGameState(HantoPlayerColor firstPlayer) throws HantoException {
		current = PlayerTurnState.fromColor(firstPlayer);
	}

	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from,
			HantoCoordinate to) throws HantoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HantoPlayer getCurrentPlayer() {
		return current.getHantoPlayer();
	}

	@Override
	public boolean isMovePossible(HantoCoordinate from, HantoCoordinate to,
			HantoPieceType type) {
		// TODO Auto-generated method stub
		return false;
	}

	public static BetaGameState createBetaGameState(HantoPlayerColor firstPlayer) throws HantoException
	{
		return new BetaGameState(firstPlayer);
	}
}
