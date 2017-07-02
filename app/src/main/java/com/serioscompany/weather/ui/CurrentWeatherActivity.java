package com.serioscompany.weather.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.serioscompany.weather.App;
import com.serioscompany.weather.R;
import com.serioscompany.weather.data.response.json.ForecastDay;
import com.serioscompany.weather.di.component.CurrentWeatherComponent;
import com.serioscompany.weather.di.component.DaggerCurrentWeatherComponent;
import com.serioscompany.weather.di.module.CurrentWeatherPresenterModule;
import com.serioscompany.weather.mvp.weather.CurrentWeatherContract;
import com.serioscompany.weather.mvp.weather.CurrentWeatherPresenter;
import com.serioscompany.weather.ui.adapter.ForecastTenDaysAdapter;
import com.serioscompany.weather.ui.base.BaseActivity;
import com.serioscompany.weather.util.ProgressDialogHelper;

import java.util.List;

import butterknife.BindView;

public class CurrentWeatherActivity extends BaseActivity implements CurrentWeatherContract.View {

  private final CurrentWeatherComponent COMPONENT = DaggerCurrentWeatherComponent
      .builder()
      .appComponent(App.getAppComponent())
      .currentWeatherPresenterModule(new CurrentWeatherPresenterModule())
      .build();

  @BindView(R.id.rv_forecast_ten_days) protected RecyclerView rvForecast;
  @BindView(R.id.tv_title_weather) protected TextView tvForecastTitle;
  @BindView(R.id.tv_degrees) protected TextView tvDegrees;
  @BindView(R.id.iv_status_icon) protected AppCompatImageView ivStatusIcon;
  private boolean ready;
  private CurrentWeatherPresenter presenter;
  private ProgressDialogHelper progressDialogHelper;
  private ForecastTenDaysAdapter adapter;

  @Override
  public Context context() {
    return this;
  }

  @Override
  public void hideLoading() {
    progressDialogHelper.hide();
  }

  @Override
  public boolean isReady() {
    return ready;
  }

  @Override
  public void showError(final String message) {
    Toast.makeText(getBaseContext(), message, Toast.LENGTH_LONG).show();
  }

  @Override
  public void showLoading() {
    progressDialogHelper.show();
  }

  @Override
  public boolean onCreateOptionsMenu(final Menu menu) {
    getMenuInflater().inflate(R.menu.current_waether_menu, menu);
    return true;
  }

  @Override
  public void finish() {
    presenter.clearData();
    super.finish();
  }

  @Override
  public void updateView(final List<ForecastDay> forecastDays) {
    final ForecastDay currentDay = forecastDays.get(0);
    tvForecastTitle.setText(currentDay.getConditions());
    tvDegrees.setText(getString(R.string.degrees,
                                String.valueOf(Math.abs(currentDay.getHigh() - currentDay.getLow()))));
    Glide.with(this).load(currentDay.getIconUrl()).into(ivStatusIcon);
    adapter.setForecastDayList(forecastDays);
  }

  @Override
  protected int getLayout() {
    return R.layout.current_weather_activity;
  }

  @Override
  protected void initToolbar() {
    getToolbar().setTitle(R.string.current_weather_toolbar_title);
    super.initToolbar();
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    presenter = COMPONENT.currentWeatherPresenter();
    progressDialogHelper = COMPONENT.progressDialogHelper();
    progressDialogHelper.create(this, R.string.handle_search_results);
    presenter.setData(getIntent().getParcelableExtra(SearchWeatherActivity.EXTRA_CHOSEN_LOCATION));
    adapter = new ForecastTenDaysAdapter();
    rvForecast.setLayoutManager(new LinearLayoutManager(this));
    rvForecast.setAdapter(adapter);
    ready = true;
    presenter.attachView(this);
  }

  @Override
  public boolean onOptionsItemSelected(final MenuItem item) {
    switch (item.getItemId()) {
      case R.id.mi_update:
        presenter.updateForecast();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  @Override
  protected void onStart() {
    super.onStart();
    if (!presenter.wasUpdated()) {
      presenter.updateForecast();
    }
  }
}
