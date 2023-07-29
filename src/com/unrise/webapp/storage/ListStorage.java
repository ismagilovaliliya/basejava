package com.unrise.webapp.storage;

import com.unrise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private final List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[storage.size()]);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected Object getSearchKey(String uuid){
        for (int i = 0; i < storage.size(); i++){
            if(storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void updateValue(Resume r, Object searchKey){
        storage.set((Integer) searchKey, r);
    }

    @Override
    protected void saveValue(Resume r) {
        storage.add(r);
    }

    @Override
    protected void deleteValue(Object searchKey) {
        storage.remove(searchKey);
    }
    @Override
    protected Resume getValue(Object searchKey) {
        return storage.get((Integer) searchKey);
    }
    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }
}
