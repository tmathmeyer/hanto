package hanto.otnah.common;

import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;

import java.util.ArrayList;
import java.util.List;

public class LinkedHantoPlayer extends HantoPlayer<LinkedHantoPlayer>
{
	private LinkedHantoPlayer(HantoPlayerColor hpc,
			List<HantoTile> defaultInventory) {
		super(hpc, defaultInventory);
	}

	private LinkedHantoPlayer nextPlayer;
	private Position butterflyPosition = new InventoryPosition();
	
	@Override
	public LinkedHantoPlayer getNextPlayer()
	{
		return nextPlayer;
	}

	@Override
	public void setNextPlayer(LinkedHantoPlayer next)
	{
		nextPlayer = next;
	}
	
	@Override
	public LinkedHantoPlayer getSelf()
	{
		return this;
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
	
	/**
	 * @param hpc the color
	 * @param defaultInventory the default inventory
	 * @return a linked hanto player with those types
	 */
	public static LinkedHantoPlayer makePlayer(HantoPlayerColor hpc,
			List<HantoTile> defaultInventory) {
		return new LinkedHantoPlayer(hpc, defaultInventory);
	}

	/**
	 * 
	 * @param firstPlayer the color of the first player
	 * @return the player with that colors
	 */
	public LinkedHantoPlayer skipTo(HantoPlayerColor firstPlayer) {
		if (getColor() == firstPlayer)
		{
			return this;
		}
		return getNextPlayer().skipTo(firstPlayer);
	}
	
	/**
	 * @return whether this players butterfly is surrounded
	 */
	public boolean isSurrounded(GameState state)
	{
		return getButterflyPosition().adjacentTiles(state).size() == 6;
	}
	
	/**
	 * @return the winning state of this player
	 */
	public MoveResult getWinningState()
	{
		MoveResult result;
		switch(getColor())
		{
			case BLUE:
				result = MoveResult.BLUE_WINS;
				break;
			case RED:
				result = MoveResult.RED_WINS;
				break;
			default:
				result = null;
		}
		return result;
	}

	/**
	 * @return the total number of moves played by all players
	 */
	public int getTotalMoves() {
		int count = 0;
		for(LinkedHantoPlayer each : collectAllUsers())
		{
			count += each.getMovesPlayed();
		}
		return count;
	}
	
	/**
	 * @return the total number of moves
	 */
	public List<LinkedHantoPlayer> collectAllUsers() {
		return collectAllUsers(getColor());
	}
	
	private List<LinkedHantoPlayer> collectAllUsers(HantoPlayerColor color) {
		List<LinkedHantoPlayer> all = new ArrayList<>();
		if (color != getColor())
		{
			all = getNextPlayer().collectAllUsers(color);
		}
		all.add(this);
		return all;	
	}
}
