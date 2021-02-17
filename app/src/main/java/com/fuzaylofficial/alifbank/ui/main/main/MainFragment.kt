package com.fuzaylofficial.alifbank.ui.main.main

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.fuzaylofficial.alifbank.databinding.MainFragmentBinding
import com.fuzaylofficial.alifbank.di.ViewModelFactory
import com.fuzaylofficial.alifbank.ui.main.adapters.GuideAdapter
import com.fuzaylofficial.alifbank.ui.main.adapters.GuideDiffUtillCallback
import com.fuzaylofficial.alifbank.ui.main.adapters.GuideLoadStateAdapter
import com.fuzaylofficial.alifbank.ui.main.base.BaseFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainFragment : BaseFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var homeBinding: MainFragmentBinding

    var guideAdapter: GuideAdapter = GuideAdapter(GuideDiffUtillCallback())


    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        homeBinding = MainFragmentBinding.inflate(inflater,container,false)
        setUpViews()
        return homeBinding.root

    }

    private fun fetchGuides() {
        lifecycleScope.launch {
            viewModel.fetchPictures().distinctUntilChanged().collectLatest {
                guideAdapter.submitData(it)
            }
        }
    }

    //RxJava
    @SuppressLint("CheckResult")
    private fun fetchGuidesObservable() {
        viewModel.fetchPicturesObservable().subscribe {
            lifecycleScope.launch {
                guideAdapter.submitData(it)
            }
        }
    }

    //LiveData
    private fun fetchGuidesLiveData() {
        viewModel.fetchPicturesLiveData().observe(viewLifecycleOwner, Observer {
            lifecycleScope.launch {
                guideAdapter.submitData(it)
            }
        })
    }


    private fun setUpViews() {

        homeBinding.pictureRv.apply {
            layoutManager = GridLayoutManager(context, 1)
            setHasFixedSize(true)
            adapter = guideAdapter.withLoadStateFooter(footer = GuideLoadStateAdapter{guideAdapter.retry()})
        }

        lifecycleScope.launch {
            guideAdapter.loadStateFlow.collectLatest {
                homeBinding.progressLoading.isVisible = it.refresh is LoadState.Loading
                homeBinding.retry.isVisible = it.refresh is LoadState.Error
                homeBinding.errorMsg.isVisible = it.refresh is LoadState.Error
            }
        }
        homeBinding.retry.setOnClickListener { guideAdapter.refresh() }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)
        fetchGuidesObservable()
        // TODO: Use the ViewModel
    }

}