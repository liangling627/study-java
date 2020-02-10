package com.jdk8.local;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * @author Hank
 * @Date 2019年04月26日
 */
public class Test {


    @org.junit.Test
    public void main() {
        Locale locale = Locale.GERMAN;
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
        Double d = 243245.56;
        System.out.println(format.format(d));
    }
}
