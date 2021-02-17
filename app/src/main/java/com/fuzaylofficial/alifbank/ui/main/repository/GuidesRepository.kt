package com.fuzaylofficial.alifbank.ui.main.repository

import androidx.lifecycle.LiveData
import androidx.paging.*
import androidx.paging.rxjava2.observable
import com.fuzaylofficial.alifbank.models.Guides
import com.fuzaylofficial.alifbank.ui.main.repository.local.GuidesDao
import com.fuzaylofficial.alifbank.ui.main.repository.remote.GuidesPagingSource
import com.fuzaylofficial.alifbank.ui.main.repository.remote.GuidesService
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow

class GuidesRepository constructor(private val service: GuidesService, private val dao: GuidesDao) {

    companion  object {
        const val DEFAULT_PAGE_SIZE = 3
    }

    fun letGuidesFlow(pagingConfig: PagingConfig = getDefaultPageConfig()): Flow<PagingData<Guides.Guide>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                GuidesPagingSource(
                    service,
                    dao
                )
            }
        ).flow
    }

    fun letGuidesObservable(pagingConfig: PagingConfig = getDefaultPageConfig()): Observable<PagingData<Guides.Guide>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                GuidesPagingSource(
                    service,
                    dao
                )
            }
        ).observable
    }

    fun letGuidesLiveData(pagingConfig: PagingConfig = getDefaultPageConfig()): LiveData<PagingData<Guides.Guide>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                GuidesPagingSource(
                    service,
                    dao
                )
            }
        ).liveData
    }

    private fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = true)
    }
}