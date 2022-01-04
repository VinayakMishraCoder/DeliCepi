package com.example.delicepifinal

import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class SearchPage : Fragment() ,RVListener{

    /*
    * todo : about, lazy animations, filter by lunch/dinner on about page, layout animations, broadcast recievers add^n
    *
    * */

    lateinit var editText: EditText
    lateinit var search_but:ImageButton
    lateinit var recyclerView: RecyclerView
    lateinit var temp_image: ImageView
    lateinit var temp_text: TextView
    private var str: String? = null
    lateinit var adapter : MyAdapter
    private var fin: String? = null
    lateinit var my_view_model : RecipeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO : SET ORIENTATION AS PORTRAIT
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false);
        (activity as AppCompatActivity).supportActionBar!!.hide()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // TODO : HIDE BAR
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        (activity as AppCompatActivity).supportActionBar!!.hide()
        return inflater.inflate(R.layout.fragment_search_page, container, false)
    }


    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        my_view_model = ViewModelProvider(this).get(RecipeViewModel::class.java)
        search_but = view.findViewById(R.id.search_button)
        editText = view.findViewById(R.id.edit_item)
        recyclerView = view.findViewById(R.id.recycler_view)
        temp_image = view.findViewById(R.id.plates)
        temp_text = view.findViewById(R.id.plates_text)
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())

        adapter = MyAdapter(this)

        recyclerView.adapter = adapter

        str = editText.text.toString()

        my_view_model.query = str as String

        if(str!!.length != 0) {

            Log.d("cheezstr", str as String)
            val show = Toast.makeText(context, "configuration change", Toast.LENGTH_SHORT)
            show.show()
        }

        //observer
        my_view_model.allRecipe.observe(this,{apiResult ->
            if(apiResult?.body()?.hits?.size != 0) {
                adapter.updateList(apiResult)
                temp_text.visibility = View.INVISIBLE
                temp_image.visibility = View.INVISIBLE
                recyclerView.visibility = View.VISIBLE
            }else {
//                temp_text.visibility = View.INVISIBLE
//                temp_image.visibility = View.INVISIBLE
//                recyclerView.visibility = View.VISIBLE
                val show = Toast.makeText(context, "Invalid Search", Toast.LENGTH_SHORT)
                show.show()
            }
            Log.d("cheg", apiResult?.body()?.hits?.size.toString())
        })


            // onclick listener
            search_but.setOnClickListener {

                str = editText.text.toString()

                recyclerView.visibility = View.VISIBLE
                temp_text.visibility = View.INVISIBLE
                temp_image.visibility = View.INVISIBLE
                if(str!!.length == 0) {
                    Log.d("cheezstr", "onViewCreated: ")
                    val show = Toast.makeText(context, "Please Enter Something", Toast.LENGTH_SHORT)
                    show.show()
                }

                fin= ""
                for (ch in str!!) {
                    if (ch == ' ') fin += '+'
                    else fin += ch
                }

                my_view_model.query = str as String
                my_view_model.getRecipes()
            }
        if(str != null){
            my_view_model.query = str as String
            my_view_model.getRecipes()
        }
    }

    override fun onClicked(hit: Hit) {
        if(hit != null){
            val bundle = Bundle().apply {
                putSerializable("hit",hit);
            }
            findNavController().navigate(R.id.action_searchPage_to_detailFragment,bundle)
        }
    }



}