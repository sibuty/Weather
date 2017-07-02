package com.serioscompany.weather.data.response.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 02.07.2017 15:52.
 */
public class DateInfo {

  private final String epoch;
  private final String pretty;
  private final int sec;
  private final int min;
  private final int hour;
  private final int day;
  private final int month;
  private final int year;
  private final int yDay;
  private final String monthName;
  private final String monthNameShort;
  private final String weekDay;
  private final String weekDayShort;
  private final String amPm;
  private final String tzShort;
  private final String tzLong;

  @JsonCreator
  public DateInfo(@JsonProperty("epoch") final String epoch,
                  @JsonProperty("pretty") final String pretty,
                  @JsonProperty("sec") final int sec,
                  @JsonProperty("min") final String min,
                  @JsonProperty("hour") final int hour,
                  @JsonProperty("day") final int day,
                  @JsonProperty("month") final int month,
                  @JsonProperty("year") final int year,
                  @JsonProperty("yday") final int yDay,
                  @JsonProperty("monthname") final String monthName,
                  @JsonProperty("monthname_short") final String monthNameShort,
                  @JsonProperty("weekday") final String weekDay,
                  @JsonProperty("weekday_short") final String weekDayShort,
                  @JsonProperty("ampm") final String amPm,
                  @JsonProperty("tz_short") final String tzShort,
                  @JsonProperty("tz_long") final String tzLong) {
    this.epoch = epoch;
    this.pretty = pretty;
    this.sec = sec;
    this.min = Integer.valueOf(min);
    this.hour = hour;
    this.day = day;
    this.month = month;
    this.year = year;
    this.yDay = yDay;
    this.monthName = monthName;
    this.monthNameShort = monthNameShort;
    this.weekDay = weekDay;
    this.weekDayShort = weekDayShort;
    this.amPm = amPm;
    this.tzShort = tzShort;
    this.tzLong = tzLong;
  }

  public String getAmPm() {
    return amPm;
  }

  public int getDay() {
    return day;
  }

  public String getEpoch() {
    return epoch;
  }

  public int getHour() {
    return hour;
  }

  public int getMin() {
    return min;
  }

  public int getMonth() {
    return month;
  }

  public String getMonthName() {
    return monthName;
  }

  public String getMonthNameShort() {
    return monthNameShort;
  }

  public String getPretty() {
    return pretty;
  }

  public int getSec() {
    return sec;
  }

  public String getTzLong() {
    return tzLong;
  }

  public String getTzShort() {
    return tzShort;
  }

  public String getWeekDay() {
    return weekDay;
  }

  public String getWeekDayShort() {
    return weekDayShort;
  }

  public int getYear() {
    return year;
  }

  public int getyDay() {
    return yDay;
  }
}
