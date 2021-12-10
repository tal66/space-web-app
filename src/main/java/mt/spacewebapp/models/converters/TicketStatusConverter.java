package mt.spacewebapp.models.converters;

import mt.spacewebapp.models.enums.TicketStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Converter
public class TicketStatusConverter implements AttributeConverter<TicketStatus, Integer> {
    static Map<Integer, List<TicketStatus>> map = Arrays.stream(TicketStatus.values())
            .collect(Collectors.groupingBy(TicketStatus::getValue));

    @Override
    public Integer convertToDatabaseColumn(TicketStatus ticketStatus) {
        return ticketStatus.getValue();
    }

    @Override
    public TicketStatus convertToEntityAttribute(Integer integer) {
        List<TicketStatus> list = map.get(integer);
        return (list == null || list.isEmpty())? null : list.get(0);
    }
}
