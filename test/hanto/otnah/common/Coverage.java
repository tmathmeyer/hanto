/*******************************************************************************
 * All sources under the hanto.otnah package were developed by
 * Ted Meyer and Nilesh Patel for the term project in CS4233
 * at Worcester Polytechnic Institute. Since WPI holds all other forms
 * of ownership to this software, we have decided to not make this
 * software available under any license. Evaluation or compilation rights
 * are therefore granted only to course staff.
 *******************************************************************************/

package hanto.otnah.common;

import static org.junit.Assert.*;
import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentotnah.HantoGameFactory;
import static hanto.common.HantoPieceType.*;

import org.junit.Test;

/**
 * 
 * @author otnah
 *
 *	some tests to get coverage on the common package
 *  not needed, but makes me feel better
 */
public class Coverage {

	/**
	 * bump up MoveResult
	 */
	@Test
	public void moveResultVooDoo() {
		assertNotNull(MoveResult.valueOf("OK"));
	}
	
	/**
	 * bump up HantoPieceType
	 */
	@Test
	public void pieceTypeMagic()
	{
		assertNotNull(BUTTERFLY.getPrintableName());
		assertNotNull(BUTTERFLY.getSymbol());
		assertNotNull(BUTTERFLY.toString());
		
		assertNotNull(HantoPieceType.valueOf("BUTTERFLY"));
	}
	
	/**
	 * bump up HantoPlayerColor
	 */
	@Test
	public void hantoPlayerColorWitchcraft()
	{
		assertNotNull(HantoPlayerColor.valueOf("RED"));
	}

	/**
	 * bump up the HantoGameFactory 
	 */
	@Test
	public void factoryCalls()
	{
		HantoGameFactory hgf = HantoGameFactory.getInstance();
		
		assertNotNull(hgf.makeHantoGame(HantoGameID.ALPHA_HANTO));
		
		assertNotNull(hgf.makeHantoGame(HantoGameID.BETA_HANTO));
	}

}
