package mt.spacewebapp.models.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.Optional;

@Converter
public class DestinationTypeConverter implements AttributeConverter<DestinationType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(DestinationType destinationType) {
        return destinationType.getValue();
    }

    @Override
    public DestinationType convertToEntityAttribute(Integer integer) {
        Optional<DestinationType> optional = Arrays.stream(DestinationType.values())
                .filter(s -> s.getValue() == integer)
                .findFirst();
        if (optional.isEmpty()){
            return null;
        }
        return optional.get();
    }
}
