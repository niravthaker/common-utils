package name.nirav.common.utils.collections;

/**
 * A predicate.
 * 
 * @author Nirav Thaker
 */
public abstract class Predicate<T> {
	abstract boolean eval(T t);
}
