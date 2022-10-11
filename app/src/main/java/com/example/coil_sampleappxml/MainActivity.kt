package com.example.coil_sampleappxml

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import coil.ImageLoader
import coil.load
import coil.request.ImageRequest
import coil.request.SuccessResult
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val iv1 = findViewById<ImageView>(R.id.iv1)
        val iv2 = findViewById<ImageView>(R.id.iv2)
        val iv3 = findViewById<ImageView>(R.id.iv3)
        val iv4 = findViewById<ImageView>(R.id.iv4)

        iv1.load("https://picsum.photos/id/237/200") {
            placeholder(R.drawable.placeholder)
            crossfade(3000)
        }

        iv2.load("https://picsum.photos/id/237/200") {
            crossfade(true)
            crossfade(1000)
            transformations(RoundedCornersTransformation(30f))
        }

        iv3.load("https://picsum.photos/id/237/200") {
            crossfade(true)
            crossfade(3000)
            transformations(CircleCropTransformation())
        }

        lifecycleScope.launch {
            iv4.setImageBitmap(getBitmap())
            Log.v("MainActivity", getBitmap().toString())
        }
    }

    // convert image url to bitmap
    private suspend fun getBitmap(): Bitmap {
        val loading: ImageLoader = ImageLoader(this)
        val request = ImageRequest.Builder(this)
            .data("https://picsum.photos/id/237/200")
            .build()
        val result = (loading.execute(request) as SuccessResult).drawable
        return (result as BitmapDrawable).bitmap
    }
}
