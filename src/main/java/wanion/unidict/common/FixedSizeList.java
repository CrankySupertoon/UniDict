package wanion.unidict.common;

/*
 * Created by WanionCane(https://github.com/WanionCane).
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 1.1. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/1.1/.
 */

import javax.annotation.Nonnull;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;

@SuppressWarnings("unused")
public class FixedSizeList<E> extends AbstractList<E>
{
    private static final int defaultCapacity = 64;
    private final E[] allTheData;
    private final int capacity;
    private int size;

    @SuppressWarnings("unchecked")
    public FixedSizeList()
    {
        allTheData = (E[]) new Object[capacity = defaultCapacity];
    }

    @SuppressWarnings("unchecked")
    public FixedSizeList(int capacity)
    {
        if (capacity <= 0)
            capacity = defaultCapacity;
        allTheData = (E[]) new Object[this.capacity = capacity];
    }

    @SuppressWarnings("unchecked")
    private FixedSizeList(Collection<E> collection)
    {
        allTheData = (E[]) Arrays.copyOf(collection.toArray(), capacity = size = collection.size());
    }

    @Override
    public int size()
    {
        return size;
    }

    @Override
    public boolean isEmpty()
    {
        return size == 0;
    }

    @Override
    public boolean add(E e)
    {
        if (size == capacity)
            return false;
        allTheData[size++] = e;
        return true;
    }

    @Override
    public void add(int index, E e)
    {
        allTheData[index] = e;
    }

    @Override
    public E get(int index)
    {
        if (index > size)
            return null;
        return allTheData[index];
    }

    @Override
    public E remove(int index)
    {
        if (index >= size)
            throw new IndexOutOfBoundsException();
        modCount++;
        E removed = allTheData[index];
        if (size != 1)
            System.arraycopy(allTheData, index + 1, allTheData, index, size - index - 1);
        allTheData[--size] = null;
        return removed;
    }

    @Override
    @Nonnull
    public Object[] toArray()
    {
        return Arrays.copyOf(allTheData, size);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void clear()
    {
        modCount++;
        for (int i = 0; i < size; i++)
            allTheData[i] = null;
        size = 0;
    }
}