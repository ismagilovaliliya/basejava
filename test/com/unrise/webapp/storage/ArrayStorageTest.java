package com.unrise.webapp.storage;

import com.unrise.webapp.storage.AbstractArrayStorageTest;
import com.unrise.webapp.storage.ArrayStorage;

import static org.junit.Assert.*;

public class ArrayStorageTest extends AbstractArrayStorageTest {

    public ArrayStorageTest() {
        super(new ArrayStorage());
    }
}