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
        return path.exists();
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return new Path(directory, uuid);
    }

    @Override
    protected Resume doGet(Path path) {
        try {
            return doRead(new BufferedInputStream(new FileInputStream(path)));
        } catch (IOException e) {
            LOG.severe("Path " + path.getName() + " cannot be read ");
            throw new StorageException("IO error", path.getName(), e);
        }
    }

    @Override
    protected void doSave(Resume resume, Path path) {
        try {
            if (path.createNewFile()) {
                LOG.info("Path " + path.getAbsolutePath() + " created");
            }
        } catch (IOException e) {
            LOG.severe("Path " + path.getAbsolutePath() + " was not created");
            throw new StorageException("IO error", path.getAbsolutePath(), e);
        }
        doUpdate(path, resume);
    }

    @Override
    protected void doUpdate(Path path, Resume resume) {
        try {
            doWrite(resume, new BufferedOutputStream(new FileOutputStream(path)));
        } catch (IOException e) {
            LOG.severe("Path " + path.getName() + " cannot be write");
            throw new StorageException("IO error", path.getName(), e);
        }
    }

    @Override
    protected void doDelete(Path path) {
        if (!path.delete()) {
            LOG.severe("Path " + path.getName() + " not deleted");
            throw new StorageException("Path " + path.getName() + " not deleted", path.getName());
        }
    }

    @Override
    protected List<Resume> getAll() {
        List<Resume> resumes = new ArrayList<>();
        for (Path path : listFileNotNull()) {
            resumes.add(doGet(path));
        }
        return resumes;
    }

    @Override
    public int size() {
        return listFileNotNull().length;
    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(this::doDelete);
        } catch (IOException e) {
            LOG.severe("Path delete error for files in directory" + directory.getFileName());
            throw new StorageException("Path delete error", null, e);
        }
    }

    private Path[] listFileNotNull() {
        Path[] files = directory.listFiles();
        if (files != null) {
            return files;
        } else {
            LOG.info("Directory " + directory.getName() + " is empty");
            throw new StorageException("Directory " + directory.getName() + " is empty", directory.getName());
        }
    }
}
