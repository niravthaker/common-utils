package name.nirav.common.utils.string;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import name.nirav.common.utils.string.BKTree;

import org.junit.Test;


public class BKTreeTest {
	private static final int THRESHOLD = 2;
	@Test
	public void distance() {
		assertEquals(1, BKTree.distance("book", "nook"));
		assertEquals(2, BKTree.distance("book", "rooks"));
		assertEquals(3, BKTree.distance("book", "drooks"));
	}
	@Test
	public void query() {
		BKTree tree = new BKTree();
		tree.add("Book");
		tree.add("Nook");
		tree.add("Rooks");
		tree.add("Drooks");
		tree.add("Boon");
		tree.add("Man");
		tree.add("Women");
		assertEquals(2,tree.search("Boo",1).size());
		assertEquals(5,tree.search("Poo",3).size());
		assertEquals("Book",tree.search("Book"));
	}
	@Test
	public void performance() throws Throwable {
		InputStream inputStream = getClass().getResourceAsStream("dict.txt");
		BKTree tree = new BKTree();
		long start = System.nanoTime();
		Scanner scanner = new Scanner(inputStream);
		while(scanner.hasNextLine()){
			tree.add(scanner.nextLine());
		}
		long diff = System.nanoTime() - start;
		System.out.printf("Dictionary indexed in %f msec.%n",toMsec(diff));
		String search = "nirav";
		start = System.nanoTime();
		List<String> query = tree.search(search, THRESHOLD);
		diff = System.nanoTime() - start;
		assertNotNull(query);
		assertEquals(11,query.size());
		System.out.printf("Fuzzy matches (threshold=%d) for '%s' took %f msec.%n%s",THRESHOLD,search,toMsec(diff),query);
	}
	private float toMsec(long diff) {
		return diff / (1000f * 1000f);
	}
}