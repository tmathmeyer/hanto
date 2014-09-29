/*******************************************************************************
 * All sources under the hanto.otnah package were developed by
 * Ted Meyer and Nilesh Patel for the term project in CS4233
 * at Worcester Polytechnic Institute. Since WPI holds all other forms
 * of ownership to this software, we have decided to not make this
 * software available under any license. Evaluation or compilation rights
 * are therefore granted only to course staff.
 *******************************************************************************/

package hanto.otnah.alpha;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.otnah.common.GameState;
import hanto.otnah.common.HantoPlayer;
import hanto.otnah.common.Position;
import hanto.otnah.common.HexPosition;

/**
 * 
 * @author otnah
 *
 */
public class AlphaHantoGame extends GameState
{
	private final AlphaHantoPlayer redPlayer, bluePlayer;
	private boolean which;
	
	/**
	 * normal constructor for AlphaHantoGame
	 * @param red the red player
	 * @param blue the blue player
	 */
	public AlphaHantoGame(final AlphaHantoPlayer red, final AlphaHantoPlayer blue)
	{
		redPlayer = red;
		bluePlayer = blue;
		
		which = true;
	}
	
	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from, HantoCoordinate to) throws HantoException
	{
		if (pieceType != HantoPieceType.BUTTERFLY)
		{
			throw new HantoException("piece: " + (pieceType==null?"null":pieceType.getPrintableName()) + " not recognized");
		}
		
		if (isMovePossible(Position.asPosition(from), Position.asPosition(to), pieceType, null))
		{
			if (which)
			{ // blue: red can go
				setPieceAt(new Butterfly(HantoPlayerColor.BLUE), to);
				which = false;
				return MoveResult.OK;
			}
			else
			{ // red: game is over
				setPieceAt(new Butterfly(HantoPlayerColor.RED), to);
				return MoveResult.DRAW;
			}
		}
		
		throw new HantoException("bad move!");
	}

	@Override
	public HantoPlayer<AlphaHantoPlayer> getCurrentPlayer()
	{
		return !which ? redPlayer : bluePlayer;
	}

	@Override
	public boolean isMovePossible(Position from, Position to, HantoPieceType type, HantoPlayerColor color)
	{
		final int distance = Position.asPosition(from).getDistanceTo(Position.asPosition(to));
		boolean result = false;
		if (distance == 0)
		{
			final int whichDistance = new HexPosition(0, 0).getDistanceTo(Position.asPosition(to));
			if (which)
			{ // color is blue
				result = (whichDistance == 0);
			}
			else
			{ // color is red
				result = (whichDistance == 1);
			}
		}
		return result;
	}

	/**
	 * get the default alpha game state
	 * @return the default game
	 */
	public static AlphaHantoGame defaultAGS()
	{
		AlphaHantoPlayer redPlayer = new AlphaHantoPlayer(HantoPlayerColor.RED);
		AlphaHantoPlayer bluePlayer = new AlphaHantoPlayer(HantoPlayerColor.BLUE);
		
		return new AlphaHantoGame(redPlayer, bluePlayer);
	}
}
