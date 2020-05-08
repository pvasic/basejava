package com.javaops.storage;

import com.javaops.storage.serializer.XmlStreamSerializer;

/**
 * @author Vasichkin Pavel
 */
public class XmlPathStorageTest extends AbstractStorageTest {
    public XmlPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new XmlStreamSerializer()));
    }
}