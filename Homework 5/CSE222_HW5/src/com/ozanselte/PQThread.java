package com.ozanselte;

public class PQThread extends Thread {

    private PriorityQueueO pq;
    private String name;
    private boolean isActive;

    public PQThread(String str, PriorityQueueO obj) {
        pq = obj;
        name = str;
    }

    @Override
    public void run() {
        isActive = true;
        synchronized(this) {
            try {
                do {
                    processData();
                    wait();
                } while(isActive);
                processData();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void processData() {
        while (pq.size() > 0) {
            PixelO temp = pq.remove();
            System.out.println(name + ": " + temp.toString());
        }
    }

    public void close() {
        synchronized(this) {
            try {
                isActive = false;
                notify();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
