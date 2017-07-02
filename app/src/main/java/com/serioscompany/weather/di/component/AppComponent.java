package com.serioscompany.weather.di.component;

import com.serioscompany.weather.App;
import com.serioscompany.weather.data.provider.NetworkProvider;
import com.serioscompany.weather.di.module.AppModule;
import com.serioscompany.weather.util.ProgressDialogHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 27.06.2017 1:49.
 */
@Singleton
@Component(modules = {
    AppModule.class
})
public interface AppComponent {

  App app();

  NetworkProvider networkProvider();

  ProgressDialogHelper progressDialogHelper();

}
