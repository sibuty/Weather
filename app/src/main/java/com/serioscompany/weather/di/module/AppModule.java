package com.serioscompany.weather.di.module;

import com.serioscompany.weather.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 27.06.2017 1:48.
 */
@Module(includes = { NetworkProviderModule.class})
public class AppModule {

  private final App app;

  public AppModule(final App app) {
    this.app = app;
  }

  @Provides
  @Singleton
  App provideApp() {
    return app;
  }
}