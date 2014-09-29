package hanto.otnah.common.util.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HexGraph<K extends Adjacency<K>, J>
{
	
	private final Map<J, Node> points = new HashMap<>();
	private final Implementer<J, K> implementer;
	
	public HexGraph(Implementer<J, K> transformer)
	{
		implementer = transformer;
	}
	
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
	
	public void insertNodeAt(K key)
	{
		Node n = new Node();
		Collection<K> adjacent = key.adjacentPositions();
		for(K k : adjacent)
		{
			Node lookup = points.get(implementer.transform(k));
			if (lookup != null)
			{
				n.addEdge(new Edge(n, lookup));
			}
		}
		points.put(implementer.transform(key), n);
	}
	
	public void removeNodeAt(K key)
	{
		Node n = points.get(implementer.transform(key));
		for(Edge e : n)
		{
			e.getOtherEnd(n).removeEdge(e);
		}
		points.remove(implementer.transform(key));
	}
	
}
