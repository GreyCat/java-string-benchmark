package ru.greycat.stringdestinations;

/**
 * The sole purpose of this class is to do *something* with string, so that JIT
 * and/or overly clever optimizers won't optimize out whole algorithm as it
 * "does nothing".
 */
public class SimpleCount implements StringDestination {
    public int counter = 0;

    @Override
    public void collect(String s) {
        if (s.startsWith("a"))
            counter++;
    }
}
