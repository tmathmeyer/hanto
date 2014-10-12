package hanto.otnah.epsilon;

import static hanto.common.HantoPlayerColor.BLUE;
import static hanto.common.HantoPlayerColor.RED;
import static hanto.otnah.common.LinkedHantoPlayerFactory.makeEpsilonPlayers;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.otnah.common.GameState;
import hanto.otnah.common.HantoPlayer;
import hanto.otnah.common.LinkedHantoPlayer;
import hanto.otnah.common.Position;
import hanto.otnah.common.pieces.moves.PieceMoveValidatorFactory;

public class EpsilonHantoGame extends GameState
{

	public EpsilonHantoGame(HantoPlayerColor player) {
		skipTo(player);
	}

	private LinkedHantoPlayer current = makeEpsilonPlayers(BLUE, RED);

	@Override
	public void skipTo(HantoPlayerColor player)
	{
		current = current.skipTo(player);
	}
	
	@Override
	public MoveResult tryResignation()
	{
		//TODO: implement brute force resignation checker
		
		return getCurrentPlayer().getSelf().getLosingState();
	}
	
	
	
	
	

	@Override
	public HantoPlayer<LinkedHantoPlayer> getCurrentPlayer() {
		return current;
	}

	@Override
	public boolean isMovePossible(Position from, Position to,
			HantoPieceType type, HantoPlayerColor color) {
		return getValidatorFactory().getMoveValidator(type).isValidMove(to, from, this);
	}

	@Override
	public boolean isOverMaxAllottedMoves(int currentMoveCount) {
		return false;
	}

	@Override
	public PieceMoveValidatorFactory getValidatorFactory()
	{
		return new PieceMoveValidatorFactory();
	}

}
