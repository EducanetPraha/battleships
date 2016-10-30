package shipgame.domain.impl;

import shipgame.domain.Battlefield;
import shipgame.domain.Listener;
import shipgame.domain.Player;
import shipgame.domain.Ship;
import shipgame.domain.types.FieldTypes;
import shipgame.domain.types.ShipTypes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomáš Rechtig on 28.10.2016.
 */
public class PlayerImpl implements Player {

    private final String name;
    private FieldTypes[][] map;
    private List<Ship> ships;

    public PlayerImpl(String name) {
        this.name = name;
        this.ships = new ArrayList<>();

        this.addShip(new ShipImpl(ShipTypes.LOD))
//                .addShip(new ShipImpl(ShipTypes.KRIZNIK))
//                .addShip(new ShipImpl(ShipTypes.PONORKA))
        ;
    }

    @Override
    public Player createMap(int size) {
        this.map = Battlefield.createMap(size);
        return this;
    }

    @Override
    public void setupShips() {
        Listener.onShipSetup(this);

        this.ships.forEach((ship) -> {
            ship = setupShip(ship);
            Battlefield.placeShip(map, ship);
            Listener.printMap(map);
        });
    }

    private Ship setupShip(Ship ship) {
        System.out.println("Coords for " + ship.getName());

        int[] coords = Listener.listenForCoords(0, this.map.length + 1);

        boolean collision = ship.setCoords(coords).checkCollision(this.map);

        if (collision) Listener.onInvalidCoords(ship).setDefaultCoords();

        return collision ? setupShip(ship) : ship;
    }

    @Override
    public FieldTypes checkField(int x, int y) {
        return this.map[x][y];
    }

    @Override
    public Player addShip(Ship ship) {
        ships.add(ship);
        return this;
    }

    @Override
    public Player checkHit(int[] coords) {
        ships.forEach((ship) -> ship.checkHit(coords));
        map[coords[0]][coords[1]] = FieldTypes.HIT;
        return this;
    }

    @Override
    public Player setMap(FieldTypes[][] map) {
        this.map = map;
        return this;
    }

    @Override
    public Player removeShip(Ship ship) {
        ships.remove(ship);
        return this;
    }

    @Override
    public FieldTypes[][] getMap() {
        return map;
    }

    @Override
    public List<Ship> getShips() {
        return ships;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean hasShips() {
        boolean flag = false;
        for (FieldTypes[] aMap : this.map) {
            for (int j = 0; j < this.map.length; j++) {
                if (aMap[j] == FieldTypes.SHIP) {
                    flag = true;
                    break;
                }
            }
        }
        return flag;
//        return ships != null;
    }
}
