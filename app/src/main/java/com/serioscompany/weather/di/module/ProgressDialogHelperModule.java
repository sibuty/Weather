package com.serioscompany.weather.di.module;

import com.serioscompany.weather.util.ProgressDialogHelper;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 02.07.2017 22:09.
 */
@Module
public class ProgressDialogHelperModule {

  @Provides
  ProgressDialogHelper provideProgressDialogHelper() {
    return new ProgressDialogHelper();
  }
}
