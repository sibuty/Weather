package com.serioscompany.weather.data.response.json.base;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.Map;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 02.07.2017 4:23.
 */
public class ResponseInfo {

  private final String version;
  private final String termsofService;
  private final Map<String, Object> features;

  @JsonCreator
  public ResponseInfo(@JsonProperty("version") final String version,
                      @JsonProperty("termsofService") final String termsofService,
                      @JsonProperty("features") final Map<String, Object> features) {
    this.version = version;
    this.termsofService = termsofService;
    this.features = features;
  }

  public Map<String, Object> getFeatures() {
    return Collections.unmodifiableMap(features);
  }

  public String getTermsofService() {
    return termsofService;
  }

  public String getVersion() {
    return version;
  }
}
