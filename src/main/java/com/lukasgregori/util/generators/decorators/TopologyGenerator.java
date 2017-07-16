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

import com.lukasgregori.topography.services.OpenTopographyService;
import com.lukasgregori.topography.util.TopologyQuery;
import com.lukasgregori.util.generators.InputGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

/**
 * @author Lukas Gregori
 */
public class TopologyGenerator extends GeneratorDecorator {

    private static final Logger LOGGER = LoggerFactory.getLogger(TopologyGenerator.class);

    private static final String OUTPUT_FILE_NAME = "topology.tif";

    private static final String SERVICE = "openTopographyService";

    public TopologyGenerator(InputGenerator task) {
        super(task);
    }

    @Override
    public void perform(int radius, double latitude, double longitude) {
        super.perform(radius, latitude, longitude);
        LOGGER.info("TopologyGenerator: Generating topology map...");
        generateTopologySample(radius, latitude, longitude);
        LOGGER.info("TopologyGenerator: Done...");
    }

    private static void generateTopologySample(int radius, double latitude, double longitude) {
        OpenTopographyService topographyService = (OpenTopographyService) context.getBean(SERVICE);
        TopologyQuery topologyQuery = new TopologyQuery(radius, latitude, longitude);
        Optional<BufferedImage> opt = topographyService.getTopographyImage(topologyQuery);
        opt.ifPresent(TopologyGenerator::saveAndBrightenImage);
    }

    private static void saveAndBrightenImage(BufferedImage image) {
        try {
            RescaleOp rescaleOp = new RescaleOp(25.0f, 15, null);
            rescaleOp.filter(image, image);
            ImageIO.write(image, "tif", new File(OUTPUT_FILE_NAME));
        } catch (IOException e) {
            LOGGER.debug("Error saving TopologyImage: " + e.getMessage());
        }
    }
}
