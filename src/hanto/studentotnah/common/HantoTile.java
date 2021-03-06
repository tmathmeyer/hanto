/*******************************************************************************
 * All sources under the hanto.otnah package were developed by
 * Ted Meyer and Nilesh Patel for the term project in CS4233
 * at Worcester Polytechnic Institute. Since WPI holds all other forms
 * of ownership to this software, we have decided to not make this
 * software available under any license. Evaluation or compilation rights
 * are therefore granted only to course staff.
 *******************************************************************************/

package hanto.studentotnah.common;

import hanto.common.HantoPiece;

/**
 * Class for handling the things pieces can do and things
 * around them.
 */
public abstract class HantoTile implements HantoPiece
{
	
	private Position position;
	
	/**
	 * Gets Current Position
	 * @return the Position object
	 */
	public Position getPosition()
	{
		return position;
	}
	
}
