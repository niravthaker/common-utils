package name.nirav.common.utils.collections;

/**
 * A function with arity 2.
 * 
 * @author Nirav Thaker
 */
public abstract class Fn2<A, B, R> {
	public abstract R apply(A a, B b);
}
