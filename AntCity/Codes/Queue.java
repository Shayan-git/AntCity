import java.util.ArrayList;
import java.util.List;

public class Queue<T> {
    private List<T> list;

    public Queue() {
        this.list = new ArrayList<>();
    }

    public void enqueue(T item) {
        list.add(item);
    }

    public T dequeue() {
        if (list.size() > 0) {
            T item = list.get(0);
            list.remove(item);
            return item;
        }
        return null;
    }

    public int size() {
        return list.size();
    }
}
