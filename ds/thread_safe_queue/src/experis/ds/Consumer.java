package experis.ds;

public class Consumer<T> implements Runnable{
    ThreadSafeQueue<T> queue;
    T[] products;

    public Consumer(T[] products, ThreadSafeQueue<T> queue){
        this.queue = queue;
        this.products = products;
    }

    @Override
    public void run() {
        for(int i = 0; i < products.length; i++){
            products[i] = queue.dequeue();
        }
    }

    public T[] getProducts(){
        return products;
    }
}
