package mt.spacewebapp.models.converters;

import mt.spacewebapp.models.enums.TicketClass;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Converter
public class TicketClassConverter implements AttributeConverter<TicketClass, Integer> {
    static Map<Integer, List<TicketClass>> map = Arrays.stream(TicketClass.values())
            .collect(Collectors.groupingBy(TicketClass::getValue));

    @Override
    public Integer convertToDatabaseColumn(TicketClass ticketClass) {
        return ticketClass.getValue();
    }

    @Override
    public TicketClass convertToEntityAttribute(Integer integer) {
        List<TicketClass> list = map.get(integer);
        return (list == null || list.isEmpty())? null : list.get(0);
    }


}
