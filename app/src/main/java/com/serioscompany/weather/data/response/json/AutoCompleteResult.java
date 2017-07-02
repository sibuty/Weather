package com.serioscompany.weather.data.response.json;

import android.support.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 02.07.2017 19:42.
 */
public class AutoCompleteResult implements Comparable<AutoCompleteResult> {

  private static final String C = "RU";

  private final String name;
  private final String type;
  private final String c;
  private final String zmw;
  private final String tz;
  private final String tzs;
  private final String l;
  private final String ll;
  private final String latitude;
  private final String longitude;

  @JsonCreator
  public AutoCompleteResult(@JsonProperty("name") final String name,
                            @JsonProperty("type") final String type,
                            @JsonProperty("c") final String c,
                            @JsonProperty("zmw") final String zmw,
                            @JsonProperty("tz") final String tz,
                            @JsonProperty("tzs") final String tzs,
                            @JsonProperty("l") final String l,
                            @JsonProperty("ll") final String ll,
                            @JsonProperty("lat") final String latitude,
                            @JsonProperty("lon") final String longitude) {
    this.name = name;
    this.type = type;
    this.c = c;
    this.zmw = zmw;
    this.tz = tz;
    this.tzs = tzs;
    this.l = l;
    this.ll = ll;
    this.latitude = latitude;
    this.longitude = longitude;
  }

  @Override
  public int compareTo(@NonNull final AutoCompleteResult o) {
    if (C.equals(o.getC())) {
      return C.equals(getC()) ? getName().compareTo(o.getName()) : 1;
    } else {
      return C.equals(getC()) ? -1 : getName().compareTo(o.getName());
    }
  }

  public String getC() {
    return c;
  }

  public String getL() {
    return l;
  }

  public String getLatitude() {
    return latitude;
  }

  public String getLl() {
    return ll;
  }

  public String getLongitude() {
    return longitude;
  }

  public String getName() {
    return name;
  }

  public String getType() {
    return type;
  }

  public String getTz() {
    return tz;
  }

  public String getTzs() {
    return tzs;
  }

  public String getZmw() {
    return zmw;
  }

  @Override
  public String toString() {
    return getName();
  }
}
