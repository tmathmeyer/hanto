/*******************************************************************************
 * All sources under the hanto.otnah package were developed by
 * Ted Meyer and Nilesh Patel for the term project in CS4233
 * at Worcester Polytechnic Institute. Since WPI holds all other forms
 * of ownership to this software, we have decided to not make this
 * software available under any license. Evaluation or compilation rights
 * are therefore granted only to course staff.
 *******************************************************************************/

package hanto.otnah.common.util.graph;

import static org.junit.Assert.*;
import hanto.otnah.common.HexPosition;

import org.junit.Test;

/**
 * 
 * @author otnah
 *
 * tests functionality of the graph data structure
 */
public class GraphTests {

	/**
	 * test whether a new graph is continuous
	 * base case
	 */
	@Test
	public void emptyContinuity()
	{
		assertTrue(new HexGraph().isContinuous());
	}
	
	/**
	 * test whether a single item is continuous
	 */
	@Test
	public void singleContinuity()
	{
		HexGraph graph = new HexGraph();
		graph.insertNodeAt(new HexPosition(0, 0));
		assertTrue(graph.isContinuous());
	}
	
	/**
	 * test when two bordering items are continuous
	 */
	@Test
	public void dualSuccessfulContinuity()
	{
		HexGraph graph = new HexGraph();
		graph.insertNodeAt(new HexPosition(0, 0));
		graph.insertNodeAt(new HexPosition(1, 0));
		assertTrue(graph.isContinuous());
	}
	
	/**
	 * test that two non-bordering are not continuous
	 */
	@Test
	public void dualUnsuccessfulContinuity()
	{
		HexGraph graph = new HexGraph();
		graph.insertNodeAt(new HexPosition(0, 0));
		graph.insertNodeAt(new HexPosition(2, 2));
		assertFalse(graph.isContinuous());
	}
	
	/**
	 * test that a position moving is continuous
	 */
	@Test
	public void dualSuccessfulContinuityMovement()
	{
		HexGraph graph = new HexGraph();
		graph.insertNodeAt(new HexPosition(0, 0));
		graph.insertNodeAt(new HexPosition(1, 0));
		assertTrue(graph.isContinuousAfter(new HexPosition(0, 0), new HexPosition(0, 1)));
	}
	
	/**
	 * test that a position moving is not continuous
	 */
	@Test
	public void dualUnsuccessfulContinuityMovement()
	{
		HexGraph graph = new HexGraph();
		graph.insertNodeAt(new HexPosition(0, 0));
		graph.insertNodeAt(new HexPosition(1, 0));
		assertFalse(graph.isContinuousAfter(new HexPosition(1, 0), new HexPosition(1, 1)));
	}

}
