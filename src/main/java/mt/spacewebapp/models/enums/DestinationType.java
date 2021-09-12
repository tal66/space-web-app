package mt.spacewebapp.models.enums;

public enum DestinationType {
    PLANET, DWARF_PLANET, MOON, STAR, OTHER;

    @Override
    public String toString() {
        return super.toString().toLowerCase().replace('_',' ');
    }
}
