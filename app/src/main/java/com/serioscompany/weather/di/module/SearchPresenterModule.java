package com.serioscompany.weather.di.module;

import com.serioscompany.weather.data.provider.NetworkProvider;
import com.serioscompany.weather.di.scope.Search;
import com.serioscompany.weather.mvp.search.SearchPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 02.07.2017 21:06.
 */
@Module
public class SearchPresenterModule {

  @Provides
  @Search
  SearchPresenter provideSearchPresenter(final NetworkProvider networkProvider) {
    return new SearchPresenter(networkProvider);
  }
}
