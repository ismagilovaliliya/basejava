package com.unrise.webapp.storage;

import com.unrise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private final Map<String, Resume> storage = new HashMap<>();
    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        ArrayList<Resume> values = new ArrayList<>(storage.values());
        return values.toArray(new Resume[values.size()]);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected Object getSearchKey(String uuid) {
        Object desiredObject=new Object();
        for (Map.Entry<String,Resume> entry : storage.entrySet()) {
            if (desiredObject.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    @Override
    protected Resume getValue(Object searchKey) {
        return storage.get(searchKey);
    }

    @Override
    protected void updateValue(Resume r, Object searchKey) {
        storage.replace((String)searchKey, r);
    }

    @Override
    protected void deleteValue(Object searchKey) {
        storage.remove((String)searchKey);
    }

    @Override
    protected void saveValue(Resume r) {
        storage.put(r.getUuid(),r);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }
}
