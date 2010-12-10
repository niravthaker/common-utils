package name.nirav.common.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Date;

import name.nirav.common.utils.collections.Function;
import name.nirav.common.utils.monads.Option;
import name.nirav.common.utils.monads.Options;
import name.nirav.common.utils.monads.Options.OptionType;

import org.junit.Test;

public class OptionsTest {

    Tuple<Date, Date>                 nullableTuple = Tuple.from(null, null);
    Tuple<Option<Date>, Option<Date>> optionalTuple = Tuple.optionalFrom(null, null);
    
    @Test
    public void nullCheck() {
        //Traditional null checking code./////////////////////////////////
        if(nullableTuple.first() != null) {
            System.out.println(nullableTuple.first());
            fail("Shouldn't have reached.");
        }
    }
    
    @Test
    public void poorMansPatternMatching() {
        // First way to avoid check for null;
        switch (optionalTuple.first().type()) {
        case Some:
            System.out.println(optionalTuple.first().get());
            fail("Shouldn't have reached.");
        }
        assertNotNull(optionalTuple.first());
        assertEquals(optionalTuple.first().type(), OptionType.None);
    }
    
    @Test
    public void forLoopOverOptions() {
        //second way to avoid check for null;
        for (Date elem : optionalTuple.first()) { 
            System.out.print(elem); // executes only if the value is non-null
            fail("Shouldn't have reached here.");
        }
    }
    
    @Test
    public void getOrElse() {
        //third way to avoid check for null;
        Date replacement = new Date();
        System.out.println("Defaults: " + optionalTuple.rest().getOrElse(replacement));
        assertEquals(replacement, optionalTuple.rest().getOrElse(replacement));
    }
    
    @Test
    public void getOrElseReturnComputedValue() {
        //fourth way to avoid check for null;
        Option<Date> option = optionalTuple.rest().getOrElse(new Function<Long, Date>() {
            @Override
            public Date apply(Long a) {
                return new Date(a);
            }
        }, 0xCAFEBABEL);
        assertEquals(OptionType.Some, option.type());
        assertEquals(0XCAFEBABEL, option.get().getTime());
    }
    
    @SuppressWarnings("unused")
    @Test
    public void alternateProcessingOnNulls() {
        //Alternate processing, convert date to long in null-safe way.
        Function<Date, Long> dateToLongFunctor = new Function<Date, Long>() {
            public Long apply(Date a) { return a.getTime(); }
        };
        
        Date now = new Date();
        Option<Date> someDate = Options.wrap(now);
        for (Long long1 : someDate.map(dateToLongFunctor)) {
            assertEquals(now, new Date(long1));
        }
        Option<Date> noneDate = Options.wrap(null);
        for (Long long1 : noneDate.map(dateToLongFunctor)) 
            fail("Shouldn't have reached here");
    }
    

}
