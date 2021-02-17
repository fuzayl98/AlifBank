package com.fuzaylofficial.alifbank.di.modules

import com.fuzaylofficial.alifbank.ui.main.main.MainFragment
import dagger.android.ContributesAndroidInjector
import dagger.Module

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector(modules = [ViewModelsModule::class])
    abstract fun contributeHomeFragment(): MainFragment?

}