package hanto.otnah.epsilon;

import static hanto.common.HantoPlayerColor.BLUE;
import static hanto.common.HantoPlayerColor.RED;
import static hanto.otnah.common.LinkedHantoPlayerFactory.makeEpsilonPlayers;
import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.otnah.common.GameState;
import hanto.otnah.common.HantoPlayer;
import hanto.otnah.common.HantoTile;
import hanto.otnah.common.LinkedHantoPlayer;
import hanto.otnah.common.Position;
import hanto.otnah.common.pieces.moves.PieceMoveValidatorFactory;

public class EpsilonHantoGame extends GameState
{

	public EpsilonHantoGame(HantoPlayerColor player) {
		skipTo(player);
	}

	private LinkedHantoPlayer current = makeEpsilonPlayers(RED, BLUE);

	@Override
	public void skipTo(HantoPlayerColor player)
	{
		current = current.skipTo(player);
	}
	
	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate fromHC,
			HantoCoordinate toHC) throws HantoException {
		
		if (checkResignation(pieceType, fromHC, toHC))
		{
			return tryResignation();
		}
		
		Position   to = Position.asPosition(toHC);
		Position from = Position.asPosition(fromHC);
		
		if(isMovePossible(from, to, pieceType, getCurrentPlayer().getColor()))
		{
			//remove piece from inventory
			HantoTile played = from.removePieceAt(this, pieceType);
			
			//put piece on board
			setPieceAt(played, to);
			
			//increment player count
			getCurrentPlayer().increaseMoveCount();
			
			if(pieceType == HantoPieceType.BUTTERFLY)
			{
				getCurrentPlayer().getSelf().setButterflyPosition(to);
			}
			
			//switch player
			current = current.getNextPlayer();
			return gameState();
		}
		
		throw new HantoException("invalid move!");
	}
	
	private boolean checkResignation(Object a, Object b, Object c)
	{
		return a == null && b == null && c == null;
	}
	
	private MoveResult tryResignation()
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
