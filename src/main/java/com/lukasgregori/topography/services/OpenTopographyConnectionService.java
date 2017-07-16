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

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author l.gregori@netconomy.net
 */
public interface OpenTopographyConnectionService {

    String BASE_URL = "http://opentopo.sdsc.edu/";

    @GET("/otr/getdem")
    Call<ResponseBody> getOpenTopographyResponse(
            @Query("demtype") String demtype,
            @Query("west") double west, @Query("south") double south,
            @Query("east") double east, @Query("north") double north
    );

}
