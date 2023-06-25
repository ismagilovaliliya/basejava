package test;

import com.unrise.webapp.model.Storage;
import com.unrise.webapp.storage.ArrayStorage;

import static org.junit.Assert.*;

public class ArrayStorageTest extends AbstractArrayStorageTest{

    public ArrayStorageTest() {
        super(new ArrayStorage());
    }
}