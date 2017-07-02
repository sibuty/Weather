package com.serioscompany.weather.mvp.weather;

import com.serioscompany.weather.data.response.json.ForecastDay;
import com.serioscompany.weather.mvp.base.BaseView;

import java.util.List;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 02.07.2017 20:58.
 */
public interface CurrentWeatherContract {

  interface View extends BaseView {
    void updateView(List<ForecastDay> forecastDays);
  }

  interface Presenter {
    void updateForecast();
  }
}
