package name.nirav.common.utils.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class CollectionUtils {
	
	public static <C extends Collection<T>, T> Collection<T> filter(C coll, Predicate<T> predicate) {
		Collection<T> retVal = new LinkedList<T>();
		for (T elem : coll) {
			if(predicate.eval(elem))
				retVal.add(elem);
		}
		return retVal;
	}
	
	public static <C extends Collection<T>, T> boolean exists(C coll, Predicate<T> predicate) {
		return !filter(coll, predicate).isEmpty();
	}
	
	public static <C extends Collection<T>, T> void forEach(C coll, Closure<T> closure) {
		for(T elem : coll) {
			closure.execute(elem);
		}
	}
	
	public static <C extends Collection<T>, T, B> Collection<B> map(C coll, Function<T, B> functor) {
		Collection<B> retVal = new LinkedList<B>();
		for (T elem : coll) {
			retVal.add(functor.apply(elem));
		}
		return retVal;
	}
	
	public static <C extends Collection<T>, T, B> T reduce(C coll, Function2<T, T, T> fn) {
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
