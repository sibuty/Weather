package com.serioscompany.weather.mvp.weather;

import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.location.LocationServices;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.serioscompany.weather.R;
import com.serioscompany.weather.data.provider.NetworkProvider;
import com.serioscompany.weather.data.response.json.AutoCompleteResult;
import com.serioscompany.weather.data.response.json.ForecastDay;
import com.serioscompany.weather.data.response.json.ForecastTenDays;
import com.serioscompany.weather.mvp.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 02.07.2017 21:00.
 */
public class CurrentWeatherPresenter extends BasePresenter<AutoCompleteResult, CurrentWeatherContract.View>
    implements CurrentWeatherContract.Presenter, PermissionListener {

  private final NetworkProvider networkProvider;
  private boolean wasUpdated;

  public CurrentWeatherPresenter(final NetworkProvider networkProvider) {
    this.networkProvider = networkProvider;
  }

  @Override
  public void onPermissionGranted() {
    LocationServices
        .getFusedLocationProviderClient(getView().context())
        .getLastLocation()
        .addOnSuccessListener(location -> loadForecast(location.getLatitude(), location.getLongitude()));
  }

  @Override
  public void onPermissionDenied(final ArrayList<String> deniedPermissions) {
  }

  @Override
  public void updateForecast() {
    if (!verifyData()) {
      if (!checkPermision(ACCESS_COARSE_LOCATION) || !checkPermision(ACCESS_FINE_LOCATION)) {
        new TedPermission(getView().context())
            .setPermissionListener(this)
            .setRationaleMessage(R.string.message_need_location_permissions)
            .setRationaleConfirmText(R.string.button_next)
            .setDeniedMessage(R.string.error_denied_location_permission)
            .setDeniedCloseButtonText(R.string.button_close)
            .setGotoSettingButtonText(R.string.button_settings)
            .setPermissions(ACCESS_FINE_LOCATION)
            .check();
      } else {
        onPermissionGranted();
      }
    } else {
      final double latitude = Double.valueOf(getData().getLatitude());
      final double longitude = Double.valueOf(getData().getLongitude());
      loadForecast(latitude, longitude);
    }
  }

  @Override
  public boolean verifyData() {
    return getData() != null;
  }

  public boolean wasUpdated() {
    return wasUpdated;
  }

  private void OnComplete() {
    getView().hideLoading();
  }

  private boolean checkPermision(@NonNull final String permision) {
    return ActivityCompat.checkSelfPermission(getView().context(), permision) == PackageManager.PERMISSION_GRANTED;
  }

  private void loadForecast(final double latitude, final double longitude) {
    getView().showLoading();
    networkProvider
        .getForecast(latitude, longitude)
        .map(ForecastTenDays::getForecastDays)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(this::onHandle, this::onError, this::OnComplete);
  }

  private void onError(final Throwable throwable) {
    getView().showError(throwable.getLocalizedMessage());
    getView().hideLoading();
  }

  private void onHandle(final List<ForecastDay> forecastDays) {
    getView().updateView(forecastDays);
    setWasUpdated(true);
  }

  private void setWasUpdated(final boolean wasUpdated) {
    this.wasUpdated = wasUpdated;
  }
}
