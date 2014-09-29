/*******************************************************************************
 * All sources under the hanto.otnah package were developed by
 * Ted Meyer and Nilesh Patel for the term project in CS4233
 * at Worcester Polytechnic Institute. Since WPI holds all other forms
 * of ownership to this software, we have decided to not make this
 * software available under any license. Evaluation or compilation rights
 * are therefore granted only to course staff.
 *******************************************************************************/

package hanto.otnah.common.util;

import hanto.common.HantoException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author otnah
 *
 */
public class CollectionUtils
{

	/**
	 * @param <A> the result type
	 * @param <B> the input type
	 * @param input the collection of inputs
	 * @param func the anonymous function
	 * @param an empty collection to fill
	 * @return a mapped collection
	 */
	public static <A, B, C extends Collection<A>> C map(Collection<B> input, Lambda<B, A> func, C empty)
	{
		for(B b : input)
		{
			A val = func.apply(b);
			if (val != null)
			{
				empty.add(val);
			}
		}
		return empty;
	}
	
	/**
	 * An anonymous lambda class to make up for not being able to use java 8
	 * @param <I>
	 * @param <O>
	 */
	public static interface Lambda<I, O>
	{
		/**
		 * @param in the input
		 * @return the output
		 */
		public O apply(I in);
	}
	
	/**
	 * @param <T> the type of resulting collection
	 * @param <V> The actual types
	 * @param as the type of list to get back
	 * @param items the Items to add to the list
	 * @return a list of items as a parent type
	 */
	@SafeVarargs
	public static <T, V extends T> List<T> toSetFromArray(Class<T> as, V ... items)
	{
		List<T> result = new LinkedList<>();
		for(T each : items)
		{
			result.add(each);
		}
		return result;
	}
	
	/**
	 * @param <T> the collection type
	 * @param as the type of collection
	 * @param items the items to be in the collection
	 * @return a list of items as the super type
	 */
	@SafeVarargs
	public static <T> List<T> makeInventory(Class<T> as, Factory<? extends T> ... items)
	{
		List<T> result = new LinkedList<>();
		for(Factory<? extends T> each : items)
		{
			for(T v : each.getWrapped())
			{
				result.add(v);
			}
		}
		return result;
	}
	

	
	
	
	
	
	public static <A> Factory<A> with(Class<A> type, int count, Object ... args)
	{
		return new Factory<A>(type, count, args);
	}
	
	/**
	 * it creates things
	 * @param <T> the type of factory
	 */
	public static class Factory<T>
	{
		private final List<T> inner = new LinkedList<>();
		
		/**
		 * Wrapper for creating N instances of a type, where that type
		 * has a 0 parameter default constructor
		 * 
		 * @param type the type of object
		 * @param count how many of them
		 * @throws HantoException if there was an error creating them
		 */
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
		
		/**
		 * Wrapper for creating N instances of a type, where that type
		 * has a 1 parameter default constructor
		 * 
		 * @param type the type of object
		 * @param count the number of them
		 * @param arg1 the actual parameter to the type constructor
		 */
		private <B> Factory(Class<T> type, int count, B arg1)
		{
			for(int i = 0; i < count; i++)
			{
				Constructor<T> cons;
				try {
					cons = type.getConstructor(arg1.getClass());
					if (cons != null) {
						inner.add(cons.newInstance(arg1));
					}
				} catch (NoSuchMethodException
						| SecurityException
						| InstantiationException
						| IllegalAccessException
						| IllegalArgumentException
						| InvocationTargetException e) {
					e.printStackTrace();
				}
				
			}
		}
		
		
		
		private Factory(Class<T> type, int count, Object... args)
		{
			for(int i = 0; i < count; i++)
			{
				try
				{
					Constructor<T> cons = type.getConstructor(classMap(args));
					if (cons != null)
					{
						inner.add(cons.newInstance(args));
					}
				}
				catch (NoSuchMethodException
						| SecurityException
						| InstantiationException
						| IllegalAccessException
						| IllegalArgumentException
						| InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		
		private static Class<?>[] classMap(Object... impls)
		{
			Class<?>[] types = new Class<?>[impls.length];
			for(int i = 0; i < impls.length; i++)
			{
				types[i] = impls[i].getClass();
			}
			return types;
		}
		
		
		
		
		private Factory(){}
		
		/**
		 * 
		 * @return get the list of items
		 */
		public List<T> getWrapped()
		{
			return inner;
		}
		
		/**
		 * @param <A> the type of factory
		 * @param type the type
		 * @param count the number
		 * @return a Factory of type, count
		 */
		public static <A> Factory<A> norm(Class<A> type, int count)
		{
			try {
				return new Factory<A> (type, count);
			} catch (HantoException e) {
				return new Factory<A>();
			}
		}
		
		/**
		 * @param <A> the type of factory
		 * @param <B> the type for the constructor of A
		 * @param type the type
		 * @param count the count
		 * @param ob the actual param
		 * @return a factory of type, count, ob
		 */
		public static <A, B> Factory<A> makes(Class<A> type, int count, B ob)
		{
			return new Factory<A> (type, count, ob);
		}
	}
}
