package com.serioscompany.weather.data.response.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 02.07.2017 15:37.
 */
public class Period {

  private final String iconName;
  private final String iconUrl;
  private final String title;
  private final String fctText;
  private final String fctTextMetric;

  @JsonCreator
  public Period(@JsonProperty("icon") final String iconName,
                @JsonProperty("icon_url") final String iconUrl,
                @JsonProperty("title") final String title,
                @JsonProperty("fcttext") final String fctText,
                @JsonProperty("fcttext_metric") final String fctTextMetric) {
    this.iconName = iconName;
    this.iconUrl = iconUrl;
    this.title = title;
    this.fctText = fctText;
    this.fctTextMetric = fctTextMetric;
  }

  public String getFctText() {
    return fctText;
  }

  public String getFctTextMetric() {
    return fctTextMetric;
  }

  public String getIconName() {
    return iconName;
  }

  public String getIconUrl() {
    return iconUrl;
  }

  public String getTitle() {
    return title;
  }
}
