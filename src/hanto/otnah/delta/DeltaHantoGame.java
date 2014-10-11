/*******************************************************************************
 * All sources under the hanto.otnah package were developed by
 * Ted Meyer and Nilesh Patel for the term project in CS4233
 * at Worcester Polytechnic Institute. Since WPI holds all other forms
 * of ownership to this software, we have decided to not make this
 * software available under any license. Evaluation or compilation rights
 * are therefore granted only to course staff.
 *******************************************************************************/


package hanto.otnah.delta;

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
import static hanto.common.HantoPlayerColor.RED;
import static hanto.common.HantoPlayerColor.BLUE;
import static hanto.otnah.common.LinkedHantoPlayerFactory.makeDeltaPlayers;


/**
 * 
 * @author otnah
 *
 */
public class DeltaHantoGame extends GameState
{
	public DeltaHantoGame(HantoPlayerColor player) {
		skipTo(player);
	}

	private LinkedHantoPlayer current = makeDeltaPlayers(RED, BLUE);

	@Override
	public void skipTo(HantoPlayerColor player)
	{
		current = current.skipTo(player);
	}
	
	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate fromHC,
			HantoCoordinate toHC) throws HantoException {
		if(pieceType == null && fromHC == null && toHC == null)
		{	
			return getCurrentPlayer().getNextPlayer().getWinningState();
		}
		if(isGameOver())
		{
			throw new HantoException("Game is over, no moves are allowed.");
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

	@Override
	public HantoPlayer<LinkedHantoPlayer> getCurrentPlayer()
	{
		return current;
	}

	@Override
	public boolean isMovePossible(Position from, Position to, HantoPieceType type, HantoPlayerColor color)
	{
		return getValidatorFactory().getMoveValidator(type).isValidMove(to, from, this);
	}

	@Override
	public boolean isOverMaxAllottedMoves(int currentMoveCount)
	{
		return false;
	}

	@Override
	public PieceMoveValidatorFactory getValidatorFactory()
	{
		return new PieceMoveValidatorFactory();
	}
}
