package name.nirav.common.utils.monads;

import name.nirav.common.utils.collections.Closure;
import name.nirav.common.utils.collections.Function;
import name.nirav.common.utils.monads.Options.OptionType;


public final class None<T> extends Option<T> {
    
    @SuppressWarnings("rawtypes")
    private static final None none = new None();
	
    @SuppressWarnings("unchecked")
    public static <T> None<T> value(){
        return none;
    }
    
    @Override
	public T get(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int size() {
		return 0;
	}

    @Override
    public OptionType type() {
        return OptionType.None;
    }

    @Override
    public T getOrElse(T replacement) {
        return replacement;
    }

    @Override
    public <B> Option<T> substitute(Function<B, T> fn, B partial) {
        return Options.wrap(fn.apply(partial));
    }

    @Override
    public <Z> void getOrElse(Closure<Z> closure, Z param) {
        closure.execute(param);
    }

    @Override
    public T get() {
        throw new UnsupportedOperationException();
    }
}
