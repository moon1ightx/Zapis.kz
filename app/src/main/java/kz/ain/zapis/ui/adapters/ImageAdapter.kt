package kz.ain.zapis.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.image_cell.view.*
import kz.ain.zapis.Constants
import kz.ain.zapis.R

class ImageAdapter(
    private val images: List<String>
): RecyclerView.Adapter<ImageAdapter.ViewHolder>(){

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(images[position])
    override fun getItemCount(): Int = images.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.image_cell, parent, false)
        )

    inner class ViewHolder(
        private val view: View
    ): RecyclerView.ViewHolder(view){
        fun bind(image: String){
            Picasso.get().load(Constants.Base_url+image).into(view.pictures)

        }
    }

}