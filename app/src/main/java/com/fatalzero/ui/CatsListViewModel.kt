package com.fatalzero.ui
import androidx.lifecycle.*

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.fatalzero.api.ApiDateImpl
import com.fatalzero.data.CatImagesRepository
import com.fatalzero.model.Cat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CatsListViewModel : ViewModel() {
     var repository= CatImagesRepository()

     var catsLiveData=
          repository.letCatImagesLiveData().cachedIn(viewModelScope)

fun refresh(){
    repository= CatImagesRepository()
    catsLiveData=
        repository.letCatImagesLiveData().cachedIn(viewModelScope)
}

}