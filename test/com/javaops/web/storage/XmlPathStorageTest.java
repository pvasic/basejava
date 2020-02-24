package com.javaops.web.storage;

import com.javaops.web.storage.serializer.XmlStreamSerializer;

/**
 * @author Vasichkin Pavel
 */
public class XmlPathStorageTest extends AbstractStorageTest {
    public XmlPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new XmlStreamSerializer()));
    }
}