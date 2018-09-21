import java.util.Objects;

public class Item {

    private Item next;
    private int data;

    public int getKey() {
        return data;
    }

    public Item getNext() {
        return next;
    }

    public void setNext(Item next) {
        this.next = next;
    }

    public Item(int data) {
        this.data = data;
        next = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return data == item.data;
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }
}
