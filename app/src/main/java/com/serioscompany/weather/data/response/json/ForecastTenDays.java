package com.serioscompany.weather.data.response.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.serioscompany.weather.data.deserializer.ForecastTenDaysDeserializer;

import java.util.Collections;
import java.util.List;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 02.07.2017 20:13.
 */
@JsonDeserialize(using = ForecastTenDaysDeserializer.class)
public class ForecastTenDays {

  private final List<ForecastDay> forecastDays;

  @JsonCreator
  public ForecastTenDays(@JsonProperty("forecastList") final List<ForecastDay> forecastDays) {
    this.forecastDays = forecastDays;
  }

  public List<ForecastDay> getForecastDays() {
    return Collections.unmodifiableList(forecastDays);
  }
}
