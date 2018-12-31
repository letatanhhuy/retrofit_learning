package sample.huy.huy_retrofit_practice.activity.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.post_create_fragment.*
import sample.huy.huy_retrofit_practice.Network.PostNetworkService
import sample.huy.huy_retrofit_practice.R
import sample.huy.huy_retrofit_practice.UI.Fragments.CreatePostFragment
import sample.huy.huy_retrofit_practice.UI.Fragments.PostListFragment
import sample.huy.huy_retrofit_practice.activity.model.Post

class MainActivity : AppCompatActivity() {

    private var isEditMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction().run {
                replace(R.id.mainViewFrame, PostListFragment()).commit() }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if(!isEditMode) {
            menuInflater.inflate(R.menu.main, menu)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuCreateNew -> {
                supportFragmentManager.beginTransaction().run {
                    if(!isEditMode) {
                        addToBackStack(null)
                        replace(R.id.mainViewFrame, CreatePostFragment()).commit()
                        item.isVisible = false
                        isEditMode = true;
                    }
                }
                invalidateOptionsMenu()
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
        return true
    }


    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
            isEditMode = false
            invalidateOptionsMenu()
        } else {
            super.onBackPressed()
        }
    }

    override fun onStart() {
        super.onStart()
//
//        //test dagger
//        //var carComponent
//        var carComponent = DaggerCarComponent.create()
//        var car = carComponent.getCar()
//        car.drive()


    }
}
