import java.io.PrintStream;
import java.util.NoSuchElementException;

public interface myStack<T> {

    public boolean isEmpty();

    public void push(T item);

    public T pop() throws NoSuchElementException;

    public T peek() throws NoSuchElementException;

    public void printStack(PrintStream stream);

    public int size();

}
