package rs.raf.drugiprojekat.presentation.view.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import rs.raf.drugiprojekat.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginBtn.setOnClickListener(){
            if(pin.text.toString().equals("123")){
                val pref = applicationContext.getSharedPreferences("PREF_0", 0)
                val editor = pref.edit()
                editor.putString("login", "t")
                editor.apply()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }else{

                val toast = Toast.makeText(this, "Pogresan pin", Toast.LENGTH_SHORT)
                toast.show()
            }
        }
    }
}