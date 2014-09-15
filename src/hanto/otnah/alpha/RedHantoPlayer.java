package hanto.otnah.alpha;

import hanto.common.HantoPlayerColor;
import hanto.otnah.common.HantoPlayer;
import hanto.otnah.common.HantoTile;
import hanto.otnah.common.util.CollectionUtils;

public class RedHantoPlayer extends HantoPlayer
{
	
	private HantoPlayer nextPlayer;
	
	@Override
	public HantoPlayer getNextPlayer() {
		return nextPlayer;
	}

	@Override
	public void setNextPlayer(HantoPlayer next) {
		nextPlayer = next;
	}
	
	public RedHantoPlayer()
	{
		super(HantoPlayerColor.RED, CollectionUtils.toSetFromArray(
				HantoTile.class, new Butterfly(HantoPlayerColor.RED)));
	}

}
