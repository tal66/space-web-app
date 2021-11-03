package mt.spacewebapp.models.enums;

public enum TicketClass {
    FIRST(1), ECONOMY(2);
    int value;

    TicketClass(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
