/*******************************************************************************
 * All sources under the hanto.otnah package were developed by
 * Ted Meyer and Nilesh Patel for the term project in CS4233
 * at Worcester Polytechnic Institute. Since WPI holds all other forms
 * of ownership to this software, we have decided to not make this
 * software available under any license. Evaluation or compilation rights
 * are therefore granted only to course staff.
 *******************************************************************************/

package hanto.otnah.beta;

import java.util.Collection;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.otnah.common.HantoTile;
import hanto.otnah.common.Position;

public class Sparrow extends HantoTile
{
	private final HantoPlayerColor myColor;
	
	public Sparrow(HantoPlayerColor hpc)
	{
		myColor = hpc;
	}
	
	@Override
	public HantoPlayerColor getColor() {
		return myColor;
	}

	@Override
	public HantoPieceType getType() {
		return HantoPieceType.SPARROW;
	}

	@Override
	public void move(HantoCoordinate to) {
		// Empty, since the sparrow can't actually move
		
	}

	@Override
	public boolean isValidMove(Position to) {
		// Always return false, since the sparrow can never move
		return false;
	}

	@Override
	public Collection<HantoCoordinate> getAdjacentPositions() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
