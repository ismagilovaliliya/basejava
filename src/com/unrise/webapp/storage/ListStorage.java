package com.unrise.webapp.storage;

import com.unrise.webapp.model.Resume;
import com.unrise.webapp.model.Storage;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private List<Resume> list = new ArrayList<>();

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public Resume[] getAll() {
        return list.toArray(new Resume[list.size()]);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    protected int findKey(String uuid){
        for (int i = 0; i < list.size(); i++){
            if(list.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return 0;
    }

    @Override
    protected void updateValue(Resume r, int key){
        list.set(key, r);
    }

    @Override
    protected void saveValue(Resume r) {
        list.add(r);
    }
    @Override
    protected void deleteValue(int key) {
        list.remove(key);
    }
    @Override
    protected Resume getValue(int key) {
        return list.get(key);
    }
}
