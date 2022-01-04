package com.example.delicepifinal

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso


class DetailFragment : Fragment() {

    private var curr_hit: Hit? = null
    lateinit var list : ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getActivity()?.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        arguments?.let {
            curr_hit = it.getSerializable("hit") as Hit?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (activity as AppCompatActivity).supportActionBar!!.show()
        (activity as AppCompatActivity).supportActionBar!!.title = "Detail"
        getActivity()?.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<FloatingActionButton>(R.id.floatingActionButton).setOnClickListener {
            val Bundle = Bundle().apply {
                putSerializable("hit",curr_hit)
            }
            findNavController().navigate(R.id.action_detailFragment_to_webViewFragment,Bundle)
        }
        Picasso.get().load(curr_hit?.recipe?.image).into(view.findViewById<ImageView>(R.id.image))
        view.findViewById<TextView>(R.id.label_name).text = curr_hit?.recipe?.label
        list = view.findViewById(R.id.mylist)
        val arr: List<String> = curr_hit?.recipe?.ingredientLines!!
        val adapter: ArrayAdapter<String>? =
            context?.let { ArrayAdapter(it,android.R.layout.simple_list_item_1,arr) }
        list.adapter = adapter
    }

}


