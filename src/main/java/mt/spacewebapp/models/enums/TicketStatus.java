package mt.spacewebapp.models.enums;

public enum TicketStatus {
    VALID(0),
    EXPIRED(1),
    CANCELED_BY_CLIENT(2),
    CANCELED_TRIP(3);

    private int value;

    TicketStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
