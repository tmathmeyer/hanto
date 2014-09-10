package hanto.otnah.alpha;

import hanto.common.HantoPlayerColor;
import hanto.otnah.common.HantoPlayer;
import hanto.otnah.common.HantoTile;
import hanto.otnah.common.util.CollectionUtils;

public class BlueHantoPlayer extends HantoPlayer
{

	public BlueHantoPlayer()
	{
		super(HantoPlayerColor.BLUE, CollectionUtils.toSetFromArray(
				HantoTile.class, new Butterfly(HantoPlayerColor.BLUE)));
	}

}
