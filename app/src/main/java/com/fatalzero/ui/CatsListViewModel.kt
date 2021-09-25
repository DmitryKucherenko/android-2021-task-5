package com.fatalzero.ui
import androidx.lifecycle.*

import androidx.lifecycle.ViewModel
import com.fatalzero.api.ApiDateImpl
import com.fatalzero.model.Cat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CatsListViewModel : ViewModel() {

     var catsList: LiveData<List<Cat?>> = getLiveDataList()

    private suspend fun getList():List<Cat?>{
       return withContext(Dispatchers.IO){
           ApiDateImpl.getCats()
       }
    }

     private fun getLiveDataList(): LiveData<List<Cat?>>{
        return liveData {
            emit(getList())
        }
    }

}