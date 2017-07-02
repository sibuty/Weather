package com.serioscompany.weather.di.module;

import com.serioscompany.weather.data.provider.NetworkProvider;
import com.serioscompany.weather.di.scope.CurrentWeather;
import com.serioscompany.weather.mvp.weather.CurrentWeatherPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 03.07.2017 0:04.
 */
@Module
public class CurrentWeatherPresenterModule {

  @Provides
  @CurrentWeather
  CurrentWeatherPresenter provideCurrentWeatherPresenter(final NetworkProvider networkProvider) {
    return new CurrentWeatherPresenter(networkProvider);
  }
}
