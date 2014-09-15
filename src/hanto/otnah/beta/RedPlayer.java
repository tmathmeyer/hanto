package hanto.otnah.beta;

import hanto.common.HantoPlayerColor;
import hanto.otnah.common.HantoPlayer;
import hanto.otnah.common.HantoTile;
import hanto.otnah.common.util.CollectionUtils;
import hanto.otnah.common.util.CollectionUtils.Factory;

public class RedPlayer extends HantoPlayer
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
	
	public RedPlayer() {
		super(HantoPlayerColor.RED, CollectionUtils.toSetFromFactoryArray(
				HantoTile.class, Factory.norm(Butterfly.class, 1), Factory.norm(Sparrow.class, 5)));
	}

}
