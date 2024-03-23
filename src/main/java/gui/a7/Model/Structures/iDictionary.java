package gui.a7.Model.Structures;

import java.util.Map;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

import gui.a7.Model.Exceptions.EvaluationException;

public interface iDictionary <K, V> extends iClearable
{
    V lookup(K key) throws EvaluationException;
    boolean isDefined(K key);
    int size();
    boolean isEmpty();
    void update(K key, V value) throws EvaluationException;
    Enumeration<K> getKeys();
    Enumeration<V> getValues();
    V getValue(K key);
    void remove(K key);
    void put(K key, V value) throws EvaluationException;
    iDictionary<K, V> copy();
    Map<K, V> getContent();
    void setContent(Map<K, V> map);
    boolean containsKey(K key);

    String toString();

    Set<ConcurrentMap.Entry<K, V>> entrySet();
}
