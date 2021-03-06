package com.javaops.storage;

import com.javaops.exception.StorageException;
import com.javaops.model.Resume;
import com.javaops.storage.serializer.StreamSerializer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * @author Vasichkin Pavel
 * Storage based on files for Resume
 */
public class FileStorage extends AbstractStorage<File> {
    private static final Logger LOG = Logger.getLogger(FileStorage.class.getName());
    private File directory;
    private StreamSerializer streamSerializer;

    protected FileStorage(File directory, StreamSerializer streamSerializer) {
        Objects.requireNonNull(directory, "directory must not be null");
        Objects.requireNonNull(streamSerializer, "readWriteStrategy must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
        this.streamSerializer = streamSerializer;
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
            return streamSerializer.doRead(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            LOG.severe("File " + file.getName() + " cannot be read ");
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected void doSave(Resume resume, File file) {
        try {
            if (file.createNewFile()) {
                LOG.info("File " + file.getAbsolutePath() + " created");
            }
        } catch (IOException e) {
            LOG.severe("File " + file.getAbsolutePath() + " was not created");
            throw new StorageException("IO error", file.getAbsolutePath(), e);
        }
        doUpdate(file, resume);
    }

    @Override
    protected void doUpdate(File file, Resume resume) {
        try {
            streamSerializer.doWrite(resume, new BufferedOutputStream(new FileOutputStream(file)));
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
        for (File file : listFileNotNull()) {
            resumes.add(doGet(file));
        }
        return resumes;
    }

    @Override
    public int size() {
        return listFileNotNull().length;
    }

    @Override
    public void clear() {
        for (File file : listFileNotNull()) {
            doDelete(file);
        }
    }

    private File[] listFileNotNull() {
        File[] files = directory.listFiles();
        if (files != null) {
            return files;
        } else {
            LOG.info("Directory " + directory.getName() + " is empty");
            throw new StorageException("Directory " + directory.getName() + " is empty", directory.getName());
        }
    }
}
