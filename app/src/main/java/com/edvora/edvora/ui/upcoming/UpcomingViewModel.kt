package com.edvora.edvora.ui.upcoming

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edvora.edvora.pojo.Ride
import com.edvora.edvora.pojo.User
import com.edvora.edvora.repository.RepositoryImplementation
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class UpcomingViewModel: ViewModel() {

    private var repository: RepositoryImplementation = RepositoryImplementation()

    private var _fetchAllData: MutableLiveData<List<Ride>> = MutableLiveData<List<Ride>>()
    val fetchAllData: LiveData<List<Ride>> get() = _fetchAllData

    private var _fetchUser: MutableLiveData<User> = MutableLiveData<User>()
    val fetchUser: LiveData<User> get() = _fetchUser

    private var _statusProgressBar: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val statusProgressBar: LiveData<Boolean> get() = _statusProgressBar


    fun getAllData(context: Context) {
        viewModelScope.launch {
            _statusProgressBar.value = true
            delay(3000)
            val result = repository.getAllData(context)
            if (result!!.isSuccessful)
                _fetchAllData.value = result.body()
            _statusProgressBar.value = false
        }
    }


    fun getUser(context: Context) {
        viewModelScope.launch {
            _statusProgressBar.value = true
            delay(3000)
            val result = repository.getUser(context)
            if (result!!.isSuccessful)
                _fetchUser.value = result.body()
            _statusProgressBar.value = false
        }
    }

}