package kz.ain.zapis.networking.models

data class DataContent(
    val recommendedFirms: List<Firm>,
    val popularFirms: List<Firm>,
    val recentlyAddedFirms: List<Firm>
)

data class ApiResponse(
    val data: DataContent
)