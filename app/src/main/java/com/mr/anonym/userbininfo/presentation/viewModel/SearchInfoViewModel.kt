package com.mr.anonym.userbininfo.presentation.viewModel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mr.anonym.domain.model.BINInfoEntity
import com.mr.anonym.domain.model.BINModel
import com.mr.anonym.domain.model.SearchHistoryEntity
import com.mr.anonym.domain.useCases.local.LocalUseCases
import com.mr.anonym.domain.useCases.remote.RemoteUseCases
import com.mr.anonym.userbininfo.presentation.utils.BinState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchInfoViewModel @Inject constructor(
    private val localUseCases: LocalUseCases,
    private val remoteUseCases: RemoteUseCases
) : ViewModel() {

    private val _binInfoModel = mutableStateOf(BinState())
    val binInfos: State<BinState> = _binInfoModel

    private val _localBinInfo = mutableStateOf( BinState().localBinInfos )
    val localBinInfos:State<List<BINInfoEntity>> = _localBinInfo

    init {
        getLocalBin()
    }

    fun getByBin(bin: String,binNumber:String) = viewModelScope.launch {
        val response = remoteUseCases.getByBinUseCase.execute(bin)
        Log.d("NetworkLogging", "ViewmodelRemote: $response")
        response.let {
            if (it != null){
                insertBinToLocal(it,binNumber)
                Log.d("LocalLogging", "ViewmodelLocalObject: $it")
            }
        }
    }

    private fun insertBinToLocal(binModel: BINModel,binNumber: String) = viewModelScope.launch {
        Log.d("LocalLogging", "ViewmodelLocal:is working")
        localUseCases.insertBIN.execute(
            BINInfoEntity(
                schema = binModel.scheme,
                type = binModel.type,
                brand = binModel.brand?:"",
                countryName = binModel.country.name,
                numeric = binModel.country.numeric,
                alpha2 = binModel.country.alpha2,
                emoji = binModel.country.emoji,
                currency = binModel.country.currency,
                latitude = binModel.country.latitude,
                longitude = binModel.country.longitude,
                bankName = binModel.bank?.name?:"",
                binNumber = binNumber
            )
        )
        Log.d("LocalLogging", "ViewmodelLocal: ${_binInfoModel.value}")
    }

    fun clearLocalBins(bins:List<BINInfoEntity>) = viewModelScope.launch {
        localUseCases.clearBIN.execute(bins)
    }

    fun getLocalBin() = viewModelScope.launch {
        localUseCases.getAllBIN.execute().collect{
            _localBinInfo.value = it
        }
    }

    fun insertSearchHistory(history:SearchHistoryEntity) = viewModelScope.launch {
        localUseCases.insertSearchHistory.execute(history)
    }
}