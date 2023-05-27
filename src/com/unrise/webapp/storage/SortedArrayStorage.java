package com.unrise.webapp.storage;

import com.unrise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage{

    @Override
    protected int findIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void insertResume(Resume resume){
        storage[size] = resume;
    }

    @Override
    protected void deleteResume(String uuid){
        int index = findIndex(uuid);

        int indexMoved = size - (index + 1);
        if (indexMoved > 0) {
            System.arraycopy(storage, index + 1, storage, index, indexMoved);
        }
        storage[size - 1] = null;
    }

}
