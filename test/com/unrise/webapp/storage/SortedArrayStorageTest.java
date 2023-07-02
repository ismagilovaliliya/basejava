package com.unrise.webapp.storage;

import com.unrise.webapp.storage.AbstractArrayStorageTest;
import com.unrise.webapp.storage.SortedArrayStorage;

import static org.junit.Assert.*;

public class SortedArrayStorageTest extends AbstractArrayStorageTest {

    public SortedArrayStorageTest() {
        super(new SortedArrayStorage());
    }
}