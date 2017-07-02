package com.serioscompany.weather.data.provider;

import com.serioscompany.weather.BuildConfig;
import com.serioscompany.weather.data.response.json.AutoCompleteResults;
import com.serioscompany.weather.data.response.json.ForecastTenDays;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 27.06.2017 1:52.
 */
public interface NetworkProvider {

  @GET(BuildConfig.AUTOCOMPLETE_URL + "/aq?h=0")
  Observable<AutoCompleteResults> getAqResults(@Query("query") String query);

  @GET("forecast10day/lang:RU/q/{latitude},{longitude}.json")
  Observable<ForecastTenDays> getForecast(@Path("latitude") double latitude, @Path("longitude") double longitude);
}
