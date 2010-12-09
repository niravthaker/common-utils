package name.nirav.common.utils.collections;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static name.nirav.common.utils.collections.CollectionUtils.exists;
import static name.nirav.common.utils.collections.CollectionUtils.filter;
import static name.nirav.common.utils.collections.CollectionUtils.forEach;
import static name.nirav.common.utils.collections.CollectionUtils.map;
import static name.nirav.common.utils.collections.CollectionUtils.reduce;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class CollectionUtilsTest {

	Predicate<String> length5OrMore = new Predicate<String>() {
		boolean eval(String t) { return t.length() > 5; }
	};
	
	@Test
	public void testFilter() {
		assertEquals(1, filter(asList("Nirav", "Hiren", "Shefali"), length5OrMore).size());
	}

	@Test
	public void testExists() {
		assertTrue(exists(asList("Nirav", "Hiren", "Shefali"), length5OrMore));
	}

	@Test
	public void testForEach() {
		final List<Integer> res = new LinkedList<Integer>();
		forEach(asList(1, 2, 3), new Closure<Integer>() {
			public void execute(Integer a) { res.add(a * 3); }
		});
		assertEquals(3, res.size());
	}

	@Test
	public void testMap() {
		Collection<Integer> map = map(asList(1, 2, 3), new Function<Integer, Integer>() {
			public Integer apply(Integer a) { return a + 2; }
		});
		assertEquals(asList(3,4,5), map);
	}

	@Test
	public void testReduce() {
		String str = reduce(asList("A", "B", "C"), new Function2<String, String, String>() {
			public String apply(String a, String b) {
				return format("%s,%s", a, b);
			}
		});
		assertEquals("A,B,C", str);
	}

}
