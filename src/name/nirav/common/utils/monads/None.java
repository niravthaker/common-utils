package name.nirav.common.utils.monads;


public final class None<T> extends Option<T> {

	@Override
	public T get(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int size() {
		return 0;
	}
}
