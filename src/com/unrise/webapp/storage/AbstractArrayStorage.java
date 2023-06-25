package com.unrise.webapp.storage;

import com.unrise.webapp.model.Resume;
import com.unrise.webapp.model.Storage;
import com.unrise.webapp.exception.ExistStorageException;
import com.unrise.webapp.exception.NotExistStorageException;
import com.unrise.webapp.exception.StorageException;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    public static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    public void update(Resume r) {
        int index = findIndex(r.getUuid());

        if (index > -1) {
            storage[index] = r;
        } else {
            throw new NotExistStorageException(r.getUuid());
        }
    }

    public void save(Resume r) {
        int index = findIndex(r.getUuid());

        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else if (index > -1) {
            throw new ExistStorageException(r.getUuid());
        } else {
            insertResume(r);
            size++;
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public void delete(String uuid) {
        deleteResume(uuid);
        size--;
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return storage[index];
    }

    protected abstract int findIndex(String uuid);

    protected abstract void insertResume(Resume resume);

    protected abstract  void deleteResume(String uuid);
}
