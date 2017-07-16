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

package com.lukasgregori.topography.services.impl;

import com.lukasgregori.topography.services.OpenTopographyConnectionService;
import com.lukasgregori.topography.services.OpenTopographyService;
import com.lukasgregori.util.RetrofitUtil;
import com.lukasgregori.topography.util.TopologyQuery;
import okhttp3.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

/**
 * @author l.gregori@netconomy.net
 */
public class OpenTopographyServiceImpl implements OpenTopographyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OpenTopographyService.class);

    private static final String DEMTYPE = "SRTMGL3";

    @Override
    public Optional<BufferedImage> getTopographyImage(TopologyQuery query) {
        try {
            Response response = getResponseForQuery(query);
            return getImageFromResponse(response);
        } catch (IOException e) {
            LOGGER.debug("Error reading topography: " + e.getMessage());
            return Optional.empty();
        }
    }

    private static Response getResponseForQuery(TopologyQuery query) throws IOException {
        String baseURL = OpenTopographyConnectionService.BASE_URL;
        Retrofit retrofit = RetrofitUtil.getRetrofitForBaseURL(baseURL);
        OpenTopographyConnectionService service = retrofit.create(OpenTopographyConnectionService.class);
        Call<ResponseBody> responseBody = service.getOpenTopographyResponse(DEMTYPE,
                query.west, query.south, query.east, query.north);
        return responseBody.execute();
    }

    private static Optional<BufferedImage> getImageFromResponse(Response response) throws IOException {
        if (response.isSuccessful()) {
            ResponseBody responseBody = (ResponseBody) response.body();
            InputStream inputStream = responseBody.byteStream();
            return Optional.ofNullable(ImageIO.read(inputStream));
        }
        return Optional.empty();
    }
}
