package com.serioscompany.weather.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ProgressBar;

import com.serioscompany.weather.App;
import com.serioscompany.weather.R;
import com.serioscompany.weather.data.response.json.AutoCompleteResult;
import com.serioscompany.weather.di.component.DaggerSearchComponent;
import com.serioscompany.weather.di.component.SearchComponent;
import com.serioscompany.weather.di.module.SearchPresenterModule;
import com.serioscompany.weather.mvp.search.SearchPresenter;
import com.serioscompany.weather.mvp.search.SearchWeatherContract;
import com.serioscompany.weather.ui.adapter.SearchWeatherAdapter;
import com.serioscompany.weather.ui.base.BaseActivity;
import com.serioscompany.weather.util.ProgressDialogHelper;

import org.apache.commons.lang3.StringUtils;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class SearchWeatherActivity extends BaseActivity implements SearchWeatherContract.View {

  public static final String EXTRA_CHOSEN_LOCATION = "chosen_location_extra";
  public static final int PADDING_FOR_PROGRESS = dpToPx(40.0F);

  private final SearchComponent COMPONENT = DaggerSearchComponent
      .builder()
      .appComponent(App.getAppComponent())
      .searchPresenterModule(new SearchPresenterModule())
      .build();

  @BindView(R.id.til_location) protected TextInputLayout tilLocation;
  @BindView(R.id.act_location) protected AutoCompleteTextView actLocation;
  @BindView(R.id.pb_load_results) protected ProgressBar progressBar;
  private boolean ready;
  private SearchPresenter presenter;
  private ProgressDialogHelper progressDialogHelper;
  private int defaultRightPadding;

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
    tilLocation.setError(message);
  }

  @Override
  public void showLoading() {
    progressDialogHelper.show();
  }

  @Override
  public void finish() {
    presenter.clearData();
    super.finish();
  }

  @Override
  public void toggleProgress(final boolean toggle) {
    runOnUiThread(() -> {
      final int left = actLocation.getPaddingLeft();
      final int top = actLocation.getPaddingTop();
      final int right = toggle ? PADDING_FOR_PROGRESS : defaultRightPadding;
      final int bottom = actLocation.getPaddingBottom();
      actLocation.setPadding(left, top, right, bottom);
    });
    runOnUiThread(() -> progressBar.setVisibility(toggle ? View.VISIBLE : View.GONE));
  }

  @Override
  protected int getLayout() {
    return R.layout.search_weather_activity;
  }

  @Override
  protected void initToolbar() {
    getToolbar().setTitle(R.string.search_weather_toolbar_title);
    super.initToolbar();
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    defaultRightPadding = actLocation.getPaddingRight();
    presenter = COMPONENT.searchPresenter();
    progressDialogHelper = COMPONENT.progressDialogHelper();
    progressDialogHelper.create(this, R.string.handle_search_results);
    actLocation.setAdapter(new SearchWeatherAdapter<>(this, presenter::loadData, this::toggleProgress));
    actLocation.setOnItemClickListener(this::itemWasClicked);
    ready = true;
    presenter.attachView(this);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    presenter.detachView();
    progressDialogHelper.onDestroy();
  }

  @OnClick(R.id.b_show_forecast)
  protected void onDone() {
    showLoading();
    if (presenter.verifyData()) {
      final Intent intent = new Intent();
      intent.putExtra(EXTRA_CHOSEN_LOCATION, presenter.getData());
      setResult(RESULT_OK, intent);
      finish();
    } else {
      showError(getString(R.string.choose_location_from_list));
    }
    hideLoading();
  }

  @OnTextChanged(value = R.id.act_location, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
  protected void onLocationChanged(final CharSequence editable) {
    if (StringUtils.isNotEmpty(editable)) {
      if (presenter.verifyData()) {
        presenter.setData(null);
      }
      tilLocation.setError(null);
      tilLocation.setErrorEnabled(false);
    }
  }

  private void itemWasClicked(final AdapterView<?> parent, final View view, final int position, final long id) {
    presenter.setData((AutoCompleteResult) parent.getItemAtPosition(position));
    tilLocation.setError(null);
    tilLocation.setErrorEnabled(false);
  }

  private static int dpToPx(final float dp) {
    final DisplayMetrics displayMetrics = App.getApp().getResources().getDisplayMetrics();
    return Math.round(dp * (displayMetrics.xdpi / (float) DisplayMetrics.DENSITY_DEFAULT));
  }
}
