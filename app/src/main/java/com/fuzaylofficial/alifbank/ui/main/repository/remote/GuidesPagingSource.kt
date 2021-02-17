package com.fuzaylofficial.alifbank.ui.main.repository.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fuzaylofficial.alifbank.models.Guides
import com.fuzaylofficial.alifbank.ui.main.repository.local.GuidesDao
import com.fuzaylofficial.alifbank.ui.main.repository.remote.GuidesService
import retrofit2.HttpException
import java.io.IOException

class GuidesPagingSource(private val guidesService: GuidesService, private val dao: GuidesDao) :
    PagingSource<Int, Guides.Guide>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Guides.Guide> {
        return try {
            val response = guidesService.guides()
            dao.insertAll(response.guides)
            LoadResult.Page(
                response.guides, prevKey = null,
                nextKey = null
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Guides.Guide>): Int? {
        return state.anchorPosition
    }

}