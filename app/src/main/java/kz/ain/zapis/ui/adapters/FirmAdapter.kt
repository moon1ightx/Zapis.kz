package kz.ain.zapis.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.firm_cell.view.*
import kz.ain.zapis.Constants
import kz.ain.zapis.R
import kz.ain.zapis.networking.models.Firm


class FirmAdapter(
    private val firms: List<Firm>,
    private val onClick: (Firm) -> Unit
): RecyclerView.Adapter<FirmAdapter.ViewHolder>(){

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(firms[position], onClick)
    override fun getItemCount(): Int = firms.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.firm_cell, parent, false)
        )

    inner class ViewHolder(
        private val view: View
    ): RecyclerView.ViewHolder(view){
        fun bind(firm: Firm, onClick: (Firm) -> Unit){
            view.firm_name.text = firm.name
            view.firm_address.text = firm.address
            Picasso.get().load(Constants.Base_url+firm.avatarUrl).into(view.picture_view)
            view.setOnClickListener{
                onClick(firm)
            }
        }
    }

}