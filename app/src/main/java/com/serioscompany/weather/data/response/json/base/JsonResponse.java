package com.serioscompany.weather.data.response.json.base;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 02.07.2017 4:22.
 */
public abstract class JsonResponse {

  private final ResponseInfo responseInfo;

  @JsonCreator
  public JsonResponse(@JsonProperty("response") final ResponseInfo responseInfo) {
    this.responseInfo = responseInfo;
  }

  public ResponseInfo getResponseInfo() {
    return responseInfo;
  }
}
