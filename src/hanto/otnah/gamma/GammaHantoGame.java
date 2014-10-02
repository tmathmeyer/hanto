/*******************************************************************************
 * All sources under the hanto.otnah package were developed by
 * Ted Meyer and Nilesh Patel for the term project in CS4233
 * at Worcester Polytechnic Institute. Since WPI holds all other forms
 * of ownership to this software, we have decided to not make this
 * software available under any license. Evaluation or compilation rights
 * are therefore granted only to course staff.
 *******************************************************************************/


package hanto.otnah.gamma;

import java.util.Collection;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.otnah.common.GameState;
import hanto.otnah.common.HantoPlayer;
import hanto.otnah.common.HantoTile;
import hanto.otnah.common.Position;
import hanto.otnah.common.pieces.moves.PieceMoveValidatorFactory;


/**
 * 
 * @author otnah
 *
 */
public class GammaHantoGame extends GameState
{
	private GammaPlayer current;
	private final GammaPlayer red = new GammaPlayer(HantoPlayerColor.RED);
	private final GammaPlayer blue = new GammaPlayer(HantoPlayerColor.BLUE);

	/**
	 * default constructor
	 * 
	 * @param firstPlayer the color pf the player that goes first
	 */
	public GammaHantoGame(HantoPlayerColor firstPlayer)
	{
		red.setNextPlayer(blue);
		blue.setNextPlayer(red);
		switch (firstPlayer)
		{
			case BLUE:
				current = blue;
				break;
			case RED:
				current = red;
				break;	
		}
	}

	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate fromHC,
			HantoCoordinate toHC) throws HantoException {
		
		Position   to = Position.asPosition(toHC);
		Position from = Position.asPosition(fromHC);
		
		if(isMovePossible(from, to, pieceType, getCurrentPlayer().getColor()))
		{
			//remove piece from inventory
			HantoTile played = removeFrom(from, pieceType);
			
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
	
	/**
	 * 
	 * @param pos the position from which to remove the piece
	 * @param type the type of piece to remove
	 * @return the piece that has been removed
	 */
	public HantoTile removeFrom(Position pos, HantoPieceType type)
	{
		return pos.removePieceAt(this, type);
	}

	@Override
	public HantoPlayer<GammaPlayer> getCurrentPlayer()
	{
		return current;
	}

	@Override
	public boolean isMovePossible(Position from, Position to, HantoPieceType type, HantoPlayerColor color)
	{
		return PieceMoveValidatorFactory.getMoveValidator(type).isValidMove(to, from, this);
	}
	
	private MoveResult gameState()
	{
		MoveResult returnValue = MoveResult.OK;
		if(isSurrounded(red.getButterflyPosition()))
		{
			returnValue = MoveResult.BLUE_WINS;
		}
		if(isSurrounded(blue.getButterflyPosition()))
		{
			if(returnValue == MoveResult.BLUE_WINS)
			{
				returnValue = MoveResult.DRAW;
			}
			else
			{
				returnValue = MoveResult.RED_WINS;
			}
		}
		if(red.getMovesPlayed() + blue.getMovesPlayed() == 12)
		{
			returnValue = MoveResult.DRAW;
		}
		return returnValue;
	}
	
	private boolean isSurrounded(Position toCheck)
	{
		Collection<HantoCoordinate> positions = toCheck.adjacentCoordinates();
		if(positions.size() < 6)
		{
			return false;
		}
		for(HantoCoordinate c : positions)
		{
			if(getPieceAt(c) == null)
			{
				return false;
			}
		}
		return true;
	}
	
}
