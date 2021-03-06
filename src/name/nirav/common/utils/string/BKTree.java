package name.nirav.common.utils.string;

import static java.lang.Math.max;
import static java.lang.Math.min;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
/**
 * Burkhard and Keller metric tree for discrete (integer edge weights) metric spaces.
 * 
 * Influenced from 
 * <dd> http://blog.notdot.net/2007/4/Damn-Cool-Algorithms-Part-1-BK-Trees
 * <dd> http://citeseer.ist.psu.edu/1593.html
 * <dd> http://doi.acm.org/10.1145/362003.362025
 * <dd> http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.21.3112
 * 
 * @author Nirav Thaker
 */
public class BKTree {
	public static class BKNode {
		final String name;
		final Map<Integer, BKNode> children = new HashMap<Integer, BKNode>();

		private BKNode(String name) {
			this.name = name;
		}

		private BKNode childAtDistance(int pos) {
			return children.get(pos);
		}

		private void addChild(int pos, BKNode child) {
			children.put(pos, child);
		}

		/**
		 * O(log(n)) BK Search
		 * 
		 * @param node
		 * @param maxDistance
		 * @return
		 */
		public List<String> search(String node, int maxDistance) {
			int distance = distance(this.name, node);
			List<String> matches = new LinkedList<String>();
			if (distance <= maxDistance)
				matches.add(this.name);
			if (children.size() == 0)
				return matches;
			int i = max(1, distance - maxDistance);
			for (; i <= distance + maxDistance; i++) {
				BKNode child = children.get(i);
				if (child == null)
					continue;
				matches.addAll(child.search(node, maxDistance));
			}
			return matches;
		}
	}

	private BKNode root;

	/**
	 * Performs a fuzzy search.
	 * 
	 * @param q
	 * @param maxDist
	 * @return
	 */
	public List<String> search(String q, int maxDist) {
		return root.search(q, maxDist);
	}
	
	/**
	 * Exact word search, same as {@link search(String, 1)}
	 * @param q
	 * @return match or empty string.
	 */
	public String search(String q) {
		List<String> list = root.search(q, 1);
		return list.isEmpty() ? "" : list.iterator().next();
	}
	

	/**
	 * Adds new word to BKTree.
	 * @param node
	 */
	public void add(String node) {
		if(node == null || node.isEmpty()) throw new IllegalArgumentException("word can't be null or empty.");
		BKNode newNode = new BKNode(node);
		if (root == null) {
			root = newNode;
		}
		addInternal(root, newNode);
	}

	/**
	 * Recursively adds <code>newNode</code> as <code>src</code> node's child.
	 * 
	 * @param src
	 * @param newNode
	 */
	private void addInternal(BKNode src, BKNode newNode) {
		if (src.equals(newNode))
			return;
		int distance = distance(src.name, newNode.name);
		BKNode bkNode = src.childAtDistance(distance);
		if (bkNode == null) {
			src.addChild(distance, newNode);
		} else
			addInternal(bkNode, newNode);
	}

	/**
	 * Computes Levenshtein distance of two strings.
	 * Adapted from http://en.wikipedia.org/wiki/Levenshtein_distance#Computing_Levenshtein_distance
	 * @param src
	 * @param tgt
	 * @return
	 */
	//TODO: Optimize space to O(m) for larger string searches
	public static int distance(String src, String tgt) {
		int d[][];
		if (src.length() == 0) {
			return tgt.length();
		}
		if (tgt.length() == 0) {
			return src.length();
		}
		d = new int[src.length() + 1][tgt.length() + 1];
		for (int i = 0; i <= src.length(); i++) {
			d[i][0] = i;
		}
		for (int j = 0; j <= tgt.length(); j++) {
			d[0][j] = j;
		}
		for (int i = 1; i <= src.length(); i++) {
			char sch = src.charAt(i - 1);
			for (int j = 1; j <= tgt.length(); j++) {
				char tch = tgt.charAt(j - 1);
				int cost = sch == tch ? 0 : 1;
				d[i][j] = minimum(d[i - 1][j] + 1,         //deletion
							      d[i][j - 1] + 1,         //insertion
							      d[i - 1][j - 1] + cost); //substitution
			}
		}
		return d[src.length()][tgt.length()];

	}

	private static int minimum(int a, int b, int c) {
		return min(min(a, b), c);
	}
}