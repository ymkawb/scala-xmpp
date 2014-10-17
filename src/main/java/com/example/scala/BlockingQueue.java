package com.example.scala;


/**
 * @author: nikolayivanov
 */
public class BlockingQueue<T> {

    private final Queue<T> queue;
    private final Object writeLock = new Object();
    private final Object putLock = new Object();

    public BlockingQueue(Queue<T> queue) {
        this.queue = queue;
    }

    public interface Queue<T> {
       void put(T value);
       T poll();
       int size();
       int maxSize();
    }


    public void put(T value){
        synchronized (writeLock) {
            if(queue.size() < queue.maxSize()){
                queue.put(value);
            }
        }
    }

    public T poll(){
        synchronized (writeLock) {
            if(queue.size() != 0){
                return queue.poll();
            }
        }
        throw new RuntimeException();
    }
}
