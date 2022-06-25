package com.example.delicepifinal

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.bitmap.TransformationUtils.centerCrop
import com.bumptech.glide.request.RequestOptions
import com.squareup.picasso.Picasso
import retrofit2.Response

class MyAdapter(val listener: RVListener, val context: SearchPage) :
    RecyclerView.Adapter<MyAdapter.RecipeViewHolder>() {

    var allRecipes: List<Hit> = emptyList()


    inner class RecipeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.recipe_item_title)
        val img: ImageView = view.findViewById(R.id.recipe_item_bg)

        fun bind(hit: Hit) {
            this.textView.text = hit.recipe.label
//            Picasso.get().load(hit.recipe.image).into(this.img)
//            val circularProgressDrawable = CircularProgressDrawable()
//            circularProgressDrawable.strokeWidth = 5f
//            circularProgressDrawable.centerRadius = 30f
//            circularProgressDrawable.start()
//            Glide.with(context)
//                .load(hit.recipe.image) // the uri you got from Firebase
//                .placeholder(circularProgressDrawable)
//                .into(this.img);
            Glide.with(context).load(hit.recipe.image).apply(
                RequestOptions().placeholder(R.drawable.anim_res).error((R.drawable.error))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .priority(Priority.HIGH)
            )
            .into(this.img)
//            Glide.with(context)
//                .load(hit.recipe.image)
//                .placeholder(R.drawable.mopgif)
//                .into(this.img)

            itemView.setOnClickListener {
                listener.onClicked(hit)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recipe_item, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val currHit = allRecipes[position]
        holder.bind(currHit)
    }

    override fun getItemCount(): Int = allRecipes.size

    fun updateList(arr: Response<Hits>) {
        if (arr?.body()?.hits?.size == 0) return
        allRecipes = arr?.body()?.hits!!
        notifyDataSetChanged()
    }
}

interface RVListener {
    fun onClicked(hit: Hit)
}