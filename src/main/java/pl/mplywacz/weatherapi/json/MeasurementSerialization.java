package pl.mplywacz.weatherapi.json;
/*
Author: BeGieU
Date: 22.05.2019
*/

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import pl.mplywacz.weatherapi.model.Measurement;

import java.io.IOException;

public class MeasurementSerialization extends StdSerializer<Measurement> {
    public MeasurementSerialization() {
        this(null);
    }

    public MeasurementSerialization(Class<Measurement> t) {
        super(t);
    }

    @Override
    public void serialize(Measurement value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        jgen.writeStartObject();
        jgen.writeNumberField("measurementId", value.getMeasurementId());
        jgen.writeStringField("temperature", value.getTemp());
        jgen.writeStringField("maxTemperature", value.getTempMax());
        jgen.writeStringField("minTemperature", value.getTempMin());
        jgen.writeStringField("humidity", value.getHumidity());
        jgen.writeStringField("pressure", value.getPressure());
        jgen.writeNumberField("ownerLocation",value.getLocation().getLocationId());

        jgen.writeEndObject();
    }
}
