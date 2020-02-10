package com.javaops.web.storage;

import com.javaops.web.exception.StorageException;
import com.javaops.web.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Vasichkin Pavel
 * Abstract storage based on files for Resume
 */
public abstract class AbstractPathStorage extends AbstractStorage<Path> implements ReadWriteStrategy {
    private static final Logger LOG = Logger.getLogger(AbstractPathStorage.class.getName());
    private Path directory;

    public abstract void doWrite(Resume r, OutputStream os) throws IOException;

    public abstract Resume doRead(InputStream is) throws IOException;

    protected AbstractPathStorage(String dir) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writable");
        }
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.exists(path);
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    protected Resume doGet(Path path) {
        try {
            return doRead(new BufferedInputStream(Files.newInputStream(path)));
        } catch (IOException e) {
            LOG.severe("Path " + path + " cannot be read ");
            throw new StorageException("IO error", path.toString(), e);
        }
    }

    @Override
    protected void doSave(Resume resume, Path path) {
        try {
            if (isExist(Files.createFile(path))) {
                LOG.info("Path " + path.toString() + " created");
            }
        } catch (IOException e) {
            LOG.severe("Path " + path.toString() + " was not created");
            throw new StorageException("IO error", path.toString(), e);
        }
        doUpdate(path, resume);
    }

    @Override
    protected void doUpdate(Path path, Resume resume) {
        try {
            doWrite(resume, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            LOG.severe("Path " + path.toString() + " cannot be write");
            throw new StorageException("IO error", path.toString(), e);
        }
    }

    @Override
    protected void doDelete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            LOG.severe("Path " + path.toString() + " not deleted");
            throw new StorageException("Path " + path.toString() + " not deleted", path.toString(), e);
        }
    }

    @Override
    protected List<Resume> getAll() {
        return getAllFiles().
                map(this::doGet).
                collect(Collectors.toList());
    }

    @Override
    public int size() {
        return (int) getAllFiles().count();
    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(this::doDelete);
        } catch (IOException e) {
            LOG.severe("Path delete error for files in directory" + directory.toString());
            throw new StorageException("Path delete error", null, e);
        }
    }

    private Stream<Path> getAllFiles() {
        try {
            return Files.list(directory);
        } catch (IOException e) {
            LOG.severe("Get path error in directory" + directory.toString());
            throw new StorageException("Get path error", null, e);
        }
    }
}
