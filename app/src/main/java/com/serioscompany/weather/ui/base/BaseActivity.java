package com.serioscompany.weather.ui.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.serioscompany.weather.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import icepick.Icepick;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 02.07.2017 20:50.
 */
public abstract class BaseActivity extends AppCompatActivity {

  @BindView(R.id.toolbar) protected Toolbar toolbar;

  @LayoutRes
  protected abstract int getLayout();

  protected Toolbar getToolbar() {
    return toolbar;
  }

  protected void initToolbar() {
    toolbar.setPadding(0, getStatusBarHeight(), 0, 0);
    setSupportActionBar(toolbar);
  }

  @Override
  protected void onCreate(@Nullable final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getLayout());
    ButterKnife.bind(this);
    initToolbar();
  }

  @Override
  protected void onSaveInstanceState(final Bundle outState) {
    super.onSaveInstanceState(outState);
    Icepick.saveInstanceState(this, outState);
  }

  @Override
  protected void onRestoreInstanceState(final Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    Icepick.restoreInstanceState(this, savedInstanceState);
  }

  @Override
  public boolean onOptionsItemSelected(final MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      onBackPressed();
    }
    return super.onOptionsItemSelected(item);
  }

  protected void setDisplayHome(final boolean enable) {
    final ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(enable);
      actionBar.setDisplayShowHomeEnabled(enable);
    }
  }

  private int getStatusBarHeight() {
    int result = 0;
    final int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
    if (resourceId > 0) {
      result = getResources().getDimensionPixelSize(resourceId);
    }
    return result;
  }
}
