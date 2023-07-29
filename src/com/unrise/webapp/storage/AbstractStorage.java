package com.unrise.webapp.storage;

import com.unrise.webapp.model.Resume;
import com.unrise.webapp.exception.ExistStorageException;
import com.unrise.webapp.exception.NotExistStorageException;
import com.unrise.webapp.model.Storage;

public abstract class AbstractStorage implements Storage{

    public Resume get(String uuid) {
        Object searchKey = getExistingSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return getValue(searchKey);
    }

    public void update(Resume r) {
        Object searchKey = getExistingSearchKey(r.getUuid());
        if (isExist(searchKey)) {
            throw new NotExistStorageException(r.getUuid());
        }
        updateValue(r,searchKey);
    }

    public void delete(String uuid){
        Object searchKey = getExistingSearchKey(uuid);
        deleteValue(searchKey);
    }

    public void save(Resume r){
        Object searchKey = getNotExistingSearchKey(r.getUuid());
        saveValue(r);
    }

    private Object getExistingSearchKey(String uuid){
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }
    private Object getNotExistingSearchKey(String uuid){
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }
    protected abstract Object getSearchKey(String uuid);
    protected abstract Resume getValue(Object searchKey);
    protected abstract void updateValue(Resume r, Object searchKey);
    protected abstract void deleteValue(Object searchKey);
    protected abstract void saveValue(Resume r);
    protected abstract boolean isExist(Object searchKey);



}
