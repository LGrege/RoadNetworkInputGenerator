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

package com.lukasgregori.topography.services;

import com.lukasgregori.topography.util.TopologyQuery;

import java.awt.image.BufferedImage;
import java.util.Optional;

/**
 * @author l.gregori@netconomy.net
 */
public interface OpenTopographyService {

    Optional<BufferedImage> getTopographyImage(TopologyQuery query);
}
