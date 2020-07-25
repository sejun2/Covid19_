package wanna.cu.covid19_.subActivity.qurantineSystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_quarantine_system.*
import wanna.cu.covid19_.R

class quarantineSystemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quarantine_system)

        setSupportActionBar(toolbar_quarantineSystem)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
toolbar_quarantineSystem.title = "대한민국 방역체계"
        webView_quarantineSystem.loadUrl("http://ncov.mohw.go.kr/baroView2.do?brdId=4&brdGubun=42")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> finish()
        }

        return super.onOptionsItemSelected(item)
    }
}