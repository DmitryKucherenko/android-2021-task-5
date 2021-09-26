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

    //var catsLiveData: LiveData<PagingData<Cat>> =  repository.letCatImagesLiveData()
     //var catsList: LiveData<List<Cat?>> = getLiveDataList()

//    private suspend fun getList():List<Cat?>{
//       return withContext(Dispatchers.IO){
//           ApiDateImpl.getCats()
//       }
//    }

//     private fun getLiveDataList(): LiveData<List<Cat?>>{
//        return liveData {
//            emit(getList())
//        }
//    }

  // repository.letCatImagesLiveData()

}