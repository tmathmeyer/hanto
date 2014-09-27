package hanto.otnah.common.players;

import hanto.otnah.common.HantoPlayer;

public class PlayerState<T extends HantoPlayer<T>>
{
	private HantoPlayer<T> currentPlayer;
	
	public PlayerState(HantoPlayer<T> first, HantoPlayer<T> ... rest)
	{
		HantoPlayer<T> last = currentPlayer = first;
		for(HantoPlayer<T> each : rest)
		{
			last.setNextPlayer(each);
			last = each;
		}
		
	}
}
