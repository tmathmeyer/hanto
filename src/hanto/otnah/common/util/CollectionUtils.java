package hanto.otnah.common.util;

import hanto.common.HantoException;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class CollectionUtils
{
	@SafeVarargs
	public static <T, V extends T> Set<T> toSetFromArray(Class<T> as, V ... items)
	{
		Set<T> result = new HashSet<>();
		for(T each : items)
		{
			result.add(each);
		}
		return result;
	}
	
	@SafeVarargs
	public static <T> Set<T> toSetFromFactoryArray(Class<T> as, Factory<? extends T> ... items)
	{
		Set<T> result = new HashSet<>();
		for(Factory<? extends T> each : items)
		{
			for(T v : each.getWrapped())
			{
				result.add(v);
			}
		}
		return result;
	}
	
	/**
	 * it creates things
	 * @param <T>
	 */
	public static class Factory<T>
	{
		private final List<T> inner = new LinkedList<>();
		
		public Factory(Class<T> type, int count) throws HantoException
		{
			for(int i = 0; i < count; i++)
			{
				try {
					inner.add(type.newInstance());
				} catch (InstantiationException e) {
					throw new HantoException("attempted to create instance of class with non-default constructor!");
				} catch (IllegalAccessException e) {
					throw new HantoException("attempted to create instance of non-instantiable class!");
				}
			}
		}
		
		private Factory(){}
		
		public List<T> getWrapped()
		{
			return inner;
		}
		
		public static <A> Factory<A> norm(Class<A> type, int count)
		{
			try {
				return new Factory<A> (type, count);
			} catch (HantoException e) {
				return new Factory<A>();
			}
		}
	}
}
