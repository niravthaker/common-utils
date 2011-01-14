package name.nirav.common.utils;

import java.util.Arrays;
import java.util.List;

import name.nirav.common.utils.monads.Option;
import name.nirav.common.utils.monads.Options;

/**
 * Pair of two typed values.
 * 
 * @author Nirav Thaker
 */
public class Tuple<A, B> {
	private final A first;
	private final B rest;
	
	public Tuple(A first, B rest) {
		this.first = first;
		this.rest = rest;
	}
	
	public A first() {
		return first;
	}
	public B rest() {
		return rest;
	}
	
	public List<Object> untypedValues() {
	    return Arrays.asList(first, rest);
	}
	
	public static <A, B> Tuple<A, B> of(A first, B rest){
		return new Tuple<A, B>(first, rest);
	}
    public static <A, B> Tuple<Option<A>, Option<B>> optionallyOf(A first, B rest){
        return new Tuple<Option<A>, Option<B>>(Options.wrap(first), Options.wrap(rest));
    }

}
