package pl.mplywacz.weatherapi.json;
/*
Author: BeGieU
Date: 22.05.2019
*/

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import pl.mplywacz.weatherapi.model.Measurement;

import java.io.IOException;

public class MeasurementDeserialization extends StdDeserializer<Measurement> {
    public MeasurementDeserialization() {
        this(null);
    }

    public MeasurementDeserialization(Class<?> vc) {
        super(vc);
    }

    @Override public Measurement deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        String temp = (node.get("main").get("temp")).asText();
        String temp_min = (node.get("main").get("temp_min")).asText();
        String temp_max = (node.get("main").get("temp_max")).asText();
        String humidity = (node.get("main").get("humidity")).asText();
        String pressure = (node.get("main").get("pressure")).asText();

        var measurement = new Measurement();
        measurement.setTemp_min(temp_min);
        measurement.setTemp_max(temp_max);
        measurement.setTemp(temp);
        measurement.setHumidity(humidity);
        measurement.setPressure(pressure);

        return measurement;
    }
}
