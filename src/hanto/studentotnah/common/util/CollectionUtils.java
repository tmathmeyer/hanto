/*******************************************************************************
 * All sources under the hanto.otnah package were developed by
 * Ted Meyer and Nilesh Patel for the term project in CS4233
 * at Worcester Polytechnic Institute. Since WPI holds all other forms
 * of ownership to this software, we have decided to not make this
 * software available under any license. Evaluation or compilation rights
 * are therefore granted only to course staff.
 *******************************************************************************/

package hanto.studentotnah.common.util;
import hanto.common.HantoPiece;
import hanto.studentotnah.common.GameState;
import hanto.studentotnah.common.Position;

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
	 * @param <C> the type of collection to return
	 * @param input the collection of inputs
	 * @param func the anonymous function
	 * @param empty an empty collection to fill
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
	 * @param <A> the type of thing to filter
	 * @param <C> a collection of A's
	 * @param input the input collection
	 * @param func the filter function
	 * @param empty the empty collection to fill
	 * @return the filtered collection
	 */
	public static <A, C extends Collection<A>> C filter(Collection<A> input, Lambda<A, Boolean> func, C empty)
	{
		for(A a : input)
		{
			if (func.apply(a))
			{
				empty.add(a);
			}
		}
		return empty;
	}
	
	/**
	 * @param a the collection of positions
	 * @param latest the game state that holds information
	 * @return a list of pieces
	 */
	public static List<HantoPiece> positionsToPieces(Collection<Position> a, final GameState latest)
	{
		return map(a, new Lambda<Position, HantoPiece>(){
			@Override
			public HantoPiece apply(Position in) {
				return latest.getPieceAt(in);
			}
		}, new LinkedList<HantoPiece>());
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
		O apply(I in);
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
	

	
	
	
	
	/**
	 * @param <A> the type of factory
	 * @param type the type of factory
	 * @param count the number of things the factory will produce
	 * @param args the arguments the factory will use to produce each
	 * @return the factory that makes count types
	 */
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
						| NullPointerException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
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
	}
}
