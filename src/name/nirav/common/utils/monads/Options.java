package name.nirav.common.utils.monads;

/**
 * Utility factory to create Option types.
 * @author Nirav Thaker
 */
public class Options {
    public enum OptionType{
        None, Some
    }
    public static <Z> Option<Z> wrap(Z optional){
        return optional == null ? None.<Z>value() : Some.create(optional);
    }
}
