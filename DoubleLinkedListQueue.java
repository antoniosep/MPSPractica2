public class DoubleLinkedListQueue<T> implements DoubleEndedQueue<T> {

    public DequeNode<T> first, last = null;

    public void append(DequeNode<T> node) {
        if(first == null) {
            first = node;
            last = node;
            node.setPrevious(null);
            node.setNext(null);
        } else {
            last.setNext(node);
            node.setPrevious(last);
            node.setNext(null);
            last = node;
        }
    }

    public void appendLeft(DequeNode<T> node) {
        if (first == null) {
            first = node;
            last = node;
            node.setPrevious(null);
            node.setNext(null);
        } else {
            first.setPrevious(node);
            node.setPrevious(null);
            node.setNext(first);
            first = node;
        }
    }


    public void deleteFirst() {
        first = first.getNext();
        first.setPrevious(null);
    }


    public void deleteLast() {
        last = last.getPrevious();
        last.setNext(null);
    }

    public DequeNode<T> peekFirst() {
        return first;
    }

    public DequeNode<T> peekLast() {
        return last;
    }

    public int size() {
        int contador = 0;
        DequeNode<T> actual = peekFirst();

        while(actual != null) {
            contador++;
            actual= actual.getNext();
        }

        return contador;
    }
}
