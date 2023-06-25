package test;

import com.unrise.webapp.model.Storage;
import com.unrise.webapp.storage.SortedArrayStorage;

import static org.junit.Assert.*;

public class SortedArrayStorageTest extends AbstractArrayStorageTest{

    public SortedArrayStorageTest() {
        super(new SortedArrayStorage());
    }
}