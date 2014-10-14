package hanto.otnah.tournament;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import hanto.common.HantoException;
import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.otnah.common.InventoryPosition;
import hanto.otnah.common.moves.MoveEnumerator;
import hanto.otnah.common.moves.PotentialMove;
import hanto.otnah.epsilon.EpsilonHantoGame;
import hanto.tournament.HantoGamePlayer;
import hanto.tournament.HantoMoveRecord;

public class HantoPlayer implements HantoGamePlayer
{
	EpsilonHantoGame game;
	
	@Override
	public void startGame(HantoGameID version, HantoPlayerColor myColor, boolean doIMoveFirst)
	{
		if (doIMoveFirst)
		{
			game = new EpsilonHantoGame(myColor);
		}
		else
		{
			game = new EpsilonHantoGame(other(myColor));
		}
	}
	
	private final HantoPlayerColor other(HantoPlayerColor col)
	{
		HantoPlayerColor result = null;
		switch(col)
		{
			case BLUE:
				result = HantoPlayerColor.RED;
			case RED:
				result = HantoPlayerColor.BLUE;
		}
		return result;
	}
	

	@Override
	public HantoMoveRecord makeMove(HantoMoveRecord opponentsMove)
	{
		try
		{
			if (opponentsMove != null)
			{
				game.makeMove(opponentsMove.getPiece(), opponentsMove.getFrom(), opponentsMove.getTo());
				System.out.println(game.getPrintableBoard());
			}
			
			Collection<PotentialMove> allMoves = new MoveEnumerator().getAllCurrentMoves(game);
			
			if (allMoves.size() == 0)
			{
				System.out.println("resigning");
				throw new HantoException("there are no more moves, resign");
			}
			
			HantoMoveRecord hmr = rank(allMoves).iterator().next().asHantoMoveRecord();
			
			game.makeMove(hmr.getPiece(), hmr.getFrom(), hmr.getTo());
			System.out.println(game.getPrintableBoard());
			
			return hmr;
		}
		catch (HantoException e)
		{
			return new HantoMoveRecord(null, null, null);
		}
		
		
		
	}

	private Collection<PotentialMove> rank(Collection<PotentialMove> allMoves) {
		
		List<PotentialMove> result = new ArrayList<>(allMoves);
		
		for(PotentialMove p : result)
		{
			if (p.getFrom() instanceof InventoryPosition && p.getType() == HantoPieceType.BUTTERFLY)
			{
				List<PotentialMove> nr = new LinkedList<>();
				nr.add(p);
				return nr;
			}
		}
		
		return result;
	}

}
