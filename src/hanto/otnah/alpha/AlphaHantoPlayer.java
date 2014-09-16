/*******************************************************************************
 * All sources under the hanto.otnah package were developed by
 * Ted Meyer and Nilesh Patel for the term project in CS4233
 * at Worcester Polytechnic Institute. Since WPI holds all other forms
 * of ownership to this software, we have decided to not make this
 * software available under any license. Evaluation or compilation rights
 * are therefore granted only to course staff.
 *******************************************************************************/

package hanto.otnah.alpha;

import hanto.common.HantoPlayerColor;
import hanto.otnah.common.HantoPlayer;
import hanto.otnah.common.HantoTile;
import hanto.otnah.common.util.CollectionUtils;

/**
 * 
 * @author otnah
 *
 */
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
	
	/**
	 * default constructor for an alpha player
	 * @param playerColor the player's color
	 */
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
