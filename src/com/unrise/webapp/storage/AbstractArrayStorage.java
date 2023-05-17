package com.unrise.webapp.storage;

import com.unrise.webapp.model.Resume;
import com.unrise.webapp.model.Storage;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

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
            System.out.println("Uuid: " + r.getUuid() + " is not exist ");
        }
    }

    public void save(Resume r) {
        int index = findIndex(r.getUuid());

        if (size == storage.length - 1) {
            System.out.println("Storage is full");
        } else if (index != -1) {
            System.out.println("Storage is already exist");
        } else {
            storage[size] = r;
            size++;
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);

        if (index > -1) {
            storage[index] = storage[size - 1];
            //System.arraycopy(storage, index + 1, storage, index, (size - (index + 1)));
            storage[size - 1] = null;
        } else {
            System.out.println("Uuid: " + uuid + " is not exist ");
        }
        size--;
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index == -1) {
            System.out.println("Resume " + uuid + " not exist");
            return null;
        }
        return storage[index];
    }

    protected abstract int findIndex(String uuid);
}
