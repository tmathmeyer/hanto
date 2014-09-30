package hanto.otnah.common.util.graph;

import hanto.common.HantoCoordinate;
import hanto.otnah.common.Position;
import hanto.otnah.common.util.graph.HexGraph.Edge;

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
	
	/**
	 * Node class for the graph, holds the node and it's edges.
	 *
	 */
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
	/**
	 * Edge class for the graph. Holds the nodes on either end.
	 */
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
	/**
	 * inserts a node at the given position.
	 * @param key
	 */
	public void insertNodeAt(Position key)
	{
		Node n = new Node();
		Collection<HantoCoordinate> adjacent = key.adjacentCoordinates();
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
	
	/**
	 * Removes the node at the given position.
	 * @param key
	 */
	public void removeNodeAt(Position key)
	{
		Node n = points.get(key);
		for(Edge e : n)
		{
			e.getOtherEnd(n).removeEdge(e);
		}
		points.remove(key);
	}
	
	/**
	 * Checks whether the given move would break the contiguity of the
	 * pieces on the board. Essentially, a connectivity check.
	 * @param from initial location
	 * @param to destination
	 * @return true if the graph is connected after the move is made.
	 */
	public boolean isContinuousAfter(Position from, Position to) {
		insertNodeAt(to);
		removeNodeAt(from);
		List<Node> visited = new ArrayList<>();
		visited.add(points.get(from));
		for(int i = 0; i < visited.size(); i++)
		{
			Iterator<Edge> it = visited.get(i).iterator();
			while(it.hasNext())
			{
				Node tmp = it.next().getOtherEnd(visited.get(i));
				if(visited.contains(tmp))
				{
					continue;
				}
				visited.add(tmp);
			}
		}
		return visited.size() == points.size();
	}
}
