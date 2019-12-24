package com.javaops.web.storage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author Vasichkin Pavel
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ArrayStorageTest.class,
        SortedArrayStorageTest.class,
        ListStorageTest.class,
        MapStorageTest.class,
        MapIteratorStorageTest.class
})
public class AllStorageClassTest {
}
