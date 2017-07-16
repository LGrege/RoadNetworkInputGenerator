package com.lukasgregori;

import com.lukasgregori.util.generators.BasicGenerator;
import com.lukasgregori.util.generators.InputGenerator;
import com.lukasgregori.util.generators.decorators.OverpassGenerator;
import com.lukasgregori.util.generators.decorators.TopologyGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class RoadNetworkInputGeneratorApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(RoadNetworkInputGeneratorApplication.class, args);

        InputGenerator inputGenerator = new BasicGenerator();
        inputGenerator = new OverpassGenerator(inputGenerator);
        inputGenerator = new TopologyGenerator(inputGenerator);
        inputGenerator.perform(20000, 47.0666667, 15.45);
    }
}
