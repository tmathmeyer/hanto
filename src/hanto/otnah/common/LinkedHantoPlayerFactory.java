/*******************************************************************************
 * All sources under the hanto.otnah package were developed by
 * Ted Meyer and Nilesh Patel for the term project in CS4233
 * at Worcester Polytechnic Institute. Since WPI holds all other forms
 * of ownership to this software, we have decided to not make this
 * software available under any license. Evaluation or compilation rights
 * are therefore granted only to course staff.
 *******************************************************************************/


package hanto.otnah.common;

import hanto.common.HantoPlayerColor;
import hanto.otnah.common.pieces.Butterfly;
import hanto.otnah.common.pieces.Crab;
import hanto.otnah.common.pieces.Sparrow;
import static hanto.otnah.common.util.CollectionUtils.makeInventory;
import static hanto.otnah.common.util.CollectionUtils.with;
import static hanto.otnah.common.LinkedHantoPlayer.makePlayer;

/**
 * 
 * @author otnah
 * 
 * factory for generating linked players
 *
 */
public class LinkedHantoPlayerFactory
{
	/**
	 * @param colors the players as colors
	 * @return the players linked together
	 */
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
	
	/**
	 * @param colors the colors of the players
	 * @return the players linked together
	 */
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
		if (first != null)
		{
			first.setNextPlayer(last);
		}
		
		return first;
	}
	
	/**
	 * 
	 * @author otnah
	 *
	 */
	private static interface PlayerGen
	{
		LinkedHantoPlayer gen(HantoPlayerColor hpc);
	}
}
