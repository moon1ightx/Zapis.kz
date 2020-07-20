package kz.ain.zapis.networking.models

data class FirmDetailsResponse(
    val data: FirmDetailsContent
)

data class FirmDetailsContent(
    val firm: Firm,
    val services: List<Service>
)

data class Service(
    val name: String,
    val duration: Int,
    val priceStr: String
)