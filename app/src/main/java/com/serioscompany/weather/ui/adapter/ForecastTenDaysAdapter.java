package com.serioscompany.weather.ui.adapter;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.serioscompany.weather.R;
import com.serioscompany.weather.data.response.json.ForecastDay;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 03.07.2017 0:52.
 */
public class ForecastTenDaysAdapter extends RecyclerView.Adapter<ForecastTenDaysAdapter.ForecastViewHolder> {

  private final List<ForecastDay> forecastDayList = new ArrayList<>();

  public ForecastTenDaysAdapter() {
  }

  public ForecastTenDaysAdapter(final Collection<ForecastDay> list) {
    this.forecastDayList.addAll(list);
  }

  @Override
  public ForecastViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
    return new ForecastViewHolder(LayoutInflater
                                      .from(parent.getContext())
                                      .inflate(R.layout.forecast_item, parent, false));
  }

  @Override
  public void onBindViewHolder(final ForecastViewHolder holder, final int position) {
    holder.fill(this.forecastDayList.get(position));
  }

  @Override
  public int getItemCount() {
    return this.forecastDayList.size();
  }

  public void setForecastDayList(final Collection<ForecastDay> list) {
    this.forecastDayList.clear();
    this.forecastDayList.addAll(list);
    notifyDataSetChanged();
  }

  static class ForecastViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_day) TextView tvDay;
    @BindView(R.id.tv_data) TextView tvData;
    @BindView(R.id.tv_degree_high) TextView tvHigh;
    @BindView(R.id.tv_degree_low) TextView tvLow;
    @BindView(R.id.iv_status_icon_day) AppCompatImageView ivDay;
    @BindView(R.id.iv_status_icon_night) AppCompatImageView ivNight;
    @BindView(R.id.tv_day_name) TextView tvDayName;
    @BindView(R.id.tv_day_weather_text) TextView tvDayWeatherText;
    @BindView(R.id.tv_night_name) TextView tvNightName;
    @BindView(R.id.tv_night_weather_text) TextView tvNightWeatherText;

    ForecastViewHolder(final View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

    void fill(final ForecastDay forecast) {
      tvDay.setText(forecast.getDateInfo().getWeekDay());
      final String month = getNumberWithZero(forecast.getDateInfo().getMonth());
      final String day = getNumberWithZero(forecast.getDateInfo().getDay());
      tvData.setText(month + '/' + day);
      tvHigh.setText(String.valueOf(forecast.getHigh()));
      tvLow.setText(String.valueOf(forecast.getLow()));
      Glide.with(itemView).load(forecast.getDay().getIconUrl()).into(ivDay);
      Glide.with(itemView).load(forecast.getNight().getIconUrl()).into(ivNight);
      tvDayName.setText(forecast.getDay().getTitle());
      tvDayWeatherText.setText(forecast.getDay().getFctTextMetric());
      tvNightName.setText(forecast.getNight().getTitle());
      tvNightWeatherText.setText(forecast.getNight().getFctTextMetric());
    }

    private static String getNumberWithZero(final int number) {
      return String.valueOf(number < 10 ? String.format(Locale.getDefault(), "0%1$d", number) : number);
    }
  }
}
