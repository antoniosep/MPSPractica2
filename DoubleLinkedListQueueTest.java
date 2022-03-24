import org.junit.jupiter.api.*;

import java.util.Deque;

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
        nodo2 = new DequeNode<>(2, null, null);
        nodo3 = new DequeNode<>(3, null, null);

        DoubleLinkedListQueue<Integer> cola = new DoubleLinkedListQueue<Integer>();
    }

    /**
     * PeekFirst y PeekLast sobre una cola vacía dará un valor nulo
     */

    @Test
    void peekEmptyQueue() {
        assertNull(cola.peekFirst());
        assertNull(cola.peekLast());
    }

    @Nested
    class Append{

        /**
         * AppendLeft sobre una cola vacía deberá dejar la cola con un único elemento
         * que será primero y último de ella
         */

        @Test
        void appendLeftEmptyQueue() {
            cola.appendLeft(nodo1);
            assertEquals(cola.peekFirst(), nodo1);
            assertEquals(cola.peekFirst(), cola.peekLast());
            assertEquals(nodo1.getPrevious(), null);
            assertEquals(nodo1.getNext(), null);
        }

        /**
         * Append sobre una cola vacía deberá dejar la cola con un único elemento
         * que será primero y último de ella
         */

        @Test
        void appendEmptyQueue() {
            cola.append(nodo1);

            assertEquals(cola.peekFirst(), nodo1);
            assertEquals(cola.peekFirst(), cola.peekLast());

            assertEquals(nodo1.getPrevious(), null);
            assertEquals(nodo1.getNext(), null);
        }

        /**
         * AppendLeft de dos elementos (nodo1, nodo2) sobre una cola vacía deberá dejar la cola de la forma:
         * Nodo2 -> Nodo1
         */

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

        /**
         * Append de dos elementos (nodo1, nodo2) sobre una cola vacía deberá dejar la cola de la forma:
         * Nodo1 -> Nodo2
         */

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
    }

    @Nested
    class Delete{

        /**
         * DeleteFirst de una cola de un elemento debería dejar la cola vacía
         */

        @Test
        void deleteFirstSingleNode() {
            cola.appendLeft(nodo1);
            cola.deleteFirst();
            assertNull(cola.peekFirst());
            assertNull(cola.peekLast());
        }

        /**
         * DeleteFirst de una cola de múltiples elementos debería borrar el primer elemento de la cola
         * y apuntar como primero al segundo elemento de la cola. También deberá dejar a null
         * el nodo previo al nuevo primer elemento de la cola
         */

        @Test
        void deleteFirstMultipleNodes() {
            cola.appendLeft(nodo3);
            cola.appendLeft(nodo2);
            cola.appendLeft(nodo1);
            cola.deleteFirst();
            assertEquals(cola.peekFirst(), nodo2);
            assertEquals(nodo2.getPrevious(), null);
        }

        /**
         * DeleteLast de una cola de un elemento debería dejar la cola vacía
         */

        @Test
        void deleteLastSingleNode() {
            cola.appendLeft(nodo1);
            cola.deleteLast();
            assertNull(cola.peekFirst());
            assertNull(cola.peekLast());
        }

        /**
         * DeleteLast de una cola de múltiples elementos debería borrar el último elemento de la cola
         * y apuntar como último al penúltimo elemento de la cola. También deberá dejar a null
         * el nodo siguiente al nuevo último elemento de la cola
         */

        @Test
        void deleteLastMultipleNodes() {
            cola.appendLeft(nodo3);
            cola.appendLeft(nodo2);
            cola.appendLeft(nodo1);
            cola.deleteLast();
            assertEquals(nodo2, cola.peekLast());
            assertEquals(nodo2.getNext(), null);
        }

        /**
         * Delete deberá eliminar el elemento de la cola y reconectar el nodo previo con el nodo
         * siguiente respecto al nodo eliminado.
         */

        @Test
        void deleteMiddleNode() {
            cola.appendLeft(nodo3);
            cola.appendLeft(nodo2);
            cola.appendLeft(nodo1);
            cola.delete(nodo2);
            assertEquals(nodo1.getNext(), nodo3);
            assertEquals(nodo3.getPrevious(), nodo1);
        }

        /**
         * Delete deberá eliminar los elementos de la cola y dejar el elemento intermedio como el único de
         * la cola
         */

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
    }

    @Nested
    class Size{

        /**
         * Size de una cola vacía debería ser 0
         */

        @Test
        void sizeEmptyQueue() {
            assertEquals(cola.size(), 0);
        }

        /**
         * Size de una cola de un elemento debería ser 1
         */

        @Test
        void sizeOneNode() {
            cola.append(nodo1);
            assertEquals(cola.size(), 1);
        }

        /**
         * Size de una cola de tres elementos debería ser 3
         */

        @Test
        void sizeThreeNodes() {
            cola.append(nodo1);
            cola.append(nodo2);
            cola.append(nodo3);
            assertEquals(cola.size(), 3);
        }
    }

    @Nested
    class Sort{

        /**
         * Sort de una cola vacía debería seguir dando null el primer
         * elemento de la cola
         */

        @Test
        void sortEmptyQueue(){
            cola.sort(Integer::compareTo);
            assertNull(cola.peekFirst());
        }

        /**
         * Sort de una cola de un elemento debería seguir dando el primer
         * elemento de la cola
         */

        @Test
        void sortOneNode(){
            cola.append(nodo1);
            cola.sort(Integer::compareTo);
            assertEquals(cola.peekFirst().getItem(), nodo1.getItem());
        }

        /**
         * Sort de una cola de dos elementos.
         *
         * Cola sin ordenar: Nodo2 -> Nodo1
         * Cola ordenada: Nodo1 -> Nodo2
         */

        @Test
        void sortTwoNodes(){
            cola.append(nodo2);
            cola.append(nodo1);

            cola.sort(Integer::compareTo);

            assertEquals(cola.peekFirst().getItem(), nodo1.getItem());
            assertEquals(cola.peekLast().getItem(), nodo2.getItem());
        }

        /**
         * Sort de una cola de tres elementos.
         *
         * Cola sin ordenar: Nodo2 -> Nodo3 -> Nodo1
         * Cola ordenada: Nodo1 -> Nodo2 -> Nodo3
         */

        @Test
        void sortThreeNodes(){
            cola.append(nodo2);
            cola.append(nodo3);
            cola.append(nodo1);

            cola.sort(Integer::compareTo);

            assertEquals(cola.getAt(0).getItem(), nodo1.getItem());
            assertEquals(cola.getAt(1).getItem(), nodo2.getItem());
            assertEquals(cola.getAt(2).getItem(), nodo3.getItem());
        }

        /**
         * Sort de una cola de cinco elementos.
         *
         * Cola sin ordenar: Nodo5 -> Nodo2 -> Nodo3 -> Nodo1 -> Nodo4
         * Cola ordenada: Nodo1 -> Nodo2 -> Nodo3 -> Nodo4 -> Nodo5
         */

        @Test
        void sortFiveNodes(){
            cola.append(nodo2);
            cola.append(nodo3);
            cola.append(nodo1);

            DequeNode<Integer> nodo4 = new DequeNode<>(4, null, nodo3);
            DequeNode<Integer> nodo5 = new DequeNode<>(5, null, nodo4);

            nodo4.setNext(nodo5);

            cola.appendLeft(nodo5);
            cola.append(nodo4);

            cola.sort(Integer::compareTo);

            assertEquals(cola.getAt(0).getItem(), nodo1.getItem());
            assertEquals(cola.getAt(1).getItem(), nodo2.getItem());
            assertEquals(cola.getAt(2).getItem(), nodo3.getItem());
            assertEquals(cola.getAt(3).getItem(), nodo4.getItem());
            assertEquals(cola.getAt(4).getItem(), nodo5.getItem());
        }
    }

    @Nested
    class GetAt{

        /**
         * GetAt sobre una cola vacía debería dar null
         */

        @Test
        void getAtEmptyQueueNullValue(){
            assertNull(cola.getAt(1));
        }

        /**
         * GetAt sobre una cola no vacía, donde la posición pasada
         * por parámetro es menor al tamaño de la cola, debería dar
         * el elemento correspondiente a la posición (posición+1), ya que
         * cuenta el primer elemento como posición cero
         */

        @Test
        void getAtQueueNotEmptyWherePositionIsValid(){
            cola.append(nodo1);
            cola.append(nodo2);
            cola.append(nodo3);

            assertEquals(cola.getAt(0), nodo1);
            assertEquals(cola.getAt(2), nodo3);
        }

        /**
         * GetAt sobre una cola no vacía, donde la posición pasada
         * por parámetro es mayor al tamaño de la cola, debería dar
         * null
         */

        @Test
        void getAtQueueNotEmptyWherePositionIsNotValid(){
            cola.append(nodo1);
            cola.append(nodo2);

            assertNull(cola.getAt(2));
        }

        /**
         * GetAt sobre una cola no vacía, donde la posición pasada
         * por parámetro es mayor al tamaño de la cola, debería dar
         * null
         */

        @Test
        void getAtQueueNotEmptyWherePositionIsNegative(){
            cola.append(nodo1);
            cola.append(nodo2);

            assertNull(cola.getAt(-1));
        }

    }

    @Nested
    class Find{

        /**
         * Find de un elemento en una cola vacía debería devolver null
         */

        @Test
        void findEmptyQueue(){
            assertNull(cola.find(1));
        }

        /**
         * Find de un elemento en una cola no vacía que no contenga dicho
         * elemento debería devolver null
         */

        @Test
        void findElementThatIsNotInTheQueue(){
            cola.append(nodo1);
            cola.append(nodo2);
            cola.append(nodo3);

            assertNull(cola.find(4));
        }

        /**
         * Find de un elemento en una cola no vacía que sí contenga dicho
         * elemento debería devolver el primer nodo que contenga el elemento
         */

        @Test
        void findElementThatIsInTheQueue(){
            cola.append(nodo1);
            cola.append(nodo2);
            cola.append(nodo3);

            DequeNode<Integer> nodo4 = new DequeNode<>(3, null, null);
            cola.append(nodo4);

            assertEquals(cola.find(3), nodo3);
        }

    }

}