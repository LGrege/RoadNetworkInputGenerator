/*********************************************************************
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * Lukas Gregori
 * lukas.gregori@student.tugraz.at
 * www.lukasgregori.com
 *
 * (c) 2017 by Lukas Gregori
 *********************************************************************/

package com.lukasgregori.util.generators.decorators;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.lukasgregori.overpass.services.OverpassService;
import com.lukasgregori.overpass.util.TagsQuery;
import com.lukasgregori.util.generators.InputGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Lukas Gregori
 */
public class OverpassGenerator extends GeneratorDecorator {

    private static final Logger LOGGER = LoggerFactory.getLogger(OverpassGenerator.class);

    private static final String OUTPUT_FILE_NAME = "terrain.json";

    private static final int MAX_RESULT_COUNT = 1000;

    private static final String SERVICE = "overpassService";

    private static final Multimap<String, String> TAGS = ImmutableMultimap.of(
            "natural", "water",
            "natural", "wood",
            "landuse", "forest"
    );

    public OverpassGenerator(InputGenerator task) {
        super(task);
    }

    @Override
    public void perform(int radius, double latitude, double longitude) {
        super.perform(radius, latitude, longitude);
        LOGGER.info("OverpassGenerator: Generating OSMJson...");
        generateOverpassSample(radius, latitude, longitude);
        LOGGER.info("OverpassGenerator: Done...");
    }

    private static void generateOverpassSample(int radius, double latitude, double longitude) {
        OverpassService overpassService = (OverpassService) context.getBean(SERVICE);
        TagsQuery nodesQuery = new TagsQuery(radius, latitude, longitude, TAGS, MAX_RESULT_COUNT);
        String response = overpassService.getOSMDataForQuery(nodesQuery);
        exportOSMJSON(response);
    }

    private static void exportOSMJSON(final String json) {
        try {
            PrintWriter writer = new PrintWriter(OUTPUT_FILE_NAME, "UTF-8");
            writer.println(json);
            writer.close();
        } catch (IOException e) {
            LOGGER.debug("Error exporting OSMJson: " + e.getMessage());
        }
    }
}
