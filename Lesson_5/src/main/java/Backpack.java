import java.util.ArrayList;

public class Backpack {

    private int carryingCapacity;
    private ArrayList<Thing> backpack;
    private int currentWeightOfBackpack;

    public Backpack(int carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
        backpack = new ArrayList<>();
        currentWeightOfBackpack = 0;
    }

    public void toTake(int number,Thing ... things){
        if (number == things.length) return;
        if (things[number].getWeight() <= carryingCapacity){
            compare(0, things[number]);
        }
        toTake(number + 1, things);
    }

    public void compare(int index, Thing thing){
        if (backpack.isEmpty()) {
            backpack.add(thing);
            currentWeightOfBackpack += thing.getWeight();
            return;
        }
        if (index == backpack.size()) return;
        if (thing.getJewel() > backpack.get(index).getJewel()){
            if (thing.getWeight() + currentWeightOfBackpack <= carryingCapacity){
                backpack.add(thing);
                currentWeightOfBackpack += thing.getWeight();
                return;
            }else {
                Thing unnecessary = findNotNeeded(0, thing);
                currentWeightOfBackpack -= unnecessary.getWeight();
                backpack.remove(unnecessary);
                index = 0;
            }
        }
        if (thing.getWeight() <= carryingCapacity - currentWeightOfBackpack){
            backpack.add(thing);
            currentWeightOfBackpack += thing.getWeight();
            return;
        }
        compare(index + 1, thing);
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
