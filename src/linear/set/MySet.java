package linear.set;

import org.jetbrains.annotations.Nullable;

public interface MySet {
    // checker
    boolean contains(Number element);
    boolean isEmpty();
    boolean isFull();

    // operations
    void add(Number element);
    Number remove(Number element);
    Number removeAt(int index);
    @Nullable
    Integer getSize();
}
