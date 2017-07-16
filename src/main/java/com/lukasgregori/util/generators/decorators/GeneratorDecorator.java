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

import com.lukasgregori.util.generators.InputGenerator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Lukas Gregori
 */
public class GeneratorDecorator implements InputGenerator {

    static final ApplicationContext context = new ClassPathXmlApplicationContext("inputgen-config.xml");

    private InputGenerator task;

    GeneratorDecorator(InputGenerator task) {
        this.task = task;
    }

    @Override
    public void perform(int radius, double latitude, double longitude) {
        this.task.perform(radius, latitude, longitude);
    }
}
