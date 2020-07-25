package wanna.cu.covid19_.subActivity.preventionOfInfect

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_prevention_of_infect.*
import wanna.cu.covid19_.R

class PreventionOfInfectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prevention_of_infect)

        setSupportActionBar(toolbar_preventionOfInfect)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        
        toolbar_preventionOfInfect.title = "감염 예방 수칙"
        webView_preventionOfInfect.loadUrl("http://ncov.mohw.go.kr/baroView4.do?brdId=4&brdGubun=44");
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> finish()
        }

        return super.onOptionsItemSelected(item)
    }
}