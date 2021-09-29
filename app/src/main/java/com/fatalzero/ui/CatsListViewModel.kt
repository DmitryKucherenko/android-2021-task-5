package com.fatalzero.ui
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.fatalzero.data.CatImagesRepository

class CatsListViewModel : ViewModel() {
     var repository= CatImagesRepository()

     var catsLiveData=
          repository.letCatImagesLiveData().cachedIn(viewModelScope)



}