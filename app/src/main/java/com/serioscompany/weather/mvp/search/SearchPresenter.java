package com.serioscompany.weather.mvp.search;

import android.os.Handler;

import com.serioscompany.weather.data.provider.NetworkProvider;
import com.serioscompany.weather.data.response.json.AutoCompleteResult;
import com.serioscompany.weather.data.response.json.AutoCompleteResults;
import com.serioscompany.weather.mvp.base.BasePresenter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 02.07.2017 21:00.
 */
public class SearchPresenter extends BasePresenter<AutoCompleteResult, SearchWeatherContract.View>
    implements SearchWeatherContract.Presenter {

  private static final long DENOUNCED_TIME = TimeUnit.MICROSECONDS.toMillis(500L);
  private final NetworkProvider networkProvider;
  private long lastRequestTime;

  public SearchPresenter(final NetworkProvider networkProvider) {
    this.networkProvider = networkProvider;
  }

  @Override
  public List<AutoCompleteResult> loadData(final String mask) {
    final List<AutoCompleteResult> results = new ArrayList<>();
    if (bounceEnds()) {
      addSubscription(networkProvider
                          .getAqResults(mask)
                          .map(AutoCompleteResults::getResults)
                          .subscribe(results::addAll, this::onError));
    }
    return results;
  }

  @Override
  public boolean verifyData() {
    return getData() != null;
  }

  private boolean bounceEnds() {
    final long currentTime = new Date().getTime();
    final boolean result = currentTime - lastRequestTime > DENOUNCED_TIME;
    if (result) {
      setLastRequestTime(currentTime);
    }
    return result;
  }

  private void onError(final Throwable throwable) {
  }

  private void runOnUiThread(final Runnable runnable) {
    new Handler(getView().context().getMainLooper()).post(runnable);
  }

  private void setLastRequestTime(final long lastRequestTime) {
    this.lastRequestTime = lastRequestTime;
  }
}
