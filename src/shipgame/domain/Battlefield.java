package shipgame.domain;

import shipgame.domain.types.FieldTypes;

import java.util.List;

/**
 * Created by Tomáš Rechtig on 24.10.2016.
 */
public interface Battlefield {

    static FieldTypes[][] placeShip(FieldTypes[][] map, Ship ship){
        ship.getShape().forEach((coords) -> map[coords[0]][coords[1]] = FieldTypes.SHIP);
        return map;
    }

    static FieldTypes[][] createMap(int size) {
        FieldTypes[][] map = new FieldTypes[size][size];
        return fillMap(map, FieldTypes.BLANK);
    }

    static FieldTypes[][] fillMap(FieldTypes[][] map, FieldTypes type) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = type;
            }
        }
        return map;
    }
}
