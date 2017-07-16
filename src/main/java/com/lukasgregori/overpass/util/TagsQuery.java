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

package com.lukasgregori.overpass.util;

import com.google.common.collect.Multimap;

import java.util.Map;

/**
 * @author Lukas Gregori
 */
public class TagsQuery {

    private static final String PREFIX = "[out:json][timeout:25];\n(\n";

    private static final String WAY_QUERY = "way[\"%s\"=\"%s\"](around:%d,%s,%s);\n";

    private static final String RELATION_QUERY = "relation[\"%s\"=\"%s\"](around:%d,%s,%s);\n";

    private static final String SUFFIX = "\n);out geom %s;";

    private final int radius;

    private final double latitude;

    private final double longitude;

    private final Multimap<String, String> tags;

    private final int maxResponseCount;

    public TagsQuery(int radius, double latitude, double longitude, Multimap<String, String> tags, int maxResponseCount) {
        this.radius = radius;
        this.latitude = latitude;
        this.longitude = longitude;
        this.tags = tags;
        this.maxResponseCount = maxResponseCount;
    }

    public String getFormattedDataQuery() {
        return PREFIX + getFormattedTags() +
                String.format(SUFFIX, maxResponseCount);
    }

    private String getFormattedTags() {
        StringBuilder builder = new StringBuilder();

        for (Map.Entry entry : tags.entries()) {
            builder.append(String.format(WAY_QUERY, entry.getKey(), entry.getValue(),
                    radius, latitude, longitude));
            builder.append(String.format(RELATION_QUERY, entry.getKey(), entry.getValue(),
                    radius, latitude, longitude));
        }

        return builder.toString();
    }
}