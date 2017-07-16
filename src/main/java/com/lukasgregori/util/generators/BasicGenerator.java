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

package com.lukasgregori.util.generators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Lukas Gregori
 */
public class BasicGenerator implements InputGenerator {

    private static final String GENERATION_MESSAGE = "Starting input generation for: Lat(%s) - Lon(%s) - Radius(%d)";

    private static final Logger LOGGER = LoggerFactory.getLogger(BasicGenerator.class);

    @Override
    public void perform(int radius, double latitude, double longitude) {
        LOGGER.info(String.format(GENERATION_MESSAGE, latitude, longitude, radius));
    }
}
