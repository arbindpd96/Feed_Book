package com.example.feedbook.ui.viewmodel.vm

import android.util.Log
import com.example.feedbook.utils.Resource
import androidx.lifecycle.*
import com.example.feedbook.data.api.repository.MainRepository
import com.example.feedbook.utils.NetworkHelper
import com.example.myfeed.data.Feed
import com.example.myfeed.data.Posts
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _users = MutableLiveData<Resource<List<Posts>>>()
    val feeds1: LiveData<Resource<List<Posts>>>
        get() = _users

    private val _users2 = MutableLiveData<Resource<List<Posts>>>()
    val feeds2: LiveData<Resource<List<Posts>>>
        get() = _users2

    private val _users3 = MutableLiveData<Resource<List<Posts>>>()
    val feeds3: LiveData<Resource<List<Posts>>>
        get() = _users3

    init {
        fetchFeed1()
        fetchUsers2()
        fetchUsers3()
    }

    private fun fetchFeed1() {
        viewModelScope.launch {
            _users.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                mainRepository.getFeed1().let {
                    if (it.isSuccessful) {
                        _users.postValue(Resource.success(it.body()?.posts))

                        Log.i("viewModel","body :- " + it.body()?.posts)
                    } else _users.postValue(Resource.error(it.errorBody().toString(), null))
                }
            } else _users.postValue(Resource.error("No internet connection", null))
        }
    }

    private fun fetchUsers2() {
        viewModelScope.launch {
            _users2.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                mainRepository.getFeed2().let {
                    if (it.isSuccessful) {
                        _users2.postValue(Resource.success(it.body()?.posts))

                        Log.i("viewModel","body :- " + it.body()?.posts)
                    } else _users2.postValue(Resource.error(it.errorBody().toString(), null))
                }
            } else _users2.postValue(Resource.error("No internet connection", null))
        }
    }

    private fun fetchUsers3() {
        viewModelScope.launch {
            _users3.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                mainRepository.getFeed2().let {
                    if (it.isSuccessful) {
                        _users3.postValue(Resource.success(it.body()?.posts))

                        Log.i("viewModel","body :- " + it.body()?.posts)
                    } else _users3.postValue(Resource.error(it.errorBody().toString(), null))
                }
            } else _users3.postValue(Resource.error("No internet connection", null))
        }
    }


}