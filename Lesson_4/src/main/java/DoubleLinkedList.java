import java.util.ListIterator;
import java.util.NoSuchElementException;

public class DoubleLinkedList<T> {

    private class Node<T> {

        private T object;
        private Node<T> next;
        private Node<T> prev;

        Node(T object) {
            this.object = object;
        }
    }

    private Node<T> head;
    private Node<T> tail;

    public DoubleLinkedList() {
        this.head = null;
        this.tail = null;
    }

    public MyListIterator myListIterator(){
        return new MyListIterator();
    }

    public T getLast(){
        if (isEmpty()) return null;
        return tail.object;
    }

    public T getFirst(){
        if (isEmpty()) return null;
        return head.object;
    }

    public T get(int index){
        Node<T> temp = getNod(index);
        if (temp != null) return temp.object;
        return null;
    }

    public T pollFirst(){
        if (isEmpty()) return null;
        T temp = getFirst();
        remove(temp);
        return temp;
    }

    public T pollLast(){
        if (isEmpty()) return null;
        Node<T> previous = tail.prev;
        Node<T> temp = tail;
        if (previous != null) previous.next = null;
        else head = null;
        tail = previous;
        return temp.object;
    }

    private Node<T> getNod(int index){
        if (isEmpty()) return null;
        if (index < 0) return null;
        int count = 0;
        Node<T> current = head;
        while (current != null){
            if (count == index){
                return current;
            }
            current = current.next;
            count++;
        }
        return null;
    }

    public T delete(int index){
        if (isEmpty()) return null;
        Node<T> temp = getNod(index);
        if (temp != null){
            removeNode(temp);
            return temp.object;
        }
        return null;
    }

    private void removeNode(Node<T> node){
        Node<T> previous = node.prev;
        Node<T> next = node.next;
        if (previous != null) previous.next = next;
        else head = next;
        if (next != null) next.prev = previous;
        else tail = previous;
    }

    public void remove(T object) {
        if (isEmpty()) return;
        Node<T> current = head;
        while (current != null) {
            if (current.object.equals(object)){
                removeNode(current);
                return;
            }
            current = current.next;
        }
    }

    public void addFirst(T object){
        Node<T> node = new Node<>(object);
        if (isEmpty()){
            head = node;
            tail = node;
        }else{
            node.next = head;
            head.prev = node;
            node.prev = null;
            head = node;
        }
    }

    public void addLast(T object){
        Node<T> node = new Node<>(object);
        if (isEmpty()){
            head = node;
            tail = node;
        }else{
            tail.next = node;
            node.next = null;
            node.prev = tail;
            tail = node;
        }
    }

    public boolean isEmpty(){
        return head == null && tail == null;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "[]";
        Node<T> current = head;
        StringBuilder sb = new StringBuilder("[");
        while (current != null) {
            sb.append(current.object);
            current = current.next;
            sb.append((current == null) ? "]" : ", ");
        }
        return sb.toString();
    }

    public class MyListIterator implements ListIterator<T> {

        private int position;
        private Node<T> selected;
        private Node<T> previous;
        private Node<T> following;

        MyListIterator() {
            selected = null;
            previous = null;
            following = head;
            position = -1;
        }

        @Override
        public void add(T object){
            if (position == -1 || previous == null){
                addFirst(object);
                position++;
                return;
            }
            if (following == null){
                addLast(object);
                position++;
                return;
            }
            Node<T> node = new Node<>(object);
            previous.next = node;
            node.prev = previous;
            node.next = following;
            following.prev = node;
            previous = node;
            position++;
        }

        @Override
        public boolean hasNext(){
            return following != null;
        }

        @Override
        public boolean	hasPrevious(){
            return previous != null ;
        }

        @Override
        public T next(){
            if (!hasNext()) throw new NoSuchElementException();
            selected = following;
            previous = following;
            following = previous.next;
            ++position;
            return selected.object;
        }

        @Override
        public int	nextIndex(){
            return position + 1;
        }

        @Override
        public T previous(){
            if (!hasPrevious()) throw new NoSuchElementException();
            selected = previous;
            following = previous;
            previous = following.prev;
            --position;
            return selected.object;
        }

        @Override
        public int	previousIndex(){
            if (hasPrevious()) return position - 1;
            return -1;
        }

        @Override
        public void	remove(){
            if (selected == null) throw new IllegalStateException();
            removeNode(selected);
            selected = null;
            position--;
        }

        @Override
        public void	set(T object){
            if (selected == null) throw new IllegalStateException();
            Node<T> node = new Node<>(object);
            Node<T> previous  = selected.prev;
            Node<T> next = selected.next;
            if (selected == head) head = node;
            else previous.next = node;
            node.prev = previous;
            if (selected == tail) tail = node;
            else next.prev = node;
            node.next = next;
            selected = null;
        }
    }
}
