package com.lukasgregori;

import com.lukasgregori.util.generators.BasicGenerator;
import com.lukasgregori.util.generators.InputGenerator;
import com.lukasgregori.util.generators.decorators.OverpassGenerator;
import com.lukasgregori.util.generators.decorators.TopologyGenerator;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class RoadNetworkInputGeneratorApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoadNetworkInputGeneratorApplication.class);

    private static final String INVALID_PARAMETERS = "The provided parameters are insufficient, " +
            "needed parameters are: radius latitude longitude";

    public static void main(String[] args) throws IOException {
        SpringApplication.run(RoadNetworkInputGeneratorApplication.class, args);

        if (parametersValid(args)) {
            int radius = Integer.parseInt(args[0]);
            double latitude = Double.parseDouble(args[1]);
            double longitude = Double.parseDouble(args[2]);
            startGeneration(radius, latitude, longitude);
        } else {
            LOGGER.error(INVALID_PARAMETERS);
        }
    }

    private static void startGeneration(int radius, double latitude, double longitude) {
        InputGenerator inputGenerator = new BasicGenerator();
        inputGenerator = new OverpassGenerator(inputGenerator);
        inputGenerator = new TopologyGenerator(inputGenerator);
        inputGenerator.perform(radius, latitude, longitude);
    }

    private static boolean parametersValid(String[] args) {
        return ArrayUtils.getLength(args) == 3 &&
                NumberUtils.isNumber(args[0]) &&
                NumberUtils.isNumber(args[1]) &&
                NumberUtils.isNumber(args[2]);
    }
}
