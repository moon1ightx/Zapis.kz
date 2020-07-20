package kz.ain.zapis.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kz.ain.zapis.networking.models.DataContent
import kz.ain.zapis.repositories.FirmsRepository
import java.net.ConnectException
import kotlin.coroutines.CoroutineContext

class FirmsViewModel(
    private val firmsRepository: FirmsRepository
): ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job


    private val firmsMutableLiveData = MutableLiveData<DataContent>()
    val firmsLiveData: LiveData<DataContent> = firmsMutableLiveData

    fun loadFirms() {
        launch {
            try {
                val response = firmsRepository.loadFirms()
                if (response != null) {
                    firmsMutableLiveData.value = response.data
                }
            } catch (e: ConnectException) {
                Log.d("ntwrk", "No Internet Connection")
            }
        }
    }
}

class FirmsFactory(
    private val firmsRepository: FirmsRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FirmsViewModel(firmsRepository) as T
    }
}