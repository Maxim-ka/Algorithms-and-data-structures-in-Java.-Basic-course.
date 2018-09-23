import java.util.ArrayList;

public class Backpack {

    private int carryingCapacity;
    private ArrayList<Thing> backpack;
    private int costOfBackpack;
    private int costOfSet;
    private int weightSet;

    public Backpack(int carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
        backpack = new ArrayList<>();
        costOfBackpack = 0;
    }

    public ArrayList<Thing> toTake(int number, Thing ... things){
        if (number == things.length) return backpack;
        if (things[number].getWeight() <= carryingCapacity){
            selectSet(number - 1, things[number], things);
        }
        return toTake(number + 1, things);
    }

    private void selectSet(int index, Thing thing, Thing[] things){
        if (index == -1) return;
        ArrayList<Thing> set = new ArrayList<>();
        weightSet = thing.getWeight();
        costOfSet = thing.getJewel();
        set.add(thing);
        set = collectSet(index, set, things);
        if (costOfSet > costOfBackpack){
            costOfBackpack = costOfSet;
            backpack = set;
        }
        selectSet(index - 1, thing, things);
    }

    private ArrayList<Thing> collectSet(int index, ArrayList<Thing> arrayList, Thing[] things){
        if (index == -1) return arrayList;
        if (things[index].getWeight() + weightSet <= carryingCapacity){
            weightSet += things[index].getWeight();
            costOfSet += things[index].getJewel();
            arrayList.add(things[index]);
        }
        return collectSet(index - 1, arrayList, things);
    }

    public int[] getCostOfBackpack(){
        int[] cost = new int[backpack.size()];
        for (int i = 0; i < backpack.size(); i++) {
            cost[i] = backpack.get(i).getJewel();
        }
        return cost;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < backpack.size(); i++) {
            sb.append(backpack.get(i).toString()).append("\n");
        }
        return sb.toString();
    }
}
