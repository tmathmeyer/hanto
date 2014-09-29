package hanto.otnah.common.util.graph;

import hanto.common.HantoCoordinate;
import hanto.otnah.common.Position;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static hanto.otnah.common.Position.asPosition;

public class HexGraph
{
	
	private final Map<Position, Node> points = new HashMap<>();
	
	static class Node implements Iterable<Edge>
	{
		private final List<Edge> edges = new ArrayList<>();
		
		public void addEdge(Edge e)
		{
			edges.add(e);
		}
		
		public void removeEdge(Edge e)
		{
			edges.remove(e);
		}

		@Override
		public Iterator<Edge> iterator()
		{
			return edges.iterator();
		} 
	}
	
	static class Edge
	{
		private final Node a, b;
		
		public Edge(Node one, Node two)
		{
			a = one;
			b = two;
		}
		
		public Node getOtherEnd(Node n)
		{
			if (a.equals(n))
			{
				return b;
			}
			return a;
		}
	}
	
	public void insertNodeAt(Position key)
	{
		Node n = new Node();
		Collection<HantoCoordinate> adjacent = key.adjacentPositions();
		for(HantoCoordinate coord : adjacent)
		{
			Node lookup = points.get(asPosition(coord));
			if (lookup != null)
			{
				n.addEdge(new Edge(n, lookup));
			}
		}
		points.put(key, n);
	}
	
	public void removeNodeAt(Position key)
	{
		Node n = points.get(key);
		for(Edge e : n)
		{
			e.getOtherEnd(n).removeEdge(e);
		}
		points.remove(key);
	}

	public boolean isContinuousAfter(Position from, Position to) {
		// TODO Auto-generated method stub
		return false;
	}
}
