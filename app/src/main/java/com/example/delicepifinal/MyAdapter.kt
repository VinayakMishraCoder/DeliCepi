package com.example.delicepifinal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import retrofit2.Response

class MyAdapter (val listener : RVListener) : RecyclerView.Adapter<MyAdapter.RecipeViewHolder>() {

    var allRecipes : List<Hit> = emptyList()


    inner class RecipeViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val textView: TextView  = view.findViewById(R.id.recipe_item_title)
        val img: ImageView = view.findViewById(R.id.recipe_item_bg)

        fun  bind(hit : Hit){
            this.textView.text = hit.recipe.label
            Picasso.get().load(hit.recipe.image).into(this.img)
            itemView.setOnClickListener{
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

    fun updateList(arr :  Response<Hits>){
        if(arr?.body()?.hits?.size == 0) return
        allRecipes = arr?.body()?.hits!!
        notifyDataSetChanged()
    }
}

interface RVListener {
    fun onClicked(hit: Hit)
}