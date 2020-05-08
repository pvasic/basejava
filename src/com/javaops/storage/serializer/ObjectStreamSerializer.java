package com.javaops.storage.serializer;

import com.javaops.exception.StorageException;
import com.javaops.model.Resume;

import java.io.*;

/**
 * @author Vasichkin Pavel
 */
public class ObjectStreamSerializer implements StreamSerializer {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(os)) {
            oos.writeObject(r);
        }

    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(is)) {
            return (Resume) ois.readObject();

            // For example. On the client side, a class that deserializes an object may not be found.
        } catch (ClassNotFoundException e) {
            throw new StorageException("Error read resume", null, e);
        }
    }
}
