package wanna.cu.covid19_

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.FragmentManager
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_sido.*
import wanna.cu.covid19_.data.DataContract
import wanna.cu.covid19_.data.DataPresenter
import wanna.cu.covid19_.mainFragments.SexFragment
import wanna.cu.covid19_.mainFragments.SidoFragment
import wanna.cu.covid19_.subActivity.GoodFoodActivity
import wanna.cu.covid19_.subActivity.PreventionOfInfectActivity
import wanna.cu.covid19_.subActivity.WhatIsCorvidActivity

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setSupportActionBar(toolbar)
        val toggle: ActionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            R.string.nav_close,
            R.string.nav_open
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, SidoFragment.newInstance()).commit()


    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_sido -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, SidoFragment.newInstance()).commit()
                toolbar.title = "시 도별 발생 현황"
            }
            R.id.nav_gender -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, SexFragment.newInstance()).commit()
                toolbar.title = "연령 및 성별 발생 현황"
            }
            R.id.nav_map -> Toast.makeText(this, "nav_map selected", Toast.LENGTH_LONG).show()
            R.id.nav_whatIsCorvid -> startActivity(Intent(this, WhatIsCorvidActivity::class.java))
            R.id.nav_goodFood -> startActivity(Intent(this, GoodFoodActivity::class.java))
            R.id.nav_preventionOfInfect -> startActivity(
                Intent(
                    this,
                    PreventionOfInfectActivity::class.java
                )
            )
            else -> return false
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }


}