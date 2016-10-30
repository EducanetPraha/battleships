package shipgame.domain;

import shipgame.domain.types.FieldTypes;
import shipgame.domain.types.ShipTypes;

import java.util.List;

/**
 * Created by Tomáš Rechtig on 28.10.2016.
 */
public interface Ship {
    List<int[]> getShape();
    String getName();
    ShipTypes getType();
    boolean checkCollision(FieldTypes[][] map);
    Ship setDefaultCoords();
    Ship setCoords(int[] arr);
    boolean checkHit(int[] coords);
}
