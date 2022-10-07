package core.basesyntax.impl;

import core.basesyntax.Storage;


public class StorageImpl<K, V> implements Storage<K, V> {
    private static final int MAX_SIZE = 10;
    private Object[] savedKey = new Object[MAX_SIZE];
    private Object[] savedData = new Object[MAX_SIZE];

    private int _currentIterator  = 0;

    @Override
    public void put(K key, V value) {
        if(!checkSameKey(key, value)) {
            savedKey[_currentIterator] = key;
            savedData[_currentIterator] = value;
            _currentIterator++;
        }
    }

    @Override
    public V get(K key) {
        for(int i= 0; i<_currentIterator; i++) {
            if(key == null) {
                if (savedKey[i] == null) {
                    return  (V) savedData[i];
                }
            } else {
                if(key.equals(savedKey[i])) {
                    return  (V) savedData[i];
                }
            }
        }
        return null;
    }

    @Override
    public int size() {
        return _currentIterator;
    }

    private boolean checkSameKey(K key, V value) {
        for(int i =0; i<_currentIterator; i++) {
            if(key == null ) {
                if(savedKey[i] == null) {
                    savedData[i] = value;
                    return true;
                }
            } else {
                if(key.equals(savedKey[i])) {
                    savedData[i] =  value;
                    return true;
                }
            }
        }
        return false;
    }
}
