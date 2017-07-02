package com.serioscompany.weather.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;

import com.serioscompany.weather.R;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 02.07.2017 22:08.
 */
public class ProgressDialogHelper {

  @StyleRes private int themeId = 0;
  private ProgressDialog progressDialog;

  public ProgressDialogHelper() {
    this(R.style.ProgressDialog);
  }

  public ProgressDialogHelper(@StyleRes final int themeId) {
    this.themeId = themeId;
  }

  public void create(@NonNull final Context context, @StringRes final int message) {
    create(context, context.getString(message));
  }

  public void create(@NonNull final Context context, @StringRes final int title, @StringRes final int message) {
    create(context, context.getString(title), context.getString(message));
  }

  public void create(@NonNull final Context context, @Nullable final CharSequence message) {
    create(context, null, message, true, true, null);
  }

  public void create(@NonNull final Context context,
                     @Nullable final CharSequence title,
                     @Nullable final CharSequence message) {
    create(context, title, message, true, true, null);
  }

  public void create(@NonNull final Context context,
                     @Nullable final CharSequence title,
                     @Nullable final CharSequence message,
                     final boolean cancelable,
                     @Nullable final DialogInterface.OnCancelListener onCancelListener) {
    create(context, title, message, true, cancelable, onCancelListener);
  }

  public void create(@NonNull final Context context,
                     @Nullable final CharSequence title,
                     @Nullable final CharSequence message,
                     final boolean indeterminate,
                     final boolean cancelable,
                     @Nullable final DialogInterface.OnCancelListener onCancelListener) {
    onDestroy();
    progressDialog = new ProgressDialog(context, themeId);
    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    progressDialog.setTitle(title);
    progressDialog.setMessage(message);
    progressDialog.setIndeterminate(indeterminate);
    progressDialog.setCancelable(cancelable);
    progressDialog.setOnCancelListener(onCancelListener);
  }

  public ProgressDialog getProgressDialog() {
    return progressDialog;
  }

  public void setProgressDialog(final ProgressDialog progressDialog) {
    onDestroy();
    this.progressDialog = progressDialog;
  }

  public void hide() {
    if (progressDialog != null) {
      progressDialog.dismiss();
    }
  }

  public void onDestroy() {
    hide();
    progressDialog = null;
  }

  public void show() {
    if (progressDialog != null) {
      progressDialog.show();
    }
  }
}
