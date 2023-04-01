/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;
    int startPosition = 0;
    void clear() {

        for( int i = 0; i < size; i++){
            storage[i] = null;
            size = 0;
            startPosition = 0;
        }
    }

    void save(Resume r) {
        int i = 0;

        if (storage[startPosition] == null) {
            storage[startPosition] = r;
            startPosition++;
            size++;
        }else{
            do {
                if (storage[i] == null) {
                    startPosition = i;
                }
                i++;
            }while (storage[i] != null);
        }
    }

    Resume get(String uuid) {
        int i;
        Resume resume = null;

        for(i = 0; i < size; i++){
            if (storage[i].toString() == uuid)
            {
                resume = storage[i];
            }
        }
        return resume;
    }

    void delete(String uuid) {

        int indexDelete = 99999;

        for(int i = 0; i < size; i++){
            if (storage[i].toString() == uuid) {
                storage[i] = null;
                indexDelete = i;
            }
        }


        if (indexDelete != 99999){
            for(int i = indexDelete+1; i <size; i++)
            {
                storage[indexDelete] = storage[i];
                storage[i] = null;
                indexDelete ++;
            }

        }

        size--;
        startPosition--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int i = 0;
        Resume[] resume = new Resume[size];

        for( i = 0; i < size; i++){
            resume[i] = storage[i];
        }
        return resume;
    }

    int size() {
        return size;
    }
}
