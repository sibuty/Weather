package com.serioscompany.weather.mvp.search;

import com.serioscompany.weather.data.response.json.AutoCompleteResult;
import com.serioscompany.weather.mvp.base.BaseView;

import java.util.List;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 02.07.2017 20:58.
 */
public interface SearchWeatherContract {

  interface View extends BaseView {
    void toggleProgress(final boolean toggle);
  }

  interface Presenter {

    List<AutoCompleteResult> loadData(final String mask);
  }
}
