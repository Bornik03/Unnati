package com.example.unnati2

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView

class ResultActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val webView = WebView(this)
        val text1 = intent.getStringExtra("text1") ?: ""
        val text2 = intent.getStringExtra("text2") ?: ""
        val text3 = intent.getStringExtra("text3") ?: ""
        val web = intent.getStringExtra("web") ?: ""
        webView.settings.javaScriptEnabled = true
        webView.setLayerType(View.LAYER_TYPE_HARDWARE, null)
         webView.settings.domStorageEnabled = true
        webView.settings.setSupportZoom(true)
        webView.settings.builtInZoomControls = true
        webView.settings.displayZoomControls=false
        webView.settings.javaScriptCanOpenWindowsAutomatically = true
        webView.settings.useWideViewPort = true
        webView.settings.loadWithOverviewMode = true
        webView.settings.allowContentAccess = true
        webView.settings.allowFileAccess = true
        setContent {
            MainContent(webView = webView,text1,text2,text3,web)
        }

    }
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainContent(webView: WebView, text1: String, text2: String, text3: String, web: String) {
    var url by remember { mutableStateOf("") } //Sensitive Content hence removed
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    val activity = LocalContext.current as? Activity
    if(web!="")
        url=web
    backDispatcher?.addCallback(object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (webView.canGoBack()) {
                webView.goBack()
            }
            else
            {
                activity?.finish()
            }
        }
    })
    MyContent(url = url, webView = webView)
    }
@Composable
fun MyContent(url: String, webView: WebView) {
    AndroidView(factory = {
        webView.apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = object :WebViewClient()
            {
            }
        }
    }, update = {
        it.loadUrl(url)
    })
}
