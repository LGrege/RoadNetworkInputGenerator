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

package com.lukasgregori.overpass.services.impl;

import com.lukasgregori.overpass.services.OverpassService;
import com.lukasgregori.overpass.services.OverpassConnectionService;
import com.lukasgregori.util.RetrofitUtil;
import com.lukasgregori.overpass.util.TagsQuery;
import okhttp3.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;

/**
 * @author Lukas Gregori
 */
public class OverpassServiceImpl implements OverpassService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OverpassService.class);

    @Override
    public String getOSMDataForQuery(TagsQuery query) {
        try {
            Response response = getResponseForQuery(query);
            ResponseBody responseBody = (ResponseBody) response.body();
            return response.isSuccessful() ? responseBody.string() : "";
        } catch (IOException e) {
            LOGGER.debug("Error reading OSMData: " + e.getMessage());
            return "";
        }
    }

    private static Response getResponseForQuery(TagsQuery query) throws IOException {
        String baseURL = OverpassConnectionService.BASE_URL;
        Retrofit retrofit = RetrofitUtil.getRetrofitForBaseURL(baseURL);
        OverpassConnectionService service = retrofit.create(OverpassConnectionService.class);
        Call<ResponseBody> responseBody = service.getOverpassResponse(query.getFormattedDataQuery());
        return responseBody.execute();
    }
}
