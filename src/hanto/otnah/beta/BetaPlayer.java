package hanto.otnah.beta;

import hanto.common.HantoPlayerColor;
import hanto.otnah.common.HantoPlayer;
import hanto.otnah.common.HantoTile;
import hanto.otnah.common.util.CollectionUtils;
import hanto.otnah.common.util.CollectionUtils.Factory;
import hanto.otnah.common.InventoryPosition;
import hanto.otnah.common.Position;

public class BetaPlayer extends HantoPlayer
{
	private HantoPlayer nextPlayer;
	private Position butterflyPosition = new InventoryPosition();
	@Override
	public HantoPlayer getNextPlayer() {
		return nextPlayer;
	}

	@Override
	public void setNextPlayer(HantoPlayer next) {
		nextPlayer = next;
	}
	
	public BetaPlayer(){
		super(HantoPlayerColor.BLUE, CollectionUtils.toSetFromFactoryArray(
				HantoTile.class, Factory.norm(Butterfly.class, 1), Factory.norm(Sparrow.class, 5)));
	}
	
	public BetaPlayer(HantoPlayerColor color){
		super(color, CollectionUtils.toSetFromFactoryArray(
				HantoTile.class, Factory.norm(Butterfly.class, 1), Factory.norm(Sparrow.class, 5)));
	}
	
	public Position getButterflyPosition()
	{
		return butterflyPosition;
	}
	
	public void setButterflyPosition(Position p)
	{
		butterflyPosition = p;
	}
}
