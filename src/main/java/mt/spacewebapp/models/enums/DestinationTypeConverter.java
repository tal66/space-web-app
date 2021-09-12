package mt.spacewebapp.models.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class DestinationTypeConverter implements AttributeConverter<DestinationType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(DestinationType destinationType) {
        switch (destinationType) {
            case PLANET:
                return 1;
            case DWARF_PLANET:
                return 2;
            case MOON:
                return 3;
            case STAR:
                return 4;
            case OTHER:
                return 5;
        }
        return null;
    }

    @Override
    public DestinationType convertToEntityAttribute(Integer integer) {
        switch (integer) {
            case 1:
                return DestinationType.PLANET;
            case 2:
                return DestinationType.DWARF_PLANET;
            case 3:
                return DestinationType.MOON;
            case 4:
                return DestinationType.STAR;
            case 5:
                return DestinationType.OTHER;
        }
        return null;
    }
}
