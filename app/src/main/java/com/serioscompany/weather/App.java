package com.serioscompany.weather;

import android.app.Application;
import android.support.annotation.NonNull;

import com.serioscompany.weather.di.component.AppComponent;
import com.serioscompany.weather.di.component.DaggerAppComponent;
import com.serioscompany.weather.di.module.AppModule;
import com.serioscompany.weather.di.module.NetworkProviderModule;
import com.serioscompany.weather.di.module.ProgressDialogHelperModule;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 27.06.2017 1:47.
 */
public class App extends Application {

  private static AppComponent appComponent = null;
  private static App app = null;

  @Override
  public void onCreate() {
    super.onCreate();
    app = this;
    appComponent = buildComponent();
  }

  protected AppComponent buildComponent() {
    return DaggerAppComponent
        .builder()
        .appModule(new AppModule(this))
        .networkProviderModule(new NetworkProviderModule(BuildConfig.API_URL))
        .progressDialogHelperModule(new ProgressDialogHelperModule())
        .build();
  }

  public static App getApp() {
    return app;
  }

  @NonNull
  public static AppComponent getAppComponent() {
    return appComponent;
  }
}
