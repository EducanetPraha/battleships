package shipgame.domain;

import shipgame.domain.types.FieldTypes;

import java.util.List;

/**
 * Created by Tomáš Rechtig on 28.10.2016.
 */
public interface Player {
    FieldTypes[][] getMap();
    List<Ship> getShips();
    Player addShip(Ship ship);
    Player removeShip(Ship ship);
    Player setMap(FieldTypes[][] map);
    Player checkHit(int[] coords);
    String getName();
    boolean hasShips();

    FieldTypes checkField(int x, int y);
    Player createMap(int size);
    void setupShips();
}
