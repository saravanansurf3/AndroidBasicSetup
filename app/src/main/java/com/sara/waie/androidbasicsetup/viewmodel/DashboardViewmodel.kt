package com.sara.waie.androidbasicsetup.viewmodel

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sara.waie.androidbasicsetup.model.Order
import com.sara.waie.androidbasicsetup.model.responseModel.DashboardResponse
import com.sara.waie.androidbasicsetup.network.NetworkResource
import com.sara.waie.androidbasicsetup.repository.AppRepository
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

const val TAG = "DashboardViewmodel"

@HiltViewModel
class DashboardViewmodel
@Inject
constructor(
    private val appRepository: AppRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val _dashboardResponse:MutableLiveData<NetworkResource<DashboardResponse>> = MutableLiveData()
    val dashboardResponse:LiveData<NetworkResource<DashboardResponse>>
        get() =_dashboardResponse


    fun loadDashboard() {
        viewModelScope.launch {
            _dashboardResponse.value=NetworkResource.onLoading
            _dashboardResponse.value=appRepository.getDashboard()
        }
    }

     fun loadMyOrderHistory():Flow<PagingData<Order>>{
       return appRepository.getMyOrderHistory().cachedIn(viewModelScope)

    }

    companion object {


    }
}