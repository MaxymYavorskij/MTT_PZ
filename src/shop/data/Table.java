package shop.data;

import shop.model.Entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Table<T extends Entity> {
    private int nextId = 1;
    private final List<T> rows = new ArrayList<>();

    public List<T> getRows() {
        return Collections.unmodifiableList(rows);
    }

    public int add(T item) {
        item.setId(nextId++);
        rows.add(item);
        return item.getId();
    }

    public T find(int id) {
        for (T row : rows) {
            if (row.getId() == id) {
                return row;
            }
        }
        return null;
    }
}
