package com.example.delicepifinal

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import kotlin.coroutines.coroutineContext


class WebViewFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private var curr_hit: Hit? = null
    private lateinit var webView: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar!!.title = "Browse"

        arguments?.let {
            curr_hit = it.getSerializable("hit") as Hit?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar!!.show()
        (activity as AppCompatActivity).supportActionBar!!.title = "Browse"

        return inflater.inflate(R.layout.fragment_web_view, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webView = view.findViewById(R.id.myweb)

        var mWebView : WebView? = null
        mWebView = view?.findViewById(R.id.myweb) as WebView?
        mWebView?.loadUrl(curr_hit?.recipe?.url!!)

        val webSettings = mWebView?.getSettings()
        webSettings?.setJavaScriptEnabled(true)
        webSettings?.safeBrowsingEnabled = false

        mWebView?.setWebViewClient(WebViewClient())

        mWebView?.canGoBack()
        mWebView?.setOnKeyListener( View.OnKeyListener{ v,keyCode,event ->
            if(keyCode == KeyEvent.KEYCODE_BACK && event.action == MotionEvent.ACTION_UP &&mWebView.canGoBack()){
                mWebView.goBack()
                return@OnKeyListener true
            }
            false
        } )
    }



}