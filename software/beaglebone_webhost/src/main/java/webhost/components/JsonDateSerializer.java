package webhost.components;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * {@code Component} used to serialize {@code Java.util.Calendar} from a {@code JSON} and vice a versa
 * <br>
 * @see com.fasterxml.jackson.databind.JsonSerializer
 */
public class JsonDateSerializer extends JsonSerializer<LocalDateTime> {
    
    /**
     * Format of the {@code Calendar} to serialize
     */
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
    
    /**
     * Serializes a {@code Calendar} {@code JSON} into a {@code Calendar} {@code Object}
     * @param date
     * 		{@code Calendar} to serialize
     * @param gen
     * 		@see com.fasterxml.jackson.core.JsonGenerator
     * @param provider
     * 		@see com.fasterxml.jackson.databind.SerializerProvider
     * @throws IOException
     */
    @Override
    public void serialize(LocalDateTime date, JsonGenerator gen, SerializerProvider provider) throws IOException {
        
        String formattedDate = date.format(DateTimeFormatter.ofPattern(dateFormat.toPattern()));
        
        gen.writeString(formattedDate);
    }
}