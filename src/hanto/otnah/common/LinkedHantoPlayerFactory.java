package hanto.otnah.common;

import hanto.common.HantoPlayerColor;
import hanto.otnah.common.pieces.Butterfly;
import hanto.otnah.common.pieces.Sparrow;
import static hanto.otnah.common.util.CollectionUtils.makeInventory;
import static hanto.otnah.common.util.CollectionUtils.with;
import static hanto.otnah.common.LinkedHantoPlayer.makePlayer;

public class LinkedHantoPlayerFactory
{
	public static LinkedHantoPlayer makeGammaPlayers(HantoPlayerColor ... colors)
	{
		LinkedHantoPlayer first=null, last=null;
		
		for(HantoPlayerColor each : colors)
		{
			LinkedHantoPlayer current = makePlayer(each, makeInventory(HantoTile.class,
														 with(Butterfly.class, 1, each),
														 with(Sparrow.class, 5, each)));
			current.setNextPlayer(last);
			last = current;
			if (first == null)
			{
				first = current;
			}
		}
		
		first.setNextPlayer(last);
		
		return first;
	}
}
