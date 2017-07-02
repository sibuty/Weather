package com.serioscompany.weather.data.response.json;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 02.07.2017 15:52.
 */
public class ForecastDay {

  private static final String KEY_CELSIUS = "celsius";

  private final Period day;
  private final Period night;
  private final DateInfo dateInfo;
  private final int high;
  private final int low;
  private final String conditions;
  private final String iconName;
  private final String iconUrl;

  public ForecastDay(final Period day,
                     final Period night,
                     final DateInfo dateInfo,
                     final Map<String, String> high,
                     final Map<String, String> low,
                     final String conditions,
                     final String iconName,
                     final String iconUrl) {
    this.day = day;
    this.night = night;
    this.dateInfo = dateInfo;
    this.high = handleEmptyString(high.get(KEY_CELSIUS));
    this.low = handleEmptyString(low.get(KEY_CELSIUS));
    this.conditions = conditions;
    this.iconName = iconName;
    this.iconUrl = iconUrl;
  }

  public String getConditions() {
    return conditions;
  }

  public DateInfo getDateInfo() {
    return dateInfo;
  }

  public Period getDay() {
    return day;
  }

  public int getHigh() {
    return high;
  }

  public String getIconName() {
    return iconName;
  }

  public String getIconUrl() {
    return iconUrl;
  }

  public int getLow() {
    return low;
  }

  public Period getNight() {
    return night;
  }

  private int handleEmptyString(final String number) {
    return StringUtils.isNotBlank(number) ? Integer.valueOf(number) : 0;
  }
}
