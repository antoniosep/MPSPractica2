import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DequeNodeTest {
    DequeNode<Integer> nodo1;
    DequeNode<Integer> nodo2;
    DequeNode<Integer> nodo3;

    @BeforeEach
    void setup(){
        nodo1 = new DequeNode<>(1, null, null);
        nodo2 = new DequeNode<>(2, null, nodo1);
        nodo3 = new DequeNode<>(3, null, nodo2);

        nodo1.setNext(nodo2);
        nodo2.setNext(nodo3);
    }


    @Test
    void anyadirNodoSiguiente(){
        nodo1 = new DequeNode<>(null, null, null);
        nodo2 = new DequeNode<>(null, null, null);
        nodo1.setNext(nodo2);
        assertEquals(nodo1.getNext(), nodo2);
    }

    @Test
    void anyadirNodoPrevio(){
        nodo1 = new DequeNode<>(1, null, null);
        nodo2 = new DequeNode<>(1, null, null);
        nodo1.setPrevious(nodo2);
        assertEquals(nodo1.getPrevious(), nodo2);
    }

    @Test
    void set_GetCorrectos(){
        nodo1 = new DequeNode<>(null, null, null);
        Integer numero = 3;
        nodo1.setItem(numero);
        assertEquals(numero, nodo1.getItem());
    }

    @Test
    void constructorCorrecto(){
        nodo1 = new DequeNode<>(1, null, nodo2);

        assertEquals(1, nodo1.getItem());
        assertEquals(nodo1.getPrevious(), nodo2);
        assertEquals(nodo1.getNext(), null);

        nodo1 = new DequeNode<>(1, nodo2, null);

        assertEquals(1, nodo1.getItem());
        assertEquals(nodo1.getPrevious(), null);
        assertEquals(nodo1.getNext(), nodo2);
    }

    @Test
    void firstLastTerminal() {
        nodo1 = new DequeNode<>(1, nodo2, null);
        nodo2 = new DequeNode<>(2, nodo3, nodo1);
        nodo3 = new DequeNode<>(3, null, nodo2);
        assertEquals(nodo1.isFirstNode(),true);
        assertEquals(nodo1.isLastNode(),false);
        assertEquals(nodo1.isNotATerminalNode(),false);

        assertEquals(nodo2.isNotATerminalNode(),true);
        assertEquals(nodo2.isFirstNode(),false);
        assertEquals(nodo2.isLastNode(),false);

        assertEquals(nodo3.isLastNode(),true);
        assertEquals(nodo3.isFirstNode(),false);
        assertEquals(nodo3.isNotATerminalNode(),false);
    }


}