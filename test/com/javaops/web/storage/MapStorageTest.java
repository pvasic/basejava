package com.javaops.web.storage;

import static org.junit.Assert.*;

/**
 * @author Vasichkin Pavel
 */
public class MapStorageTest extends AbstractStorageTest{
    public MapStorageTest() {
        super(new MapStorage());
    }
}