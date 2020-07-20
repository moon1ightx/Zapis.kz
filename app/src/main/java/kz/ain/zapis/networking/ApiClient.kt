package kz.ain.zapis.networking

import kz.ain.zapis.networking.models.ApiResponse
import kz.ain.zapis.networking.models.FirmDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiClient {

    @GET("screen/home")
    suspend fun getFirms() : ApiResponse

    @GET("firms/{id}")
    suspend fun getFirm(
        @Path("id") id: Int
    ) : FirmDetailsResponse

}