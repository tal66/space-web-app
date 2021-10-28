package mt.spacewebapp.models.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Converter
public class TicketStatusConverter implements AttributeConverter<TicketStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(TicketStatus ticketStatus) {
        return ticketStatus.getValue();
    }

    @Override
    public TicketStatus convertToEntityAttribute(Integer integer) {
        List<TicketStatus> list = Arrays.stream(TicketStatus.values())
                .filter(s -> s.getValue() == integer)
                .collect(Collectors.toList());
        if (list.isEmpty()){
            return null;
        }
        return list.get(0);
    }
}
