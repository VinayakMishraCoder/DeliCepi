package com.example.delicepifinal

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation

class DefaultPage : Fragment() {

    lateinit var navcontroller: NavController
    lateinit var getStarted: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar!!.title = "DeliCepi"
//        getActivity()?.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (activity as AppCompatActivity).supportActionBar!!.show()
        (requireActivity() as MainActivity).supportActionBar!!.title = "DeliCepi"
        getActivity()?.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        return inflater.inflate(R.layout.fragment_default_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navcontroller = Navigation.findNavController(view)
        getStarted = view.findViewById(R.id.get_started)
        getStarted.setOnClickListener {
            navcontroller.navigate(R.id.action_defaultPage_to_searchPage)
        }
    }


}