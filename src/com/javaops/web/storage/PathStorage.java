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
 * Storage based on files for Resume
 */
public class PathStorage extends AbstractStorage<Path> {
    private static final Logger LOG = Logger.getLogger(PathStorage.class.getName());
    private Path directory;
    private ReadWriteStrategy readWriteStrategy;

    protected PathStorage(String dir, ReadWriteStrategy readWriteStrategy) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        Objects.requireNonNull(readWriteStrategy, "readWriteStrategy must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writable");
        }
        this.readWriteStrategy = readWriteStrategy;
    }

    public void setReadWriteStrategy(ReadWriteStrategy readWriteStrategy) {
        this.readWriteStrategy = readWriteStrategy;
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.isRegularFile(path);
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    protected Resume doGet(Path path) {
        try {
            return readWriteStrategy.doRead(new BufferedInputStream(Files.newInputStream(path)));
        } catch (IOException e) {
            LOG.severe("Path " + getFileName(path) + " cannot be read ");
            throw new StorageException("IO error", getFileName(path), e);
        }
    }

    @Override
    protected void doSave(Resume resume, Path path) {
        try {
            if (isExist(Files.createFile(path))) {
                LOG.info("Path " + getFileName(path) + " created");
            }
        } catch (IOException e) {
            LOG.severe("Path " + getFileName(path) + " was not created");
            throw new StorageException("IO error", getFileName(path), e);
        }
        doUpdate(path, resume);
    }

    @Override
    protected void doUpdate(Path path, Resume resume) {
        try {
            readWriteStrategy.doWrite(resume, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            LOG.severe("Path " + getFileName(path)+ " cannot be write");
            throw new StorageException("IO error", getFileName(path), e);
        }
    }

    @Override
    protected void doDelete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            LOG.severe("Path " + getFileName(path) + " not deleted");
            throw new StorageException("Path " + getFileName(path) + " not deleted", getFileName(path), e);
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
            LOG.severe("Path delete error for files in directory" + directory);
            throw new StorageException("Path delete error", e);
        }
    }

    private Stream<Path> getAllFiles() {
        try {
            return Files.list(directory);
        } catch (IOException e) {
            LOG.severe("Get path error in directory" + directory);
            throw new StorageException("Get path error", e);
        }
    }

    private String getFileName(Path path) {
        return path.getFileName().toString();
    }


}
