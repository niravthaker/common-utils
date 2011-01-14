package name.nirav.common.utils.collections;

/**
 * Interface representing unit of execution.
 * 
 * @author Nirav Thaker
 */
public abstract class Closure<A> {
	public abstract void execute(A a);
}
