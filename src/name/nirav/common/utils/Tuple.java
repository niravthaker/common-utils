package name.nirav.common.utils;

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
	
	public static <A, B> Tuple<A, B> create(A first, B rest){
		return new Tuple<A, B>(first, rest);
	}

}
