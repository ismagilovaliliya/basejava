package test;

import com.unrise.webapp.exception.ExistStorageException;
import com.unrise.webapp.exception.NotExistStorageException;
import com.unrise.webapp.exception.StorageException;
import com.unrise.webapp.model.Resume;
import com.unrise.webapp.model.Storage;
import com.unrise.webapp.storage.AbstractArrayStorage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {
    private Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    private static final Resume RESUME_1 = new Resume(UUID_1);
    private static final Resume RESUME_2 = new Resume(UUID_2);
    private static final Resume RESUME_3 = new Resume(UUID_3);

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
        Assert.assertEquals(3,storage.size());
    }

    @Test
    public void clear() throws Exception{
        storage.clear();
        Assert.assertEquals(0,storage.size());
    }

    @Test
    public void update() throws Exception{
        Resume RESUME_4 = new Resume(UUID_2);
        storage.update(RESUME_4);
        Assert.assertEquals(RESUME_4,storage.get(UUID_2));
    }

    @Test
    public void save() throws Exception{
        Resume RESUME_4 = new Resume("uuid4");
        storage.save(RESUME_4);
        Assert.assertEquals(4,storage.size());
    }

    @Test
    public void getAll() throws Exception{
        Resume [] allResume = storage.getAll();
        Assert.assertEquals(3, allResume.length);
        Assert.assertEquals(allResume[0], RESUME_1);
        Assert.assertEquals(allResume[1], RESUME_2);
        Assert.assertEquals(allResume[2], RESUME_3);
    }

    @Test
    public void delete() throws Exception{
        storage.delete(UUID_1);
        Assert.assertEquals(2, storage.size());
    }

    @Test
    public void get() throws Exception{
        Assert.assertEquals(RESUME_1, storage.get(RESUME_1.getUuid()));
        Assert.assertEquals(RESUME_2, storage.get(RESUME_2.getUuid()));
        Assert.assertEquals(RESUME_3, storage.get(RESUME_3.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(RESUME_3);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() throws Exception {
        try {
            for (int i = storage.size() + 1; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        }catch (StorageException e){
            Assert.fail();
        }
        storage.save(new Resume());
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        Resume RESUME_4 = new Resume("uuid4");
        storage.update(RESUME_4);
        Assert.assertTrue(RESUME_3 == storage.get("uuid4"));
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete("dummy");
    }
}