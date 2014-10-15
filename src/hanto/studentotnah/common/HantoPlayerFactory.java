/*******************************************************************************
 * All sources under the hanto.otnah package were developed by
 * Ted Meyer and Nilesh Patel for the term project in CS4233
 * at Worcester Polytechnic Institute. Since WPI holds all other forms
 * of ownership to this software, we have decided to not make this
 * software available under any license. Evaluation or compilation rights
 * are therefore granted only to course staff.
 *******************************************************************************/


package hanto.studentotnah.common;

import hanto.common.HantoPlayerColor;
import hanto.studentotnah.common.pieces.Butterfly;
import hanto.studentotnah.common.pieces.Crab;
import hanto.studentotnah.common.pieces.Horse;
import hanto.studentotnah.common.pieces.Sparrow;
import static hanto.studentotnah.common.HantoPlayer.makePlayer;
import static hanto.studentotnah.common.util.CollectionUtils.makeInventory;
import static hanto.studentotnah.common.util.CollectionUtils.with;

/**
 * 
 * @author otnah
 * 
 * factory for generating linked players
 *
 */
public class HantoPlayerFactory
{
	/**
	 * @param colors the array of colors for each player
	 * @return the linked players
	 */
	public static HantoPlayer makeAlphaPlayers(HantoPlayerColor ... colors)
	{
		return makeLinkedPlayers(new PlayerGen(){
			@Override
			public HantoPlayer gen(HantoPlayerColor each) {
				return makePlayer(each, makeInventory(HantoTile.class,
						 		 		with(Butterfly.class, 1, each)));
			}
		}, colors);
	}
	
	/**
	 * @param colors the colors of the players
	 * @return the players linked together
	 */
	public static HantoPlayer makeBetaPlayers(HantoPlayerColor ... colors)
	{
		return makeLinkedPlayers(new PlayerGen(){
			@Override
			public HantoPlayer gen(HantoPlayerColor each) {
				return makePlayer(each, makeInventory(HantoTile.class,
						 		 		with(Butterfly.class, 1, each),
						 		 		with(Sparrow.class, 5, each)));
			}
		}, colors);
	}
	
	/**
	 * @param colors the players as colors
	 * @return the players linked together
	 */
	public static HantoPlayer makeGammaPlayers(HantoPlayerColor ... colors)
	{
		return makeLinkedPlayers(new PlayerGen(){
			@Override
			public HantoPlayer gen(HantoPlayerColor each) {
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
	public static HantoPlayer makeDeltaPlayers(HantoPlayerColor ... colors)
	{
		return makeLinkedPlayers(new PlayerGen(){
			@Override
			public HantoPlayer gen(HantoPlayerColor each) {
				return makePlayer(each, makeInventory(HantoTile.class,
						 		 		with(Crab.class, 4, each),
						 		 		with(Butterfly.class, 1, each),
						 		 		with(Sparrow.class, 4, each)));
			}
		}, colors);
	}
	
	/**
	 * @param colors the colors of the players
	 * @return the players linked together
	 */
	public static HantoPlayer makeEpsilonPlayers(HantoPlayerColor ... colors)
	{
		return makeLinkedPlayers(new PlayerGen(){
			@Override
			public HantoPlayer gen(HantoPlayerColor each) {
				return makePlayer(each, makeInventory(HantoTile.class,
						with(Butterfly.class, 1, each),
						with(Sparrow.class,   2, each),
						with(Crab.class,      6, each),
						with(Horse.class,     4, each)));
			}
		}, colors);
	}
	
	
	
	private static HantoPlayer makeLinkedPlayers(PlayerGen gen, HantoPlayerColor ... colors)
	{
		HantoPlayer first=null, last=null;
		
		for(HantoPlayerColor each : colors)
		{
			HantoPlayer current = gen.gen(each);
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
		/**
		 * @param hpc the color
		 * @return a linked hanto player, unlinked, of this color
		 */
		HantoPlayer gen(HantoPlayerColor hpc);
	}
}
