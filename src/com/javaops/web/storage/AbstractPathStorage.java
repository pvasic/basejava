package com.javaops.web.storage;

import com.javaops.web.exception.StorageException;
import com.javaops.web.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * @author Vasichkin Pavel
 * Abstract storage based on files for Resume
 */
public abstract class AbstractPathStorage extends AbstractStorage<Path> {
    private static final Logger LOG = Logger.getLogger(AbstractPathStorage.class.getName());
    private Path directory;

    protected abstract void doWrite(Resume r, OutputStream os) throws IOException;

    protected abstract Resume doRead(InputStream is) throws IOException;

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
        return Paths.get(directory.toString() + "/" + uuid);
    }

    @Override
    protected Resume doGet(Path path) {
        try {
            return doRead(new BufferedInputStream(new FileInputStream(path.toFile())));
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
            doWrite(resume, new BufferedOutputStream(new FileOutputStream(path.toFile())));
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
        List<Resume> resumes = new ArrayList<>();
        for (Path path : getAllFiles()) {
            resumes.add(doGet(path));
        }
        return resumes;
    }

    @Override
    public int size() {
        return getAllFiles().size();
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

    private List<Path> getAllFiles() {
        List<Path> paths = new ArrayList<>();
        try {
            Files.list(directory).forEach(paths::add);
            return paths;
        } catch (IOException e) {
            LOG.severe("Get path error in directory" + directory.toString());
            throw new StorageException("Get path error", null, e);
        }
    }
}
