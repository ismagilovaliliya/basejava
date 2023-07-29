package com.unrise.webapp.storage;

import com.unrise.webapp.exception.ExistStorageException;
import com.unrise.webapp.exception.NotExistStorageException;
import com.unrise.webapp.exception.StorageException;
import com.unrise.webapp.model.Resume;
import com.unrise.webapp.model.Storage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final String UUID_NOT_EXIST = "dummy";

    private static final Resume RESUME_1;
    private static final Resume RESUME_2;
    private static final Resume RESUME_3;
    private static final Resume RESUME_4;

    static {
        RESUME_1 = new Resume(UUID_1);
        RESUME_2 = new Resume(UUID_2);
        RESUME_3 = new Resume(UUID_3);
        RESUME_4 = new Resume(UUID_4);
    }
    
    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void size() throws Exception{
        assertSize(3);
    }

    @Test
    public void clear() throws Exception{
        storage.clear();
        assertSize(0);
        Resume[] actual = storage.getAll();
        Resume[] expected = {};
        Assert.assertArrayEquals(expected, actual);

    }

    @Test
    public void update() throws Exception{
        Resume resume2  = new Resume(UUID_2);
        storage.update(resume2);
        Assert.assertEquals(resume2,storage.get(UUID_2));
    }

    @Test
    public void save() throws Exception{
        storage.save(RESUME_4);
        assertSize(4);
        assertGet(RESUME_4);
    }

    @Test
    public void getAll() throws Exception{
        Resume [] actual = storage.getAll();
        Resume [] expected = {RESUME_1,RESUME_2, RESUME_3};
        Assert.assertEquals(3, actual.length);
        Assert.assertArrayEquals(expected, actual);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception{
        storage.delete(UUID_1);
        assertSize(2);
        storage.get(UUID_1);
    }

    @Test
    public void get() throws Exception{
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get(UUID_NOT_EXIST);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(RESUME_3);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() throws Exception {
        storage.clear();

        try {
            for (int i = storage.size(); i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        }catch (StorageException e){
            Assert.fail();
        }
        storage.save(new Resume());
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        Resume RESUME_4 = new Resume(UUID_4);
        storage.update(RESUME_4);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete(UUID_NOT_EXIST);
    }

    public void assertSize(int value) {
        Assert.assertEquals(value, storage.size());
    }

    public void assertGet(Resume resume){
        Assert.assertEquals(resume, storage.get(resume.getUuid()));
    }
}