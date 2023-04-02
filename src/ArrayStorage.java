/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size;
    int startPosition = 0;

    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        startPosition = 0;
        size = 0;
    }

    void save(Resume r) {
        if (storage[startPosition] == null) {
            storage[startPosition] = r;
            startPosition++;
            size++;
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].toString() == uuid) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        int indexDelete = -1;

        for (int i = 0; i < size; i++) {
            if (storage[i].toString() == uuid) {
                storage[i] = null;
                indexDelete = i;
            }
        }


        if (indexDelete >= 0) {
            for (int i = indexDelete + 1; i < size; i++) {
                storage[indexDelete] = storage[i];
                storage[i] = null;
                indexDelete++;
            }

        }

        size--;
        startPosition--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resume = new Resume[size];

        for (int i = 0; i < size; i++) {
            resume[i] = storage[i];
        }
        return resume;
    }

    int size() {
        return size;
    }
}
