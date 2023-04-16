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
        Arrays.fill(storage,null);
        size = 0;

       /* for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
        */
    }

    public void update(Resume r){
        if (size > 0 && size < storage.length) {
            storage[size-1] = r;
        }
    }

    public void save(Resume r) {
        if (size < storage.length) {
            storage[size] = r;
            size++;
        }
    }

    public Resume get(String uuid) {
        int index = 0;

        if(size > 0) {
            index = findIndex(uuid);
            if (index > -1){
                return storage[index];
            }else{
                System.out.println("Uuid: "+ uuid +" is not exist ");
            }
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
        int index = findIndex(uuid);

        if (index > -1){
            for (int i =index; i<size; i++){
                storage[i] = storage[size-1];
                storage[size-1] = null;
                size--;
            }
        }else{
            System.out.println("Uuid: "+ uuid +" is not exist ");;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] resume = new Resume[size];

        for (int i = 0; i < size; i++) {
            resume[i] = storage[i];
        }
        return resume;
    }

    public int size() {
        return size;
    }

    public int findIndex(String uuid){

        if(size > 0){
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid() == uuid) {
                    return i;
                }
            }
        }
        return -1;
    }

}
