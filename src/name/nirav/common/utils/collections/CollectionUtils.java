package name.nirav.common.utils.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import name.nirav.common.utils.monads.Options;

/**
 * Collection related utilities.
 * 
 * @author Nirav Thaker
 */
public class CollectionUtils {

	public static <T> List<T> newList() {
		return new LinkedList<T>();
	}

	public static <T> Set<T> newSet() {
		return new ConcurrentHashSet<T>();
	}

	public static <K, V> Map<K, V> newMap() {
		return new ConcurrentHashMap<K, V>();
	}

	public static <C extends Collection<T>, T> void forEach(C coll, Closure<T> closure) {
		for (C c : Options.wrap(coll))
			for (T elem : c)
				closure.execute(elem);
	}

	public static <C extends Collection<T>, T> Collection<T> filter(C coll, final Predicate<T> predicate) {
		final Collection<T> retVal = collectionLike(coll);
		forEach(coll, new Closure<T>() {
			public void execute(T a) {
				if (predicate.eval(a))
					retVal.add(a);
			}
		});
		return retVal;
	}

	private static <C extends Collection<?>, T> Collection<T> collectionLike(C coll) {
		switch (Options.wrap(coll).type()) {
		case Some:
			return coll instanceof List ? CollectionUtils.<T> newList() : CollectionUtils.<T> newSet();
		default:
			return CollectionUtils.<T> newList();
		}
	}

	public static <C extends Collection<T>, T> boolean exists(C coll, Predicate<T> predicate) {
		return !filter(coll, predicate).isEmpty();
	}

	public static <C extends Collection<T>, T, B> Collection<B> map(C coll, final Fn<T, B> functor) {
		final Collection<B> retVal = collectionLike(coll);
		forEach(coll, new Closure<T>() {
			public void execute(T a) {
				retVal.add(functor.apply(a));
			}
		});
		return retVal;
	}

	public static <C extends Collection<T>, T, B> T reduce(C coll, Fn2<T, T, T> fn) {
		Iterator<T> iterator = coll.iterator();
		T accum = null;
		while (iterator.hasNext()) {
			T t = iterator.next();
			if (accum == null) {
				accum = t;
				continue;
			}
			accum = fn.apply(accum, t);
		}
		return accum;
	}

}
