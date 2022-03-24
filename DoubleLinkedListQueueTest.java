import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DoubleLinkedListQueueTest {
    DequeNode<Integer> nodo1;
    DequeNode<Integer> nodo2;
    DequeNode<Integer> nodo3;
    DoubleLinkedListQueue<Integer> cola = new DoubleLinkedListQueue<>();

    @BeforeEach
    void setup() {
        nodo1 = new DequeNode<>(1, null, null);
        nodo2 = new DequeNode<>(2, null, nodo1);
        nodo3 = new DequeNode<>(3, null, nodo2);

        nodo1.setNext(nodo2);
        nodo2.setNext(nodo3);
        cola.first = null;
        cola.last = null;
    }

    @Test
    void PeekEmptyQueue() {
        assertNull(cola.peekFirst());
        assertNull(cola.peekLast());
    }

    @Test
    void appendLeftEmptyQueue() {
        cola.appendLeft(nodo1);
        assertEquals(cola.peekFirst(), nodo1);
        assertEquals(cola.peekFirst(), cola.peekLast());
    }

    @Test
    void appendEmptyQueue() {
        cola.append(nodo1);
        assertEquals(cola.peekFirst(), nodo1);
        assertEquals(cola.peekFirst(), cola.peekLast());
    }

    @Test
    void appendEmptyQueuex2() {
        cola.append(nodo1);
        cola.append(nodo2);
        assertEquals(cola.peekFirst(), nodo1);
        assertEquals(cola.peekLast(), nodo2);
    }

    @Test
    void appendLeftEmptyQueuex2() {
        cola.appendLeft(nodo1);
        cola.appendLeft(nodo2);
        assertEquals(cola.peekFirst(), nodo2);
        assertEquals(cola.peekLast(), nodo1);
    }
	
	
    @Test
    void DeleteFirstSingleNode() {
        cola.appendLeft(nodo1);
        cola.deleteFirst();
        assertNull(cola.peekFirst());
        assertNull(cola.peekLast());
    }

    @Test
    void DeleteLastSingleNode() {
        cola.appendLeft(nodo1);
        cola.deleteLast();
        assertNull(cola.peekFirst());
        assertNull(cola.peekLast());
    }

    @Test
    void SizeEmptyQueue() {
        assertEquals(cola.size(), 0);
    }

    void SizeOneNode() {
        cola.append(nodo1);
        assertEquals(cola.size(), 1);
    }

    void SizeThreeNodes() {
        cola.append(nodo1);
        cola.append(nodo2);
        cola.append(nodo3);
        assertEquals(cola.size(), 3);
    }
	

    @Test
    void sortEmptyQueue(){
        cola.sort(Integer::compareTo);
        assertNull(cola.peekFirst());
    }

    @Test
    void sortOneNode(){
        cola.append(nodo1);
        cola.sort(Integer::compareTo);
        assertEquals(cola.peekFirst(), nodo1);
    }

    @Test
    void sortTwoNodes(){
        cola.append(nodo2);
        cola.append(nodo1);

        cola.sort(Integer::compareTo);

        assertEquals(cola.peekFirst(), nodo1);
        assertEquals(cola.peekLast(), nodo2);
    }

}
