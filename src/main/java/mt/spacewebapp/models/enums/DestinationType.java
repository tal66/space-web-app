package mt.spacewebapp.models.enums;

public enum DestinationType {
    PLANET(1), DWARF_PLANET(2),
    MOON(3), STAR(4),
    OTHER(5);

    private int value;

    DestinationType(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase().replace('_',' ');
    }

    public int getValue() {
        return this.value;
    }
}
