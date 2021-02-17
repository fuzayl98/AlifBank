package com.fuzaylofficial.alifbank.ui.main.main

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import androidx.paging.rxjava2.cachedIn
import com.fuzaylofficial.alifbank.models.Guides
import com.fuzaylofficial.alifbank.ui.main.base.BaseViewModel
import com.fuzaylofficial.alifbank.ui.main.repository.local.GuidesDao
import com.fuzaylofficial.alifbank.ui.main.repository.GuidesRepository
import com.fuzaylofficial.alifbank.ui.main.repository.remote.GuidesService
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

class MainViewModel @Inject constructor(var guidesService: GuidesService, val context: Context, val repository: GuidesRepository, val dao: GuidesDao) : BaseViewModel() {


    //Кейс для Coroutines
    fun fetchPictures(): Flow<PagingData<Guides.Guide>> {
        return repository.letGuidesFlow()
                .map { it.map { it } }
                .cachedIn(viewModelScope)
    }

    //Кейс для RxJava
    fun fetchPicturesObservable(): Observable<PagingData<Guides.Guide>> {
        return repository.letGuidesObservable()
                .map { it.map { it } }
                .cachedIn(viewModelScope)
    }

    //Кейс для LiveData
    fun fetchPicturesLiveData(): LiveData<PagingData<Guides.Guide>> {
        return repository.letGuidesLiveData()
                .map { it.map { it } }
                .cachedIn(viewModelScope)
    }
}