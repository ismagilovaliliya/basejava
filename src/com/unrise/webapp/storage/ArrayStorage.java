package com.unrise.webapp.storage;

import com.unrise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage {
    private static final int STORAGE_LIMIT = 10000;
    private final Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size;

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

    public Resume get(String uuid) {
        int index = findIndex(uuid);

        if (index > -1) {
            return storage[index];
        } else {
            System.out.println("Uuid: " + uuid + " is not exist ");
        }

        return null;
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

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    public int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}