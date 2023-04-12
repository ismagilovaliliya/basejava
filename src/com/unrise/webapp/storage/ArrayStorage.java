package com.unrise.webapp.storage;

import com.unrise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size;

    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    public void update(Resume r){
        if (size > 0 && size < storage.length) {
            storage[size-1] = r;
        }else{
            System.out.println("Resume is empty");
        }
    }

    public void save(Resume r) {
        if (size < storage.length) {
            System.out.println("Resume is not full");
            storage[size] = r;
            size++;
        }else{
            System.out.println("Resume is full");
        }
    }

    public Resume get(String uuid) {
        if(size > 0){
            System.out.println("Resume is not empty");
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid() == uuid) {
                    return storage[i];
                }
            }
        }else{
            System.out.println("WARNING! Resume is empty");
        }
        return null;
    }

    public void delete(String uuid) {
   /*     int indexForDelete = -1;

        for (int i = 0; i < size; i++) {
            if (storage[i].toString() == uuid) {
                indexForDelete = i;
            }
        }

        if (indexForDelete >= 0) {
            for (int i = indexForDelete; i < size-1; i++) {
                storage[i] = storage[i+1];
            }
            storage[size-1] = null;
            size--;
        }
    */
        for (int i =0; i<size; i++){
            if(uuid == storage[i].getUuid()){
                storage[i] = storage[size-1];
                storage[size-1] = null;
                size--;
            }
        }

    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] resume = new Resume[size];

        for (int i = 0; i < size; i++) {
            resume[i].equals(storage[i]);
        }
        return resume;
    }

    public int size() {
        return size;
    }

}
