package hanto.otnah.alpha;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.otnah.common.GameState;
import hanto.otnah.common.HantoPlayer;
import hanto.otnah.common.Position;
import hanto.otnah.common.util.HexUtil;

public class AlphaGameState extends GameState
{
	private final HantoPlayer redPlayer, bluePlayer;
	private boolean which;
	
	public AlphaGameState(final HantoPlayer red, final HantoPlayer blue)
	{
		redPlayer = red;
		bluePlayer = blue;
		
		which = true;
	}
	
	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from, HantoCoordinate to) throws HantoException
	{
		if (isMovePossible(from, to, pieceType))
		{
			if (which)
			{ // blue, red can go
				setPieceAt(new Butterfly(HantoPlayerColor.BLUE) , to);
				which = false;
				return MoveResult.OK;
			}
			else
			{ // red, game over
				setPieceAt(new Butterfly(HantoPlayerColor.RED) , to);
				return MoveResult.DRAW;
			}
		}
		
		throw new HantoException("bad move!");
	}

	@Override
	public HantoPlayer getCurrentPlayer()
	{
		return !which ? redPlayer : bluePlayer;
	}

	@Override
	public boolean isMovePossible(HantoCoordinate from, HantoCoordinate to, HantoPieceType type)
	{
		int distance = Position.asPosition(from).getDistanceTo(to);
		boolean result = false;
		if (distance == 0)
		{
			if (which)
			{ // color is blue
				result = (0 == HexUtil.distance(to, new AlphaPosition(0, 0)));
			}
			else
			{ // color is red
				result = (1 == HexUtil.distance(to, new AlphaPosition(0, 0)));
			}
		}
		return result;
	}

	public static AlphaGameState defaultAGS()
	{
		HantoPlayer redPlayer = new RedHantoPlayer();
		HantoPlayer bluePlayer = new BlueHantoPlayer();
		
		return new AlphaGameState(redPlayer, bluePlayer);
	}
}
