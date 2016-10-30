package shipgame.domain.types;

/**
 * Created by Tomáš Rechtig on 24.10.2016.
 */
public enum FieldTypes {
    SHIP('S'),
    HIT('X'),
    BLANK('O');

    private final char indicator;

    FieldTypes(char indicator) {
        this.indicator = indicator;
    }

    public char getIndicator() {
        return indicator;
    }
}
