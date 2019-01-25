package com.company;


import org.junit.Rule;
import org.junit.contrib.java.lang.system.SystemOutRule;

import static junit.framework.TestCase.assertEquals;


public class SystemOutTest {
    @Rule
    public static final SystemOutRule log = new SystemOutRule().enableLog();

    public static void main(String[] args) {
        System.out.print("hello world");
        System.out.println(log.getLog());
    }
}