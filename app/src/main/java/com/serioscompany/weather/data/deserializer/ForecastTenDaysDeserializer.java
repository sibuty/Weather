package com.serioscompany.weather.data.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.serioscompany.weather.data.response.json.DateInfo;
import com.serioscompany.weather.data.response.json.ForecastDay;
import com.serioscompany.weather.data.response.json.ForecastTenDays;
import com.serioscompany.weather.data.response.json.Period;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 02.07.2017 17:06.
 */
public class ForecastTenDaysDeserializer extends JsonDeserializer<ForecastTenDays> {

  private static final String KEY_FORECAST = "forecast";
  private static final String KEY_TXT_FORECAST = "txt_forecast";
  private static final String KEY_SIMPLEFORECAST = "simpleforecast";
  private static final String KEY_FORECASTDAY = "forecastday";
  private static final String KEY_DATE = "date";
  private static final String KEY_HIGH = "high";
  private static final String KEY_LOW = "low";
  private static final String KEY_CONDITIONS = "conditions";
  private static final String KEY_ICON = "icon";
  private static final String KEY_ICON_URL = "icon_url";

  @Override
  public ForecastTenDays deserialize(final JsonParser jp, final DeserializationContext ctxt)
      throws IOException, JsonProcessingException {
    final ObjectCodec codec = jp.getCodec();
    final TreeNode forecastNode = jp.readValueAsTree().get(KEY_FORECAST);
    final TreeNode periodNodes = forecastNode.get(KEY_TXT_FORECAST).get(KEY_FORECASTDAY);
    final List<Period> periods = new ArrayList<>(periodNodes.size());
    for (int i = 0; i < periodNodes.size(); i++) {
      periods.add(periodNodes.get(i).traverse(codec).readValueAs(Period.class));
    }
    final ArrayNode dayNodes = (ArrayNode) forecastNode.get(KEY_SIMPLEFORECAST).get(KEY_FORECASTDAY);
    final List<ForecastDay> forecastDayList = new ArrayList<>(dayNodes.size());
    for (int i = 0; i < dayNodes.size(); i++) {
      final int periodIndex = i * 2;
      final Period day = periods.get(periodIndex);
      final Period night = periods.get(periodIndex + 1);
      final DateInfo dateInfo =
          dayNodes.get(i).get(KEY_DATE).traverse(codec).readValueAs(new TypeReference<DateInfo>() {
          });
      final Map<String, String> hight =
          dayNodes.get(i).get(KEY_HIGH).traverse(codec).readValueAs(new TypeReference<Map<String, String>>() {
          });
      final Map<String, String> low =
          dayNodes.get(i).get(KEY_LOW).traverse(codec).readValueAs(new TypeReference<Map<String, String>>() {
          });
      final String conditions = dayNodes.get(i).get(KEY_CONDITIONS).asText();
      final String iconName = dayNodes.get(i).get(KEY_ICON).asText();
      final String iconUrl = dayNodes.get(i).get(KEY_ICON_URL).asText();
      forecastDayList.add(new ForecastDay(day, night, dateInfo, hight, low, conditions, iconName, iconUrl));
    }
    return new ForecastTenDays(forecastDayList);
  }
}
