package mt.spacewebapp.models.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.Optional;

@Converter
public class TicketStatusConverter implements AttributeConverter<TicketStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(TicketStatus ticketStatus) {
        return ticketStatus.getValue();
    }

    @Override
    public TicketStatus convertToEntityAttribute(Integer integer) {
        Optional<TicketStatus> optional = Arrays.stream(TicketStatus.values())
                .filter(s -> s.getValue() == integer)
                .findFirst();
        if (optional.isEmpty()){
            return null;
        }
        return optional.get();
    }
}
