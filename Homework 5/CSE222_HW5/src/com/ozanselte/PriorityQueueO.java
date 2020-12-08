package com.ozanselte;

import java.util.Comparator;

public class PriorityQueueO {

    public PixelO[] heap;
    private int heapSize;
    private Comparator<PixelO> comparator;

    public PriorityQueueO(Comparator<PixelO> c) {
        clear();
        comparator = c;
    }

    @SuppressWarnings("unchecked")
    public void add(PixelO obj) {
        if(isEmpty()) {
            heap = new PixelO[64];
        }
        PixelO temp = new PixelO(obj);
        initCapacity();
        heap[heapSize] = temp;
        int pos = heapSize;
        while(1 < pos && 0 < comparator.compare(temp, heap[pos/2])) {
            heap[pos] = heap[pos/2];
            pos /= 2;
        }
        heap[pos] = temp;
        heapSize++;
    }

    @SuppressWarnings("unchecked")
    private void initCapacity() {
        if(heapSize == heap.length) {
            PixelO[] oldHeap = heap;
            heap = new PixelO[heap.length * 2];
            for(int i = 0; i < heapSize; i++) {
                heap[i] = oldHeap[i];
            }
        }
        else if(2*(heapSize-1) < heap.length) {
            PixelO[] oldHeap = heap;
            heap = new PixelO[heap.length / 2];
            for(int i = 0; i < heapSize; i++) {
                heap[i] = oldHeap[i];
            }
        }
    }


    public PixelO remove() {
        if(isEmpty()) return null;
        PixelO item, temp;
        heapSize--;
        item = heap[0];
        temp = heap[heapSize];
        int parent = 0, child = 1;
        while(child <= heapSize) {
            if(child < heapSize && 0 > comparator.compare(heap[child], heap[child+1])) {
                child++;
            }
            if(0 <= comparator.compare(temp, heap[child])) {
                break;
            }
            heap[parent] = heap[child];
            parent = child;
            child *= 2;
        }
        heap[parent] = temp;
        initCapacity();
        return item;
    }

    public boolean isEmpty() {
        return (null == heap || 0 == heap.length || 0 == heapSize);
    }

    public int size() {
        return heapSize;
    }

    public void clear() {
        heap = null;
        heapSize = 0;
    }
}
