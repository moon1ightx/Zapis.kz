package kz.ain.zapis.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kz.ain.zapis.networking.ApiClient
import kz.ain.zapis.networking.models.ApiResponse

class FirmsRepository (
    private val apiClient: ApiClient
){
    suspend fun loadFirms(): ApiResponse?{
        return withContext(Dispatchers.IO){
            apiClient.getFirms()
        }
    }
}