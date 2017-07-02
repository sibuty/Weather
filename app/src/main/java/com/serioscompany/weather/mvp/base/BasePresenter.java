package com.serioscompany.weather.mvp.base;

import android.support.annotation.NonNull;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 02.07.2017 20:55.
 */
public abstract class BasePresenter<T, V extends BaseView> {

  private final CompositeSubscription subscriptions = new CompositeSubscription();
  private V view;
  private T data;

  public abstract boolean verifyData();

  public void addSubscription(final Subscription subscription) {
    subscriptions.add(subscription);
  }

  public void attachView(@NonNull final V view) {
    this.view = view;
  }

  public void clearData() {
    setData(null);
  }

  public void detachView() {
    view = null;
    subscriptions.clear();
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  protected V getView() {
    return view;
  }
}
