package mt.spacewebapp.models.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.Optional;

@Converter
public class TicketClassConverter implements AttributeConverter<TicketClass, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TicketClass ticketClass) {
        return ticketClass.getValue();
    }

    @Override
    public TicketClass convertToEntityAttribute(Integer integer) {
        Optional<TicketClass> optional = Arrays.stream(TicketClass.values())
                .filter(s -> s.getValue() == integer)
                .findFirst();
        if (optional.isEmpty()){
            return null;
        }
        return optional.get();
    }


}
