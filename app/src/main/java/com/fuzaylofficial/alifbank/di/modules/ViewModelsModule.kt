package com.fuzaylofficial.alifbank.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fuzaylofficial.alifbank.di.ViewModelFactory
import com.fuzaylofficial.alifbank.di.keys.ViewModelKey
import com.fuzaylofficial.alifbank.ui.main.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsModule {
    @Binds
    abstract fun bindViewModelFactory(modelProvider: ViewModelFactory?): ViewModelProvider.Factory?

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    protected abstract fun mainViewModel(mainViewModel: MainViewModel?): ViewModel?

}
