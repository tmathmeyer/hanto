/*******************************************************************************
 * All sources under the hanto.otnah package were developed by
 * All sources under the hanto.otnah package were developed by
 * Ted Meyer and Nilesh Patel for the term project in CS4233
 * at Worcester Polytechnic Institute. Since WPI holds all other forms
 * of ownership to this software, we have decided to not make this
 * software available under any license. Evaluation or compilation rights
 * are therefore granted only to course staff.
 *******************************************************************************/

package hanto.otnah.beta;

import hanto.common.HantoPlayerColor;
import hanto.otnah.common.HantoPlayer;
import hanto.otnah.common.HantoTile;
import hanto.otnah.common.pieces.Butterfly;
import hanto.otnah.common.pieces.Sparrow;
import hanto.otnah.common.util.CollectionUtils;
import hanto.otnah.common.util.CollectionUtils.Factory;
import hanto.otnah.common.InventoryPosition;
import hanto.otnah.common.Position;

/**
 * 
 * @author otnah
 *
 */
public class BetaPlayer extends HantoPlayer<BetaPlayer>
{
	private BetaPlayer nextPlayer;
	private Position butterflyPosition = new InventoryPosition();
	
	@Override
	public BetaPlayer getNextPlayer()
	{
		return nextPlayer;
	}

	@Override
	public void setNextPlayer(BetaPlayer next)
	{
		nextPlayer = next;
	}
	
	/**
	 * @param color the color of this player
	 */
	public BetaPlayer(HantoPlayerColor color)
	{
		super(color, CollectionUtils.makeInventory(
				HantoTile.class, Factory.makes(Butterfly.class, 1, color), Factory.makes(Sparrow.class, 5, color)));
	}
	
	/**
	 * 
	 * @return the location of this players butterfly
	 */
	public Position getButterflyPosition()
	{
		return butterflyPosition;
	}
	
	/**
	 * 
	 * @param p the location at which the player has placed his butterfly
	 */
	public void setButterflyPosition(Position p)
	{
		butterflyPosition = p;
	}

	@Override
	public BetaPlayer getSelf()
	{
		return this;
	}
}
