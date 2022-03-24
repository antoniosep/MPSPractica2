import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DoubleLinkedListQueueTest {
    DequeNode<Integer> nodo1;
    DequeNode<Integer> nodo2;
    DequeNode<Integer> nodo3;
    DoubleLinkedListQueue<Integer> cola = new DoubleLinkedListQueue<Integer>();

    @BeforeEach
    void setup() {
        nodo1 = new DequeNode<>(1, null, null);
        nodo2 = new DequeNode<>(2, null, nodo1);
        nodo3 = new DequeNode<>(3, null, nodo2);

        nodo1.setNext(nodo2);
        nodo2.setNext(nodo3);
        DoubleLinkedListQueue<Integer> cola = new DoubleLinkedListQueue<Integer>();
    }

    @Test
    void peekEmptyQueue() {
        assertNull(cola.peekFirst());
        assertNull(cola.peekLast());
    }

    @Test
    void appendLeftEmptyQueue() {
        cola.appendLeft(nodo1);
        assertEquals(cola.peekFirst(), nodo1);
        assertEquals(cola.peekFirst(), cola.peekLast());
        assertEquals(nodo1.getPrevious(), null);
        assertEquals(nodo1.getNext(), null);
    }

    @Test
    void appendEmptyQueue() {
        cola.append(nodo1);

        assertEquals(cola.peekFirst(), nodo1);
        assertEquals(cola.peekFirst(), cola.peekLast());

        assertEquals(nodo1.getPrevious(), null);
        assertEquals(nodo1.getNext(), null);
    }

    @Test
    void appendLeftEmptyQueuex2() {
        cola.appendLeft(nodo1);
        cola.appendLeft(nodo2);

        assertEquals(cola.peekFirst(), nodo2);
        assertEquals(cola.peekLast(), nodo1);

        assertEquals(nodo2.getPrevious(), null);
        assertEquals(nodo2.getNext(), nodo1);

        assertEquals(nodo1.getPrevious(), nodo2);
        assertEquals(nodo1.getNext(), null);
    }

    @Test
    void appendEmptyQueuex2() {
        cola.append(nodo1);
        cola.append(nodo2);

        assertEquals(cola.peekFirst(), nodo1);
        assertEquals(cola.peekLast(), nodo2);

        assertEquals(nodo1.getPrevious(), null);
        assertEquals(nodo1.getNext(), nodo2);

        assertEquals(nodo2.getPrevious(), nodo1);
        assertEquals(nodo2.getNext(), null);
    }

    @Test
    void deleteFirstSingleNode() {
        cola.appendLeft(nodo1);
        cola.deleteFirst();
        assertNull(cola.peekFirst());
        assertNull(cola.peekLast());
    }

    @Test
    void deleteFirstMultipleNodes() {
        cola.appendLeft(nodo3);
        cola.appendLeft(nodo2);
        cola.appendLeft(nodo1);
        cola.deleteFirst();
        assertEquals(cola.peekFirst(), nodo2);
        assertEquals(nodo2.getPrevious(), null);
    }

    @Test
    void deleteLastSingleNode() {
        cola.appendLeft(nodo1);
        cola.deleteLast();
        assertNull(cola.peekFirst());
        assertNull(cola.peekLast());
    }

    @Test
    void deleteLastMultipleNodes() {
        cola.appendLeft(nodo3);
        cola.appendLeft(nodo2);
        cola.appendLeft(nodo1);
        cola.deleteLast();
        assertEquals(nodo2, cola.peekLast());
        assertEquals(nodo2.getNext(), null);
    }

    @Test
    void deleteMiddleNode() {
        cola.appendLeft(nodo3);
        cola.appendLeft(nodo2);
        cola.appendLeft(nodo1);
        cola.delete(nodo2);
        assertEquals(nodo1.getNext(), nodo3);
        assertEquals(nodo3.getPrevious(), nodo1);
    }

    @Test
    void deleteEdges() {
        cola.appendLeft(nodo3);
        cola.appendLeft(nodo2);
        cola.appendLeft(nodo1);
        cola.delete(nodo1);
        cola.delete(nodo3);
        assertEquals(cola.peekFirst(), nodo2);
        assertEquals(cola.peekLast(), nodo2);
        assertEquals(nodo2.getNext(), null);
        assertEquals(nodo2.getPrevious(), null);
    }

    @Test
    void sizeEmptyQueue() {
        assertEquals(cola.size(), 0);
    }

    @Test
    void sizeOneNode() {
        cola.append(nodo1);
        assertEquals(cola.size(), 1);
    }

    @Test
    void sizeThreeNodes() {
        cola.append(nodo1);
        cola.append(nodo2);
        cola.append(nodo3);
        assertEquals(cola.size(), 3);
    }
}