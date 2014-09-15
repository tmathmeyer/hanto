/*******************************************************************************
 * All sources under the hanto.otnah package were developed by
 * Ted Meyer and Nilesh Patel for the term project in CS4233
 * at Worcester Polytechnic Institute. Since WPI holds all other forms
 * of ownership to this software, we have decided to not make this
 * software available under any license. Evaluation or compilation rights
 * are therefore granted only to course staff.
 *******************************************************************************/

package hanto.otnah.common.util;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class CollectionUtilsTest {

	public static class A {

	}

	public static class B extends A {

	}

	@Test
	public void testDoesCreate() {
		B one = new B();
		B two = new B();
		B thr = new B();
		B fou = new B();
		B fiv = new B();
		B six = new B();

		Set<A> things = new HashSet<>();
		things.add(one);
		things.add(two);
		things.add(thr);
		things.add(fou);
		things.add(fiv);
		things.add(six);

		// sets dont have order, so we can't test equality
		
		for(A a : CollectionUtils.toSetFromArray(A.class, one, two, thr, fou, fiv, six))
		{
			assertTrue(things.contains(a));
		}
	}

}
