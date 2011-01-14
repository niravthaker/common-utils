package name.nirav.common.utils.monads;

import java.util.AbstractList;
import java.util.Collection;

import name.nirav.common.utils.collections.Closure;
import name.nirav.common.utils.collections.CollectionUtils;
import name.nirav.common.utils.collections.Fn;
import name.nirav.common.utils.collections.Fn2;
import name.nirav.common.utils.monads.Options.OptionType;

/**
 * Java Implementation of Haskell's MayBe and Scala's Option monads.
 * 
 * @author Nirav Thaker
 * 
 * @param <T>
 */
public abstract class Option<T> extends AbstractList<T> implements
        Collection<T> {

    /**
     * @return type of this option @see {@link OptionType}
     */
    public abstract OptionType type();

    /**
     * Returns replacement if optional value is null.
     * 
     * @param fn
     * @return
     */
    public abstract T getOrElse(T replacement);
    
    /**
     * @return
     */
    public abstract T get();
    
    /**
     * @param replacement
     * @return
     */
    public abstract <Z> void getOrElse(Closure<Z> closure, Z param);
    
    /**
     * Fancy version of {@link #getOrElse(Object)}, Takes a partial value and a function, returns function's optional return
     * value if current optional value is null.
     * 
     * @param <B>
     * @param fn
     * @param replacement
     * @return
     */
    public abstract <B> Option<T> getOrElse(Fn<B, T> fn, B partial);
    
    public <B> Collection<B> map(Fn<T, B> functor){
        return CollectionUtils.map(this, functor);
    }
    public T reduce(Fn2<T, T, T> functor){
        return CollectionUtils.reduce(this, functor);
    }
}
