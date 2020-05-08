package com.javaops.storage.serializer;

import com.javaops.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Vasichkin Pavel
 */
public interface StreamSerializer {

    void doWrite(Resume r, OutputStream os) throws IOException;

    Resume doRead(InputStream is) throws IOException;
}
