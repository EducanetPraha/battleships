package shipgame.domain.impl;

import shipgame.domain.Ship;
import shipgame.domain.types.FieldTypes;
import shipgame.domain.types.ShipTypes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomáš Rechtig on 28.10.2016.
 */
public class ShipImpl implements Ship {

    private final String name;
    private List<int[]> shape = new ArrayList<>();
    private final ShipTypes type;

    ShipImpl(ShipTypes type) {
        this.name = type.getName();
        this.type = type;
        this.setDefaultCoords();
    }

    @Override
    public Ship setDefaultCoords() {
//        System.out.println("Setting default coordinates for: " + this.name);
        this.shape.clear();
        this.type.getShape().forEach((coords) -> this.shape.add(new int[]{coords[0], coords[1]}));
        return this;
    }

    @Override
    public Ship setCoords(final int[] arr) {
//        System.out.println("Setting coordinates for: " + this.name);
        shape.forEach((shipCoords) -> {
            shipCoords[0] = shipCoords[0] + arr[0] - 1;
            shipCoords[1] = shipCoords[1] + arr[1] - 1;
        });
        return this;
    }

    @Override
    public boolean checkHit(int[] coords) {
        boolean flag = false;

        for (int[] ints : shape) {
            if(ints[0] == coords[0] && ints[1] == coords[1]) {
                System.out.println(name + " got hit");
                flag = true;
            }
        }

        return flag;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<int[]> getShape() {
        return shape;
    }

    @Override
    public ShipTypes getType() {
        return type;
    }

    /**
     * Setup helping functions
     */
    @Override
    public boolean checkCollision(FieldTypes[][] map) {
        int min = -1, max = map.length;

        boolean flag = false;

        for (int[] coords : shape) {
//            System.out.println("coords[0]: " + coords[0] + " < max: " + max + " coords[0]: " + coords[0] + " > min: " + min);
//            System.out.println("coords[1]: " + coords[1] + " < max: " + max + " coords[1]: " + coords[1] + " > min: " + min);
            flag = !(coords[0] < max && coords[0] > min) && (coords[1] < max && coords[1] > min);
        }

        return flag || shipCollision(map);
    }

    private boolean shipCollision(FieldTypes[][] map) {

        boolean flag = false;

        for (int[] coords : shape) {
            if (map[coords[0]][coords[1]] == FieldTypes.SHIP){
                System.out.println("You can't place your ship upon your other ship, dudee!");
                flag = true;
                break;
            }
        }

        return flag;
    }
}
