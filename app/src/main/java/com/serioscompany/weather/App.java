package com.serioscompany.weather;

import android.app.Application;
import android.support.annotation.NonNull;

import com.serioscompany.weather.di.component.AppComponent;
import com.serioscompany.weather.di.component.DaggerAppComponent;
import com.serioscompany.weather.di.module.AppModule;
import com.serioscompany.weather.di.module.NetworkProviderModule;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 27.06.2017 1:47.
 */
public class App extends Application{

  private static AppComponent appComponent;

  @NonNull
  public static AppComponent getAppComponent() {
    return appComponent;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    appComponent = buildComponent();
  }

  protected AppComponent buildComponent() {
    return DaggerAppComponent
        .builder()
        .appModule(new AppModule(this))
        .networkProviderModule(new NetworkProviderModule(BuildConfig.API_URL))
        .build();
  }

}
