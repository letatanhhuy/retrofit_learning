package sample.huy.huy_retrofit_practice.activity.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.post_create_fragment.*
import sample.huy.huy_retrofit_practice.R
import sample.huy.huy_retrofit_practice.UI.Fragments.CreatePostFragment
import sample.huy.huy_retrofit_practice.UI.Fragments.PostListFragment

class MainActivity : AppCompatActivity() {
    private lateinit var menuBar: Menu
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction().run {
                replace(R.id.mainViewFrame, PostListFragment()).commit() }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        menuBar = menu as Menu
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuCreateNew -> {
                supportFragmentManager.beginTransaction().run {
                    addToBackStack(null)
                    replace(R.id.mainViewFrame, CreatePostFragment()).commit()
                }
            }
            R.id.menuCreateOk -> {
                btnCreate.performClick()
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
