package wanna.cu.covid19_.subActivity.whatiscovid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_what_is_corvid.*
import wanna.cu.covid19_.R

class WhatIsCovidActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_what_is_corvid)

        setSupportActionBar(toolbar_whatIsCovid)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar_whatIsCovid.title = "코로나란?"
        webView_whatIsCovid.loadUrl("http://ncov.mohw.go.kr/baroView.do")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> finish()
        }

        return super.onOptionsItemSelected(item)
    }
}