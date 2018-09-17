import java.util.ArrayList;

public class Backpack {

    private int carryingCapacity;
    private ArrayList<Thing> backpack;
    private int currentWeightOfBackpack;
    private int costOfBackpack;

    public Backpack(int carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
        backpack = new ArrayList<>();
        currentWeightOfBackpack = 0;
        costOfBackpack = 0;
    }

    public ArrayList<Thing> toTake(int number,Thing ... things){
        if (number == things.length) return backpack;
        if (things[number].getWeight() <= carryingCapacity){
            if (things[number].getWeight() + currentWeightOfBackpack <= carryingCapacity) {
                backpack.add(things[number]);
                currentWeightOfBackpack += things[number].getWeight();
                costOfBackpack += things[number].getJewel();
            }else{
                if (!compare(0, things[number]) && costOfBackpack < things[number].getJewel()){
                    for (int i = 0; i < backpack.size(); i++) {
                        currentWeightOfBackpack -= backpack.get(i).getWeight();
                        costOfBackpack -= backpack.get(i).getJewel();
                    }
                    backpack.clear();
                    backpack.add(things[number]);
                    currentWeightOfBackpack += things[number].getWeight();
                    costOfBackpack += things[number].getJewel();
                }
            }
        }
        return toTake(number + 1, things);
    }

    private boolean compare(int index, Thing thing){
        if (index == backpack.size()) return false;
//        if (thing.getJewel() > backpack.get(index).getJewel()){
            if (costOfBackpack  < thing.getJewel() + (costOfBackpack - backpack.get(index).getJewel()) &&
                thing.getWeight() + (currentWeightOfBackpack - backpack.get(index).getWeight()) <= carryingCapacity){
                currentWeightOfBackpack += (thing.getWeight() - backpack.get(index).getWeight());
                costOfBackpack += (thing.getJewel() - backpack.get(index).getJewel());
                backpack.remove(backpack.get(index));
                backpack.add(thing);
                return true;
            }
//        }
        return compare(index + 1, thing);
    }

    public int[] getCostOfBackpack(){
        int[] cost = new int[backpack.size()];
        for (int i = 0; i < backpack.size(); i++) {
            cost[i] = backpack.get(i).getJewel();
        }
        return cost;
    }

    private Thing findNotNeeded(int index, Thing thing){
        if (index == backpack.size()) return findCheapThing(0, backpack.get(0));
        if (backpack.get(index).getWeight() + thing.getWeight() > carryingCapacity) return backpack.get(index);
        return findNotNeeded(index + 1, thing);
    }

    private Thing findCheapThing(int index, Thing thing){
        Thing cheap  = thing;
        if (index == backpack.size()) return cheap;
        if (cheap.getJewel() > backpack.get(index).getJewel()) cheap = backpack.get(index);
        return findCheapThing(index + 1, cheap);
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
