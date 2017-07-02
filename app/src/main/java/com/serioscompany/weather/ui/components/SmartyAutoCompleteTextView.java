package com.serioscompany.weather.ui.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 02.07.2017 23:43.
 */
public class SmartyAutoCompleteTextView extends android.support.v7.widget.AppCompatAutoCompleteTextView {

  public SmartyAutoCompleteTextView(final Context context) {
    super(context);
    setEmptyListener();
  }

  public SmartyAutoCompleteTextView(final Context context, final AttributeSet attrs) {
    super(context, attrs);
    setEmptyListener();
  }

  public SmartyAutoCompleteTextView(final Context context, final AttributeSet attrs, final int defStyle) {
    super(context, attrs, defStyle);
    setEmptyListener();
  }

  @Override
  public boolean dispatchTouchEvent(final MotionEvent event) {
    final boolean flag = isPopupShowing();
    final boolean result = super.dispatchTouchEvent(event);
    if (!flag && !this.getText().toString().isEmpty() && this.isFocusable() && this.isFocusableInTouchMode()) {
      this.showDropDown();
    }
    return result;
  }

  @Override
  public void setOnItemClickListener(final AdapterView.OnItemClickListener l) {
    super.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(final AdapterView<?> adapterView, final View view, final int i, final long j) {
        if (l != null) {
          l.onItemClick(adapterView, view, i, j);
        }
        final Context context = getContext();
        final InputMethodManager mgr = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        final View focusView = SmartyAutoCompleteTextView.this.focusSearch(View.FOCUS_DOWN);
        if (focusView != null) {
          focusView.requestFocus();
        } else {
          mgr.hideSoftInputFromWindow(SmartyAutoCompleteTextView.this.getWindowToken(),
                                      InputMethodManager.HIDE_NOT_ALWAYS);
        }
      }
    });
  }

  private void setEmptyListener() {
    setOnItemClickListener(null);
  }
}
