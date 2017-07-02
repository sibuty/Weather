package com.serioscompany.weather.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 03.07.2017 0:05.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface CurrentWeather {
}
