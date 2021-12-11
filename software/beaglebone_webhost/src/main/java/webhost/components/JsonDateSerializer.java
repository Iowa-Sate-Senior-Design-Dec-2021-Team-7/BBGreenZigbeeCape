package webhost.components;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * {@code Component} used to serialize {@code Java.util.Calendar} from a {@code JSON} and vice a versa
 * <br>
 * @see com.fasterxml.jackson.databind.JsonSerializer
 */
public class JsonDateSerializer extends StdSerializer<Date> {
    
    /**
     * Format of the {@code Calendar} to serialize
     */
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public JsonDateSerializer() {
        this(null);
    }

    public JsonDateSerializer(Class t) {
        super(t);
    }

    @Override
    public void serialize (Date value, JsonGenerator gen, SerializerProvider arg2)
            throws IOException, JsonProcessingException {
        gen.writeString(dateFormat.format(value));
    }
}