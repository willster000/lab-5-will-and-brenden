import java.util.ArrayList;
import java.util.List;

public class PriorityList<T> {
        private List<T> priorities;

    public PriorityList() {
        priorities = new ArrayList<>();
    }

    public void addPriority(T item) {
        priorities.add(item);
    }

    public List<T> getPriorities() {
        return priorities;
    }
    // write a method to return an element at array index i
}
