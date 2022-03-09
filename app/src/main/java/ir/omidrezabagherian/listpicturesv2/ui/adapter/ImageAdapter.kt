package ir.omidrezabagherian.listpicturesv2.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ir.omidrezabagherian.listpicturesv2.R
import ir.omidrezabagherian.listpicturesv2.databinding.CardImageBinding
import ir.omidrezabagherian.testapplicationfive.ModelHome.Photo

class ImageAdapter(private val context: Context) : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {
    private var dataList = mutableListOf<Photo>()

    fun setDataList(dataList: List<Photo>) {
        this.dataList.addAll(dataList)
        notifyDataSetChanged()
    }

    fun addDataList(dataList: List<Photo>) {
        this.dataList.addAll(dataList)
        notifyDataSetChanged()
    }

    class ViewHolder(private val cardImageBinding: CardImageBinding) :
        RecyclerView.ViewHolder(cardImageBinding.root) {
            fun bind(get:Photo,context: Context){
                Glide.with(context)
                    .load(get.url_s)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(cardImageBinding.imageCard)
                cardImageBinding.titleCard.text = get.title
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cardImageBinding =
            CardImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(cardImageBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position],context)
    }

    override fun getItemCount() = dataList.size

}