package kz.ain.zapis.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kz.ain.zapis.networking.ApiClient
import kz.ain.zapis.networking.models.FirmDetailsResponse

class FirmDetailsRepository (
    private val apiClient: ApiClient
){
    suspend fun loadFirmDetails(id: Int): FirmDetailsResponse?{
        return withContext(Dispatchers.IO){
            apiClient.getFirm(id)
        }
    }
}