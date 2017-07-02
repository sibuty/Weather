package com.serioscompany.weather.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.serioscompany.weather.R;
import com.serioscompany.weather.data.response.json.AutoCompleteResult;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 02.07.17 22:20.
 */
public class SearchWeatherAdapter<T extends AutoCompleteResult> extends ArrayAdapter<T> {

  @Nullable private final PerformLoadDataListener<T> loadDataListener;
  @Nullable private final ProgressLoadListener progressLoadListener;

  public SearchWeatherAdapter(@NonNull final Context context) {
    this(context, null);
  }

  public SearchWeatherAdapter(@NonNull final Context context,
                              @Nullable final PerformLoadDataListener<T> loadDataListener) {
    this(context, loadDataListener, null);
  }

  public SearchWeatherAdapter(@NonNull final Context context,
                              @Nullable final PerformLoadDataListener<T> loadDataListerner,
                              @Nullable final ProgressLoadListener progressLoadListener) {
    super(context, -1);
    this.loadDataListener = loadDataListerner;
    this.progressLoadListener = progressLoadListener;
  }

  @NonNull
  @Override
  public View getView(final int position, @Nullable View convertView, @NonNull final ViewGroup parent) {
    final SearchWeatherViewHolder searchWeatherViewHolder;

    if (convertView == null) {
      final LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      convertView = inflater.inflate(R.layout.simple_dropdown_item_one_line, parent, false);
      searchWeatherViewHolder = new SearchWeatherViewHolder(convertView);
      convertView.setTag(searchWeatherViewHolder);
    } else {
      searchWeatherViewHolder = (SearchWeatherViewHolder) convertView.getTag();
    }

    final AutoCompleteResult item = getItem(position);
    if (item != null) {
      searchWeatherViewHolder.textView.setText(item.getName());
    }

    return convertView;
  }

  @NonNull
  @Override
  public Filter getFilter() {
    return new SearchWeatherFilter<>(this, loadDataListener, progressLoadListener);
  }

  public interface PerformLoadDataListener<T> {

    List<T> loadData(final String mask);
  }

  public interface ProgressLoadListener {

    @UiThread
    void toggleProgress(final boolean toggle);
  }

  private static class SearchWeatherFilter<T> extends Filter {

    @NonNull private final ArrayAdapter<T> adapter;
    @Nullable private final PerformLoadDataListener<T> loadDataListener;
    @Nullable private final ProgressLoadListener progressLoadListener;

    public SearchWeatherFilter(@NonNull final ArrayAdapter<T> adapter,
                               @Nullable final PerformLoadDataListener<T> loadDataListener,
                               @Nullable final ProgressLoadListener progressLoadListener) {
      this.adapter = adapter;
      this.loadDataListener = loadDataListener;
      this.progressLoadListener = progressLoadListener;
    }

    SearchWeatherFilter(@NonNull final ArrayAdapter<T> adapter,
                        @Nullable final PerformLoadDataListener<T> loadDataListener) {
      this(adapter, loadDataListener, null);
    }

    SearchWeatherFilter(@NonNull final ArrayAdapter<T> adapter) {
      this(adapter, null);
    }

    @Override
    protected FilterResults performFiltering(final CharSequence mask) {
      final FilterResults filterResults = new FilterResults();
      if (loadDataListener != null && StringUtils.isNotBlank(mask)) {
        if (progressLoadListener != null) {
          progressLoadListener.toggleProgress(true);
        }
        final List<T> data = loadDataListener.loadData(mask.toString());
        if (data != null && !data.isEmpty()) {
          filterResults.values = data;
          filterResults.count = data.size();
        }
      }
      return filterResults;
    }

    @Override
    protected void publishResults(final CharSequence mask, final FilterResults results) {
      adapter.clear();
      if (results.count > 0) {
        adapter.addAll((List<T>) results.values);
        adapter.notifyDataSetChanged();
      } else {
        adapter.notifyDataSetInvalidated();
      }
      if (progressLoadListener != null) {
        progressLoadListener.toggleProgress(false);
      }
    }
  }

  private static class SearchWeatherViewHolder {

    public TextView textView;

    SearchWeatherViewHolder(final View textView) {
      this.textView = (TextView) textView;
    }
  }
}
