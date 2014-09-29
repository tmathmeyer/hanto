package hanto.otnah.common.util.graph;

import java.util.Collection;

public interface Adjacency<K>
{
	/**
	 * Gets the list of positions that are adjacent to the given position.
	 * @return the list of adjacent positions
	 */
	public Collection<K> adjacentPositions();
}
