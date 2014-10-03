package hanto.otnah.common;

import hanto.common.HantoPlayerColor;
import hanto.otnah.common.pieces.Butterfly;
import hanto.otnah.common.pieces.Crab;
import hanto.otnah.common.pieces.Sparrow;
import static hanto.otnah.common.util.CollectionUtils.makeInventory;
import static hanto.otnah.common.util.CollectionUtils.with;
import static hanto.otnah.common.LinkedHantoPlayer.makePlayer;

public class LinkedHantoPlayerFactory
{
	public static LinkedHantoPlayer makeGammaPlayers(HantoPlayerColor ... colors)
	{
		return makeLinkedPlayers(new PlayerGen(){
			@Override
			public LinkedHantoPlayer gen(HantoPlayerColor each) {
				return makePlayer(each, makeInventory(HantoTile.class,
						 				with(Butterfly.class, 1, each),
						 				with(Sparrow.class, 5, each)));
			}
		}, colors);
		
		
		
		
	}
	
	public static LinkedHantoPlayer makeDeltaPlayers(HantoPlayerColor ... colors)
	{
		return makeLinkedPlayers(new PlayerGen(){
			@Override
			public LinkedHantoPlayer gen(HantoPlayerColor each) {
				return makePlayer(each, makeInventory(HantoTile.class,
						 		 		with(Crab.class, 4, each),
						 		 		with(Butterfly.class, 1, each),
						 		 		with(Sparrow.class, 4, each)));
			}
		}, colors);
		
	}
	
	private static LinkedHantoPlayer makeLinkedPlayers(PlayerGen gen, HantoPlayerColor ... colors)
	{
		LinkedHantoPlayer first=null, last=null;
		
		for(HantoPlayerColor each : colors)
		{
			LinkedHantoPlayer current = gen.gen(each);
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
	
	private static interface PlayerGen
	{
		public LinkedHantoPlayer gen(HantoPlayerColor hpc);
	}
}
