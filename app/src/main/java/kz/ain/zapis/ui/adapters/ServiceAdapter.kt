package kz.ain.zapis.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.service_cell.view.*
import kz.ain.zapis.R
import kz.ain.zapis.networking.models.Service


class ServiceAdapter(
    private val services: List<Service>
): RecyclerView.Adapter<ServiceAdapter.ViewHolder>(){

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(services[position])
    override fun getItemCount(): Int = services.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.service_cell, parent, false)
        )

    inner class ViewHolder(
        private val view: View
    ): RecyclerView.ViewHolder(view){
        fun bind(service: Service){
            view.service_name.text = service.name
            view.service_duration.text =  service.duration.toString() + " мин"
            view.service_price.text = service.priceStr

        }
    }

}