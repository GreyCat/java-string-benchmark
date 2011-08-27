package ru.greycat.stringsources;

/**
 * Simplest test provider possible. Always returns the same constant string.
 */
public class SingleString implements StringSource {
    @Override
    public String nextString() {
        return "OoZe9ab8 \07Eef9hooj \nweiph7iD\r OoMai8ra FaHeaf7g ingie2Ah\t Wei4abah uL3pheog тхеед4Бе еЦхамаЪ0";
    }
}
