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

package com.lukasgregori.util;

import retrofit2.Retrofit;

/**
 * @author l.gregori@netconomy.net
 */
public class RetrofitUtil {

    public static Retrofit getRetrofitForBaseURL(String baseURL) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(baseURL);
        return builder.build();
    }
}
