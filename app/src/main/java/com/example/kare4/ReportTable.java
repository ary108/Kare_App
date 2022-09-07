package com.example.kare4;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Objects;

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class ReportTable<K,V> {
    private ArrayList<HashNode<K, V> > holdingArray;
    private int arraylistCapacity;
    private int size;
    public ReportTable()    {
        holdingArray = new ArrayList<>();
        arraylistCapacity = 1000000;
        size = 0;

        for (int i = 0; i < arraylistCapacity; i++)
            holdingArray.add(null);
    }

    public int size() { return size; }

    public boolean isEmpty() { return size() == 0; }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private final int hashCode (K key) {
        return Objects.hashCode(key);
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private int getIndex(K key)    {
        int hashCode = hashCode(key);
        int index = hashCode % arraylistCapacity;

        index = index < 0 ? index * -1 : index;
        return index;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public V remove(K key)    {

        int bucketIndex = getIndex(key);
        int hashCode = hashCode(key);
        HashNode<K, V> head = holdingArray.get(bucketIndex);
        HashNode<K, V> prev = null;
        while (head != null) {
            if (head.key.equals(key) && hashCode == head.hashCode)
                break;
            prev = head;
            head = head.next;
        }

        if (head == null)
            return null;
        size--;
        if (prev != null) prev.next = head.next;
        else
            holdingArray.set(bucketIndex, head.next);
        return head.value;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public V get(K key)    {

        int bucketIndex = getIndex(key);
        int hashCode = hashCode(key);

        HashNode<K, V> head = holdingArray.get(bucketIndex);

        while (head != null) {
            if (head.key.equals(key) && head.hashCode == hashCode)
                return head.value;
            head = head.next;
        }

        return null;
    }

    public void add(K key, V value)    {
        while (!fullTable()) {

            int bucketIndex = getIndex(key);
            int hashCode = hashCode(key);
            HashNode<K, V> head = holdingArray.get(bucketIndex);

            while (head != null) {
                if (head.key.equals(key) && head.hashCode == hashCode) {
                    head.value = value;
                    return;
                }
                head = head.next;}

            size++;
            head = holdingArray.get(bucketIndex);
            HashNode<K, V> newNode
                    = new HashNode<K, V>(key, value, hashCode);
            newNode.next = head;
            holdingArray.set(bucketIndex, newNode);
        }
    }

    public boolean fullTable() {
        if (size == arraylistCapacity) {
            return true;
        }
        return false;
    }


}
