package com.serioscompany.weather.di.component;

import com.serioscompany.weather.di.module.CurrentWeatherPresenterModule;
import com.serioscompany.weather.di.scope.CurrentWeather;
import com.serioscompany.weather.mvp.weather.CurrentWeatherPresenter;
import com.serioscompany.weather.util.ProgressDialogHelper;

import dagger.Component;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 03.07.2017 0:05.
 */
@CurrentWeather
@Component(dependencies = AppComponent.class, modules = CurrentWeatherPresenterModule.class)
public interface CurrentWeatherComponent {

  CurrentWeatherPresenter currentWeatherPresenter();

  ProgressDialogHelper progressDialogHelper();
}

