package shipgame.domain.types;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomáš Rechtig on 28.10.2016.
 */
public enum ShipTypes {
    LOD("Lod", new int[]{0,0}),
    PONORKA("Ponorka", new int[]{0,0,1,0}),
    KRIZNIK("Kriznik", new int[]{0,0,1,0,2,0});

    private final String name;
    private final int[] rawShape;
    private List<int[]> shape = new ArrayList<>();

    ShipTypes(String name, int[] rawShape) {
        this.name = name;
        this.rawShape = rawShape;
        this.shape = setShape(this.shape, this.rawShape);
    }

    private List<int[]> setShape(List<int[]> list, int[] arr){
        for (int i = 0; i < arr.length; i+=2) {
            list.add(new int[]{arr[i],arr[i + 1]});
        }
        return list;
    }

    public String getName() {
        return name;
    }

    public List<int[]> getShape() {
        return shape;
    }

    private int[] getRawShape() {
        return rawShape;
    }
}
