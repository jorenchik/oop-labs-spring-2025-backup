package jtm.activity12;

import java.util.LinkedList;
import static jtm.activity12.GenericsTest.log;  // static import of the StringBuilder from the test

public class Generics<E extends Number> extends LinkedList<E> {

    private static final long serialVersionUID = 1L;

    public Generics() {
        super();
        log.append("Generics instance created\n");
    }

    @Override
    public void push(E e) {
        super.push(e);
        log.append(e.getClass().getName() + ": " + e + " pushed\n");
    }

    @Override
    public E pop() {
        E element = this.peek();
        super.pop();
        log.append(element.getClass().getName() + ": " + element + " popped\n");
        return element;
    }
}
