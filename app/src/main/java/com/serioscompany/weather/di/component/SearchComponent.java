package com.serioscompany.weather.di.component;

import com.serioscompany.weather.di.module.SearchPresenterModule;
import com.serioscompany.weather.di.scope.Search;
import com.serioscompany.weather.mvp.search.SearchPresenter;
import com.serioscompany.weather.util.ProgressDialogHelper;

import dagger.Component;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 02.07.2017 21:17.
 */
@Search
@Component(dependencies = AppComponent.class, modules = SearchPresenterModule.class)
public interface SearchComponent {

  SearchPresenter searchPresenter();

  ProgressDialogHelper progressDialogHelper();
}
