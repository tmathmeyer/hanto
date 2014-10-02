package hanto.otnah.common.util.graph;

import static org.junit.Assert.*;
import hanto.otnah.common.HexPosition;

import org.junit.Test;

public class GraphTests {

	@Test
	public void emptyContinuity()
	{
		assertTrue(new HexGraph().isContinuous());
	}
	
	@Test
	public void singleContinuity()
	{
		HexGraph graph = new HexGraph();
		graph.insertNodeAt(new HexPosition(0, 0));
		assertTrue(graph.isContinuous());
	}
	
	@Test
	public void dualSuccessfulContinuity()
	{
		HexGraph graph = new HexGraph();
		graph.insertNodeAt(new HexPosition(0, 0));
		graph.insertNodeAt(new HexPosition(1, 0));
		assertTrue(graph.isContinuous());
	}
	
	@Test
	public void dualUnsuccessfulContinuity()
	{
		HexGraph graph = new HexGraph();
		graph.insertNodeAt(new HexPosition(0, 0));
		graph.insertNodeAt(new HexPosition(2, 2));
		assertFalse(graph.isContinuous());
	}
	
	@Test
	public void dualSuccessfulContinuityMovement()
	{
		HexGraph graph = new HexGraph();
		graph.insertNodeAt(new HexPosition(0, 0));
		graph.insertNodeAt(new HexPosition(1, 0));
		assertTrue(graph.isContinuousAfter(new HexPosition(0, 0), new HexPosition(0, 1)));
	}

}
