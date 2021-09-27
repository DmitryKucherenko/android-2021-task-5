package com.fatalzero.data

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.fatalzero.api.Api
import com.fatalzero.api.ApiDateImpl
import com.fatalzero.model.Cat
import kotlinx.coroutines.flow.Flow

class CatImagesRepository(private val catApiService: Api = ApiDateImpl.catService) {


    private fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = 1, enablePlaceholders = false)
    }


    fun letCatImagesFlow(pagingConfig: PagingConfig = getDefaultPageConfig()): Flow<PagingData<Cat>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { CatImagePagingSource(catApiService) }
        ).flow
    }

    fun letCatImagesLiveData(pagingConfig: PagingConfig = getDefaultPageConfig()): LiveData<PagingData<Cat>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { CatImagePagingSource(catApiService) }
        ).liveData
    }


}