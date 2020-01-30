package com.javaops.web.storage;

import com.javaops.web.exception.StorageException;
import com.javaops.web.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * @author Vasichkin Pavel
 * Abstract storage based on files for Resume
 */
public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private static final Logger LOG = Logger.getLogger(AbstractFileStorage.class.getName());
    private File directory;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected Resume doGet(File file) {
        try {
            return doRead(file);
        } catch (IOException e) {
            LOG.severe("File " + file.getName() + " cannot be read ");
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected void doSave(Resume resume, File file) {
        try {
            if (file.createNewFile()) {
                LOG.info("File " + file.getName() + " created");
            }
        } catch (IOException e) {
            LOG.severe("File " + file.getName() + " was not created");
            throw new StorageException("IO error", file.getName(), e);
        }
        doUpdate(file, resume);
    }

    @Override
    protected void doUpdate(File file, Resume resume) {
        try {
            doWrite(resume, file);
        } catch (IOException e) {
            LOG.severe("File " + file.getName() + " cannot be write");
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected void doDelete(File file) {
        if (!file.delete()) {
            LOG.severe("File " + file.getName() + " not deleted");
            throw new StorageException("File " + file.getName() + " not deleted", file.getName());
        }
    }

    @Override
    protected List<Resume> getAll() {
        List<Resume> resumes = new ArrayList<>();
        File[] files = directory.listFiles();
        if (listFileNotNull(files)) {
            for (File file : files) {
                resumes.add(doGet(file));
            }
        }
        return resumes;
    }

    @Override
    public int size() {
        File[] files = directory.listFiles();
        if (listFileNotNull(files)) {
            return files.length;
        }
        return 0;
    }

    @Override
    public void clear() {
        File[] files = directory.listFiles();
        if (listFileNotNull(files)) {
            for (File file : files) {
                doDelete(file);
            }
        }
    }

    private boolean listFileNotNull(File[] files) {
        if (files != null) {
            return true;
        } else {
            LOG.info("Directory " + directory.getName() + " is empty");
            throw new StorageException("Directory " + directory.getName() + " is empty", directory.getName());
        }
    }

    protected abstract void doWrite(Resume r, File file) throws IOException;

    protected abstract Resume doRead(File file) throws IOException;
}
