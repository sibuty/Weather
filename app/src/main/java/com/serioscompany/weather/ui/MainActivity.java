package com.serioscompany.weather.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;

import com.serioscompany.weather.R;
import com.serioscompany.weather.ui.base.BaseActivity;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {

  private static final int CODE_OPEN_SW = 101;

  @Override
  protected int getLayout() {
    return R.layout.main_activity;
  }

  @Override
  protected void initToolbar() {
    getToolbar().setTitle(R.string.main_activity_toolbar_title);
    super.initToolbar();
  }

  @Override
  protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
    if (resultCode == RESULT_OK
        && requestCode == CODE_OPEN_SW
        && data.hasExtra(SearchWeatherActivity.EXTRA_CHOSEN_LOCATION)) {
      data.setClass(this, CurrentWeatherActivity.class);
      startCurrentWeatherActivity(data);
    }
    super.onActivityResult(requestCode, resultCode, data);
  }

  @Override
  protected void onResume() {
    super.onResume();
    findViewById(R.id.b_current_weather).setEnabled(true);
    findViewById(R.id.b_search_weather).setEnabled(true);
  }

  @OnClick(R.id.b_current_weather)
  protected void openCurrentWeatherActivity(final View button) {
    button.setEnabled(false);
    startCurrentWeatherActivity(new Intent());
    button.setEnabled(false);
  }

  @OnClick(R.id.b_search_weather)
  protected void openSearchWeatherActivity(final View button) {
    button.setEnabled(false);
    startActivityForResult(new Intent(this, SearchWeatherActivity.class), CODE_OPEN_SW);
    button.setEnabled(true);
  }

  private void startCurrentWeatherActivity(@NonNull final Intent intent) {
    intent.setClass(this, CurrentWeatherActivity.class);
    startActivity(intent);
  }
}
