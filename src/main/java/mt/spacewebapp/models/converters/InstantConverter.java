package mt.spacewebapp.models.converters;

import javax.persistence.AttributeConverter;
import java.time.Instant;

public class InstantConverter implements AttributeConverter<Instant, String> {
    @Override
    public String convertToDatabaseColumn(Instant instant) {
        return instant.toString();
    }

    @Override
    public Instant convertToEntityAttribute(String s) {
        return Instant.parse(s);
    }
}
