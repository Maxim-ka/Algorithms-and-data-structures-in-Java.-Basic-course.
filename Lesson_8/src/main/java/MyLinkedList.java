public class MyLinkedList{

    private Item head;
    private int size;

    public int getSize() {
        return size;
    }

    public MyLinkedList() {
        this.head = null;
        size = 0;
    }

    public boolean isEmpty(){
        return head == null;
    }

    public Item getHead() {
        return head;
    }

    public void insert(Item item){
        if (isEmpty()) head = item;
        else{
            Item temp = head;
            head = item;
            head.setNext(temp);
        }
        size++;
    }

    public Item find(int key){
        Item current = head;
        while (current != null){
            if (current.getKey() == key) return current;
            current = current.getNext();
        }
        return null;
    }

    public Item delete(int key) {
        Item prev = null;
        Item current = head;
        while (current != null){
            if (current.getKey() == key){
                if (prev != null) prev.setNext(current.getNext());
                else head = current.getNext();
                size--;
                current.setNext(null);
                return current;
            }
            prev = current;
            current = current.getNext();
        }
        return null;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder("[");
        Item current = head;
        while (current != null) {
            sb.append(current.getKey());
            current = current.getNext();
            sb.append((current == null) ? "]" : ", ");
        }
        return sb.toString();
    }
}
