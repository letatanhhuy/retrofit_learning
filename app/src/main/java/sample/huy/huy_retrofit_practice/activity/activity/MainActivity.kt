package sample.huy.huy_retrofit_practice.activity.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import sample.huy.huy_retrofit_practice.R
import sample.huy.huy_retrofit_practice.activity.model.AlbumPhoto

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var ap = AlbumPhoto(0,"aaaa")
    }
}
