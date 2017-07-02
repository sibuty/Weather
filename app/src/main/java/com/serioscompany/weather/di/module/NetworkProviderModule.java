package com.serioscompany.weather.di.module;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.serioscompany.weather.App;
import com.serioscompany.weather.data.provider.NetworkProvider;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.PropertyNamingStrategy.SNAKE_CASE;
import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 27.06.2017 1:46.
 */
@Module
public class NetworkProviderModule {

  private static final long TIMEOUT = 35L;
  private final String baseUrl;

  public NetworkProviderModule(final String baseUrl) {
    this.baseUrl = baseUrl;
  }

  @Provides
  @Singleton
  Cache provideHttpCache(final App app) {
    final long cacheSize = 10L * 1024L * 1024L;
    return new Cache(app.getCacheDir(), cacheSize);
  }

  @Named("logging")
  @Provides
  @Singleton
  Interceptor provideHttpLoggingInterceptor() {
    return new HttpLoggingInterceptor().setLevel(BODY);
  }

  @Provides
  @Singleton
  ObjectMapper provideJacksonMapper() {
    return new ObjectMapper().disable(FAIL_ON_UNKNOWN_PROPERTIES).setPropertyNamingStrategy(SNAKE_CASE);
  }

  @Provides
  @Singleton
  OkHttpClient provideOkhttpClient(final Cache cache, @Named("logging") final Interceptor loggingInterceptor) {
    return new OkHttpClient.Builder()
        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor(loggingInterceptor)
        .cache(cache)
        .build();
  }

  @Provides
  @Singleton
  NetworkProvider provideRetrofit(final ObjectMapper mapper, final OkHttpClient okHttpClient) {
    return new Retrofit.Builder()
        .baseUrl(baseUrl)
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .addConverterFactory(JacksonConverterFactory.create(mapper))
        .client(okHttpClient)
        .build()
        .create(NetworkProvider.class);
  }
}
