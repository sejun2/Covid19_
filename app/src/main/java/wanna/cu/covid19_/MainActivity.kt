package wanna.cu.covid19_

import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Base64.NO_WRAP
import android.util.Log
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.kakao.util.maps.helper.Utility.getPackageInfo
import kotlinx.android.synthetic.main.activity_main.*
import net.daum.mf.map.api.MapView
import wanna.cu.covid19_.mainFragments.MapFragment
import wanna.cu.covid19_.mainFragments.SexAgeFragment
import wanna.cu.covid19_.mainFragments.SidoFragment
import wanna.cu.covid19_.subActivity.qurantineSystem.quarantineSystemActivity
import wanna.cu.covid19_.subActivity.preventionOfInfect.PreventionOfInfectActivity
import wanna.cu.covid19_.subActivity.whatiscovid.WhatIsCovidActivity
import java.lang.Exception
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    val sidoFragment by lazy { SidoFragment.newInstance() }
    val sexAgeFragment by lazy { SexAgeFragment.newInstance() }
    val mapFragment by lazy{MapFragment.newInstance()}

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
            .replace(R.id.fragment_container, sidoFragment).commit()



    }
    fun getHashKey(context: Context): String? {
        try {
            if (Build.VERSION.SDK_INT >= 28) {
                val packageInfo = getPackageInfo(context, PackageManager.GET_SIGNING_CERTIFICATES)
                val signatures = packageInfo.signingInfo.apkContentsSigners
                val md = MessageDigest.getInstance("SHA")
                for (signature in signatures) {
                    md.update(signature.toByteArray())
                    Log.d("keyHash:", String(Base64.encode(md.digest(), NO_WRAP)))
                    return String(Base64.encode(md.digest(), NO_WRAP))
                }
            } else {
                val packageInfo =
                    getPackageInfo(context, PackageManager.GET_SIGNATURES) ?: return null

                for (signature in packageInfo!!.signatures) {
                    try {
                        val md = MessageDigest.getInstance("SHA")
                        md.update(signature.toByteArray())
                        Log.d("keyHash:", Base64.encodeToString(md.digest(), Base64.NO_WRAP))
                        return Base64.encodeToString(md.digest(), Base64.NO_WRAP)
                    } catch (e: NoSuchAlgorithmException) {
                        // ERROR LOG
                    }
                }
            }
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }

        return null
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_sido -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, sidoFragment).commit()
                toolbar.title = "시 도별 발생 현황"
            }
            R.id.nav_gender -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, sexAgeFragment).commit()
                toolbar.title = "연령 및 성별 발생 현황"
            }
            R.id.nav_map -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, mapFragment).commit()
                toolbar.title = "지도로 보기"
            }
            R.id.nav_whatIsCorvid -> startActivity(Intent(this, WhatIsCovidActivity::class.java))
            R.id.nav_quarantineSystem -> startActivity(
                Intent(
                    this,
                    quarantineSystemActivity::class.java
                )
            )
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