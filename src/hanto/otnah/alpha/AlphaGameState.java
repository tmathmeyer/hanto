package hanto.otnah.alpha;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.MoveResult;
import hanto.otnah.common.GameState;
import hanto.otnah.common.HantoPlayer;

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
			//move
			return MoveResult.OK;
		}
		
		
		throw new HantoException("implement me");
	}

	@Override
	public HantoPlayer getCurrentPlayer()
	{
		return which ? redPlayer : bluePlayer;
	}

	@Override
	public boolean isMovePossible(HantoCoordinate from, HantoCoordinate to, HantoPieceType type)
	{
		// TODO Auto-generated method stub
		return false;
	}

	public static AlphaGameState defaultAGS()
	{
		HantoPlayer redPlayer = new RedHantoPlayer();
		HantoPlayer bluePlayer = new BlueHantoPlayer();
		
		return new AlphaGameState(redPlayer, bluePlayer);
	}
}
