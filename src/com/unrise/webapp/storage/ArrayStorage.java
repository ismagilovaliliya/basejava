package com.unrise.webapp.storage;

import com.unrise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage extends AbstractArrayStorage{

    @Override
    protected int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void insertResume(Resume resume){
        storage[size] = resume;
    }
    @Override
    protected void deleteResume(String uuid) {
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
}