package name.nirav.common.utils.collections;

/**
 * A function with arity one.
 * @author Nirav Thaker
 */
public abstract class Fn<A, B> {
	public abstract B apply(A a);
}
