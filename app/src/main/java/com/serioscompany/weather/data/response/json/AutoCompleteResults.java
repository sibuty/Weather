package com.serioscompany.weather.data.response.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.List;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 02.07.2017 20:23.
 */
public class AutoCompleteResults {

  private final List<AutoCompleteResult> results;

  @JsonCreator
  public AutoCompleteResults(@JsonProperty("RESULTS") final List<AutoCompleteResult> results) {
    Collections.sort(results);
    this.results = results;
  }

  public List<AutoCompleteResult> getResults() {
    return Collections.unmodifiableList(results);
  }
}
