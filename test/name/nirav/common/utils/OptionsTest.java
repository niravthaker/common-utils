package name.nirav.common.utils;

import java.util.Date;

import name.nirav.common.utils.collections.Function;
import name.nirav.common.utils.monads.Option;

import org.junit.Test;

public class OptionsTest {
    class A{
        
    }
    class B{
        
    }
    
    @Test
    public void simpleNullCheckReplacement() {
        Tuple<Date,Date> nullableTuple = Tuple.create(null, null);
        
        //Good ole null check. Keep nesting!
        if(nullableTuple.first() != null) {
            System.out.println(nullableTuple.first());
        }
        Tuple<Option<Date>,Option<Date>> optionals = Tuple.createOptional(null, null);
        
        // First way to avoid check for null;
        switch (optionals.first().type()) {
        case Some:
            System.out.println(optionals.first().get());
        }
        
        //second way to avoid check for null;
        for (Date elem : optionals.first()) 
            System.out.print(elem); // only executes if the value is non-null
     
        //third way to avoid check for null;
        System.out.println("Defaults: " + optionals.rest().getOrElse(new Date()));
        
        //fourth way to avoid check for null;
        optionals.rest().substitute(new Function<Date, Date>() {
            public Date apply(Date a) {
                return a;
            }
        }, new Date());
        
        //Alternate processing, convert date to long in null-safe way.
        for (Long long1 : optionals.rest().map(new Function<Date, Long>() {
            public Long apply(Date a) { return a.getTime(); }
        })) {
            System.out.println(long1);
        }

    }
}
