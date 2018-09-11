
import java.util.Arrays;
import java.util.Stack;

public class HanoiTower {

    private Stack<Integer> sourceRod = new Stack<>();
    private Stack<Integer> tempRod = new Stack<>();
    private Stack<Integer> targetRod = new Stack<>();

    public HanoiTower(Integer ... number){
        Arrays.sort(number, (o1, o2) -> {
            if (o1 < o2) return 1;
            if (o1 > o2) return -1;
            return 0;
        });
        sourceRod.addAll(Arrays.asList(number));
        showRods();
    }

    private void showRods(){
        System.out.println(Arrays.toString(sourceRod.toArray()) + " sourceRod");
        System.out.println(Arrays.toString(tempRod.toArray()) + " tempRod");
        System.out.println(Arrays.toString(targetRod.toArray()) + " targetRod");
    }

    private void move(Stack<Integer> source, Stack<Integer> temp, Stack<Integer> target, int size){
        if (size == 0) return;
        move(source, target, temp, size - 1);
        target.push(source.pop());
        move(temp, source, target,size - 1);
    }

    public void moveToTargetRod(){
        if (sourceRod.empty()) {
            System.out.println("Нет начальной установки");
            showRods();
            return;
        }
        move(sourceRod, tempRod, targetRod, sourceRod.size());
        showRods();
    }

}
