package com.unrise.webapp.storage;

import com.unrise.webapp.exception.ExistStorageException;
import com.unrise.webapp.exception.NotExistStorageException;
import com.unrise.webapp.model.Resume;
import com.unrise.webapp.model.Storage;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStorage implements Storage{

    public Resume get(String uuid) {
        int key = findKey(uuid);
        if (key < 0) {
            throw new NotExistStorageException(uuid);
        }
        return getValue(key);
    }

    public void update(Resume r) {
        int key = findKey(r.getUuid());
        if (key < 0) {
            throw new NotExistStorageException(r.getUuid());
        }
        updateValue(r,key);
    }

    public void delete(String uuid){
        int key = findKey(uuid);
        deleteValue(key);
    }

    public void save(Resume r){
        int key = findKey(r.getUuid());
        if (key > -1) {
            throw new ExistStorageException(r.getUuid());
        }else{
            saveValue(r);
        }

    }
    protected abstract int findKey(String uuid);
    protected abstract Resume getValue(int key);
    protected abstract void updateValue(Resume r, int key);
    protected abstract void deleteValue(int key);
    protected abstract void saveValue(Resume r);

}
