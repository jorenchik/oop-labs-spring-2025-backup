// File: src/jtm/activity12/Generics.java
package jtm.activity12;

import java.util.LinkedList;
import static jtm.activity12.GenericsTest.log;  // static import of the StringBuilder from the test

public class Generics<E extends Number> extends LinkedList<E> {

    // serialVersionUID generated to suppress warning
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor: delegates to super and logs creation.
     */
    public Generics() {
        super();
        log.append("Generics instance created\n");
    }

    /**
     * Override of push(E). Pushes the element onto the list and logs it.
     */
    @Override
    public void push(E e) {
        super.push(e);
        log.append(e.getClass().getName() + ": " + e + " pushed\n");
    }

    /**
     * Override of pop(). Pops the first element and logs its class and value.
     * Uses peek() to inspect before removal.
     */
    @Override
    public E pop() {
        E element = this.peek();
        super.pop();
        log.append(element.getClass().getName() + ": " + element + " popped\n");
        return element;
    }
}
