package hanto.otnah.alpha;

import hanto.common.HantoPlayerColor;
import hanto.otnah.common.HantoPlayer;
import hanto.otnah.common.HantoTile;
import hanto.otnah.common.util.CollectionUtils;

public class AlphaHantoPlayer extends HantoPlayer<AlphaHantoPlayer>
{
	
	private AlphaHantoPlayer nextPlayer;
	
	@Override
	public AlphaHantoPlayer getNextPlayer() {
		return nextPlayer;
	}

	@Override
	public void setNextPlayer(AlphaHantoPlayer next) {
		nextPlayer = next;
	}
	
	public AlphaHantoPlayer(HantoPlayerColor playerColor)
	{
		super(playerColor, CollectionUtils.toSetFromArray(
				HantoTile.class, new Butterfly(HantoPlayerColor.BLUE)));
	}

	@Override
	public AlphaHantoPlayer getSelf()
	{
		return this;
	}

}
