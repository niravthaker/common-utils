package name.nirav.common.utils.monads;

import name.nirav.common.utils.collections.Closure;
import name.nirav.common.utils.collections.Fn;
import name.nirav.common.utils.monads.Options.OptionType;

/**
 * An option with "Some" value present.
 * @author Nirav Thaker
 */
public final class Some<T> extends Option<T> {
    private final T val;

    public Some(T val) {
        this.val = val;
    }

    public static <T> Some<T> create(T optional) {
        return new Some<T>(optional);
    }

    @Override
    public T get(int index) {
        return val;
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    public OptionType type() {
        return OptionType.Some;
    }

    @Override
    public T getOrElse(T replacement) {
        return val;
    }

    @Override
    public <B> Option<T> getOrElse(Fn<B, T> fn, B partial) {
        return create(val);
    }

    @Override
    public <Z> void getOrElse(Closure<Z> closure, Z param) {
        
    }

    @Override
    public T get() {
        return val;
    }
}
