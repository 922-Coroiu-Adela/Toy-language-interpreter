package gui.a7.Model.Structures;

import java.util.Map;
import java.util.Hashtable;
import java.util.Enumeration;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import gui.a7.Model.Exceptions.EvaluationException;
import gui.a7.Model.Values.iValue;

public class adtDictionary<K, V> implements iDictionary<K, V>
{
    Hashtable<K, V> dictionary;

    public adtDictionary()
    {
        dictionary = new Hashtable<>();
    }

    public adtDictionary(ConcurrentMap<String, iValue> dict) {
        dictionary = new Hashtable<>();
        for (String key : dict.keySet())
            dictionary.put((K) key, (V) dict.get(key));
    }

    @Override
    public boolean containsKey(K key)
    {
        return dictionary.containsKey(key);
    }

    @Override
    public V lookup(K key) throws EvaluationException
    {
        if (dictionary.containsKey(key))
            return dictionary.get(key);
        else
            throw new EvaluationException("The key does not exist in the dictionary!");
    }

    @Override
    public boolean isDefined(K key)
    {
        return dictionary.containsKey(key);
    }

    @Override
    public int size()
    {
        return dictionary.size();
    }

    @Override
    public boolean isEmpty()
    {
        return dictionary.isEmpty();
    }

    @Override
    public void update(K key, V value) throws EvaluationException
    {
        if (dictionary.containsKey(key))
            dictionary.replace(key, value);
        else
            throw new EvaluationException("The key does not exist in the dictionary!");
    }

    @Override
    public Enumeration<K> getKeys()
    {
        return dictionary.keys();
    }

    @Override
    public Enumeration<V> getValues()
    {
        return dictionary.elements();
    }

    @Override
    public V getValue(K key)
    {
        return dictionary.get(key);
    }

    @Override
    public void remove(K key)
    {
        dictionary.remove(key);
    }

    @Override
    public void put(K key, V value) throws EvaluationException
    {
        dictionary.put(key, value);
    }

    @Override
    public iDictionary<K, V> copy()
    {
        iDictionary<K, V> copy = new adtDictionary<>();
        for (K key : dictionary.keySet()) {
            try {
                copy.put(key, dictionary.get(key));
            } catch (EvaluationException e) {
                throw new RuntimeException(e);
            }
        }
        return copy;
    }

    @Override
    public Map<K, V> getContent()
    {
        return dictionary;
    }

    public void setContent(Map<K, V> map)
    {
        dictionary = (Hashtable<K, V>) map;
    }

    @Override
    public String toString()
    {
        StringBuilder string = new StringBuilder();
        for (K key : dictionary.keySet())
        {
            string.append(key.toString());
            string.append("->");
            string.append(dictionary.get(key).toString());
            string.append("\n");
        }
        return string.toString();
    }

    @Override
    public void clear()
    {
        dictionary.clear();
    }

    @Override
    public Set<ConcurrentMap.Entry<K, V>> entrySet()
    {
        return dictionary.entrySet();
    }

}
