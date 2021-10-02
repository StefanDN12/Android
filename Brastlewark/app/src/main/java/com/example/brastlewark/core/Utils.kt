package com.example.brastlewark.core

import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import java.net.URL

class Utils {
    fun generateGlideUrl(url : String): GlideUrl {
        val USER_AGENT = "Mozilla/5.0 (Linux; Android 11) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.181 Mobile Safari/537.36"
        val glideUrl = GlideUrl(
            URL(url),
            LazyHeaders.Builder().addHeader("User-Agent", USER_AGENT).build())
        return glideUrl
    }
}