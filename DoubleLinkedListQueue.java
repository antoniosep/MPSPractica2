import java.util.Comparator;

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
        if(first != null) {
            first.setPrevious(null);
        } else {
            last = null;
        }

    }


    public void deleteLast() {
        last = last.getPrevious();
        if(last != null) {
            last.setNext(null);
        } else {
            first = null;
        }

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

    public DequeNode<T> getAt(int position) {
        int contador = 0;
        DequeNode<T> actual = null;

        if(position>=0){
            actual = peekFirst();

            while(actual!=null && contador < position) {
                contador++;
                actual= actual.getNext();
            }
        }

        return actual;
    }

    public DequeNode<T> find (T item) {
        DequeNode<T> actual = peekFirst();
        while ((actual != null) && (actual.getItem() != item) ) {
            actual= actual.getNext();
        }

        return actual;
    }

    public void delete(DequeNode<T> node) {
        if(peekFirst() == node) {
            deleteFirst();
        } else if(peekLast() == node) {
            deleteLast();
        } else {
            node.getPrevious().setNext(node.getNext());
            node.getNext().setPrevious(node.getPrevious());
        }



    }

    public void sort(Comparator<T> comparator) {
        DequeNode<T> firstOrdered = null;
        if(size()>0){
            firstOrdered = new DequeNode<>(first.getItem(), null, null);
        }

        for (int i = 1; i < size(); i++) {
            DequeNode<T> nuevo = new DequeNode<>(getAt(i).getItem(), null, null);

            DequeNode<T> previo = firstOrdered.getPrevious();
            DequeNode<T> actual = firstOrdered;

            while (actual != null && comparator.compare(nuevo.getItem(), actual.getItem()) >= 0) {
                previo = actual;
                actual = actual.getNext();
            }

            if (previo == null) {
                firstOrdered = nuevo;
                nuevo.setNext(actual);
                nuevo.setPrevious(null);
                actual.setPrevious(nuevo);
            } else {
                previo.setNext(nuevo);
                nuevo.setPrevious(previo);
                nuevo.setNext(actual);

                if (actual != null) {
                    actual.setPrevious(nuevo);
                }
            }
        }

        first = firstOrdered;
        last = getAt(size()-1);
    }
}
