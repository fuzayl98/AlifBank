package com.fuzaylofficial.alifbank.di.modules

import com.fuzaylofficial.alifbank.ui.main.repository.local.GuidesDao
import com.fuzaylofficial.alifbank.ui.main.repository.GuidesRepository
import com.fuzaylofficial.alifbank.ui.main.repository.remote.GuidesService
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun bindRepository(dao: GuidesDao, service: GuidesService):GuidesRepository{
        return GuidesRepository(service = service,dao = dao)
    }

}