/*******************************************************************************
 * All sources under the hanto.otnah package were developed by
 * Ted Meyer and Nilesh Patel for the term project in CS4233
 * at Worcester Polytechnic Institute. Since WPI holds all other forms
 * of ownership to this software, we have decided to not make this
 * software available under any license. Evaluation or compilation rights
 * are therefore granted only to course staff.
 *******************************************************************************/
package hanto.studentotnah.common.util.graph;

import hanto.common.HantoCoordinate;
import hanto.studentotnah.common.Position;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static hanto.studentotnah.common.Position.asPosition;

/**
 * 
 * @author otnah
 *
 * A class for wrapping the graph structure
 */
public class HexGraph
{
	
	private final Map<Position, Node> points = new HashMap<>();
	
	/**
	 * @author otnah
	 * Node class for the graph, holds the node and it's edges.
	 */
	static class Node implements Iterable<Edge>
	{
		private final List<Edge> edges = new ArrayList<>();
		private final Set<String> tracers = new HashSet<>();
		
		/**
		 * @param e the edge to add
		 */
		public void addEdge(Edge e)
		{
			edges.add(e);
		}
		
		/**
		 * @param e the edge to remove
		 */
		public void removeEdge(Edge e)
		{
			edges.remove(e);
		}

		@Override
		public Iterator<Edge> iterator()
		{
			return edges.iterator();
		}
		
		/**
		 * marks nodes for searching processessessessessess
		 * @param name the name of the marker
		 * @return the number of nodes marked
		 */
		public int mark(String name)
		{
			int count = 1;
			tracers.add(name);
			for(Edge e : this)
			{
				Node n = e.getOtherEnd(this);
				if (!n.isMarked(name))
				{
					count += n.mark(name);
				}
			}
			return count;
		}
		
		/**
		 * @param name the name of the marker
		 * @return whether this node is marked already
		 */
		public boolean isMarked(String name)
		{
			return tracers.contains(name);
		}
		
		/**
		 * removes a marker from this node
		 * @param name the name of the makrer
		 */
		public void unmark(String name)
		{
			tracers.remove(name);
		}
	}
	
	/**
	 * @author otnah
	 * 
	 * Edge class for the graph. Holds the nodes on either end.
	 */
	static class Edge
	{
		private final Node a, b;
		
		/**
		 * 
		 * @param one the first node
		 * @param two the second node
		 */
		private Edge(Node one, Node two)
		{
			a = one;
			b = two;
		}
		
		/**
		 * 
		 * @param n one end of this edge
		 * @return the node at the other end
		 */
		public Node getOtherEnd(Node n)
		{
			if (a.equals(n))
			{
				return b;
			}
			return a;
		}
		
		@Override
		public int hashCode()
		{
			return a.hashCode() ^ b.hashCode();
		}
		
		@Override
		public boolean equals(Object other)
		{
			if (other == null)
			{
				return false;
			}
			if (other == this)
			{
				return true;
			}
			if (other instanceof Edge)
			{
				Edge o = (Edge) other;
				return o.b.equals(b) && o.a.equals(a);
			}
			return false;
		}
	}
	
	/**
	 * inserts a node at the given position.
	 * @param key
	 */
	public void insertNodeAt(Position key)
	{
		Node n = new Node();
		Collection<Position> adjacent = key.adjacentPositions();
		for(HantoCoordinate coord : adjacent)
		{
			Node lookup = points.get(asPosition(coord));
			if (lookup != null)
			{
				Edge e = new Edge(n, lookup);
				n.addEdge(e);
				lookup.addEdge(e);
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
		if (n != null)
		{
			for(Edge e : n)
			{
				e.getOtherEnd(n).removeEdge(e);
			}
			points.remove(key);
		}
	}
	
	/**
	 * Checks whether the given move would break the contiguity of the
	 * pieces on the board. Essentially, a connectivity check.
	 * @param from initial location
	 * @param to destination
	 * @return true if the graph is connected after the move is made.
	 */
	public boolean isContinuousAfter(Position from, Position to)
	{
		insertNodeAt(to);
		removeNodeAt(from);
		
		boolean result = isContinuous();
		
		insertNodeAt(from);
		removeNodeAt(to);
		
		return result;
	}
	
	/**
	 * Checks whether the given move would break the contiguity of the
	 * pieces on the board. Essentially, a connectivity check.
	 * @param from initial location
	 * @return true if the graph is connected after the move is made.
	 */
	public boolean isContinuousSans(Position from)
	{
		removeNodeAt(from);
		
		boolean result = isContinuous();
		
		insertNodeAt(from);
		
		return result;
	}
	
	/**
	 * @return whether the graph in its current state is continuous
	 */
	public boolean isContinuous()
	{
		if (points.size() == 0)
		{
			return true;
		}
		int continuity = points.values().iterator().next().mark("search");
		for(Node n : points.values())
		{
			n.unmark("search");
		}
		return continuity == points.size();
	}
}
