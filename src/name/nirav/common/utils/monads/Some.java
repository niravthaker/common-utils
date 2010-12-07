package name.nirav.common.utils.monads;

public final class Some<T> extends Option<T>{
	private final T val;

	public Some(T val) {
		this.val = val;
	}

	@Override
	public T get(int index) {
		return val;
	}

	@Override
	public int size() {
		return 1;
	}
}
