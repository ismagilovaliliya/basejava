package com.unrise.webapp.storage;

import com.unrise.webapp.exception.NotExistStorageException;
import com.unrise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage{

    @Override
    protected int findIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void insertResume(Resume resume){
        storage[size] = resume;
    }

    @Override
    protected void deleteResume(String uuid){
        int index = findIndex(uuid);
        if (index > -1) {
            int indexMoved = size - (index + 1);
            if (indexMoved > 0) {
                System.arraycopy(storage, index + 1, storage, index, indexMoved);
            }
            storage[size - 1] = null;
        }else {
            throw new NotExistStorageException(uuid);
        }
    }
}
