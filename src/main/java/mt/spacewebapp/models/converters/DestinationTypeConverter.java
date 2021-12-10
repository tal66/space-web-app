package mt.spacewebapp.models.converters;

import mt.spacewebapp.models.enums.DestinationType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Converter
public class DestinationTypeConverter implements AttributeConverter<DestinationType, Integer> {
    static Map<Integer, List<DestinationType>> map = Arrays.stream(DestinationType.values())
            .collect(Collectors.groupingBy(DestinationType::getValue));

    @Override
    public Integer convertToDatabaseColumn(DestinationType destinationType) {
        return destinationType.getValue();
    }

    @Override
    public DestinationType convertToEntityAttribute(Integer integer) {
        List<DestinationType> list = map.get(integer);
        return (list == null || list.isEmpty())? null : list.get(0);
    }
}
