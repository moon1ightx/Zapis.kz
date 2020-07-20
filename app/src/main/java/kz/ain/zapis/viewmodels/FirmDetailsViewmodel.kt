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
import kz.ain.zapis.networking.models.FirmDetailsContent
import kz.ain.zapis.repositories.FirmDetailsRepository
import java.net.ConnectException
import kotlin.coroutines.CoroutineContext

class FirmDetailsViewmodel(
    private val firmDetailsRepository: FirmDetailsRepository
): ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job


    private val firmDetailsMutableLiveData = MutableLiveData<FirmDetailsContent>()
    val firmDetailsLiveData: LiveData<FirmDetailsContent> = firmDetailsMutableLiveData

    fun loadFirmDetails(id: Int) {
        launch {
            try {
                val response = firmDetailsRepository.loadFirmDetails(id)
                if (response != null) {
                    firmDetailsMutableLiveData.value = response.data
                }
            } catch (e: ConnectException) {
                Log.d("ntwrk", "No Internet Connection")
            }
        }
    }
}

class FirmDetailsFactory(
    private val firmDetailsRepository: FirmDetailsRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FirmDetailsViewmodel(firmDetailsRepository) as T
    }
}