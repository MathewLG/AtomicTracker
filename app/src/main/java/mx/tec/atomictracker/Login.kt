package mx.tec.atomictracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Login : AppCompatActivity() {

    lateinit var email : EditText
    lateinit var password : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        email = findViewById(R.id.login_mail)
        password = findViewById(R.id.login_password)
    }

    fun login(view : View?){
        if(email.text.toString()== ""){
            Toast.makeText(this, "Por favor, ingresa un correo válido", Toast.LENGTH_SHORT).show()
            return
        }
        if(password.text.toString() == ""){
            Toast.makeText(this, "Por favor, ingresa una contraseña", Toast.LENGTH_SHORT).show()
            return
        }
        Firebase.auth.signInWithEmailAndPassword(
            email.text.toString(),
            password.text.toString()).addOnCompleteListener(this){
                if(it.isSuccessful){
                    Log.d("FIREBASE", "Registro exitoso")
                    homeActivity()


                }else{
                    Log.d("FIREBASE", "Registro fallido: ${it.exception?.message}")
                    Toast.makeText(this, "User not found, try again or sign up", Toast.LENGTH_SHORT).show()
                }
        }
    }



    public fun homeActivity(){
        val intent = Intent(this, Home::class.java)
        intent.putExtra("mail", email.text.toString())
        startActivity(intent)
    }

    public fun signupActivity(view : View?){
        val intent = Intent(this, Registro::class.java)
        startActivity(intent)
    }
}