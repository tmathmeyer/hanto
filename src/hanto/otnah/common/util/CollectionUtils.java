package hanto.otnah.common.util;

import java.util.HashSet;
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
}
