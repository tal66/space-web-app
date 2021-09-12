package mt.spacewebapp.models.enums;

import javax.persistence.AttributeConverter;

public class TicketClassConverter implements AttributeConverter<TicketClass, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TicketClass ticketClass) {
        switch (ticketClass) {
            case First:
                return 1;
            case Economy:
                return 2;
        }
        return null;
    }

    @Override
    public TicketClass convertToEntityAttribute(Integer integer) {
        switch (integer) {
            case 1:
                return TicketClass.First;
            case 2:
                return TicketClass.Economy;
        }
        return null;
    }


}
