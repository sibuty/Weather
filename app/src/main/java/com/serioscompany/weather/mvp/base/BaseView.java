package com.serioscompany.weather.mvp.base;

import android.content.Context;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 02.07.2017 20:55.
 */
public interface BaseView {

  Context context();

  void hideLoading();

  boolean isReady();

  void showError(final String message);

  void showLoading();
}
