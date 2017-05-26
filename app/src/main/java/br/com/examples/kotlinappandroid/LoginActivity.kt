package br.com.examples.kotlinappandroid

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    var mAuth: FirebaseAuth? = null
    var progress: ProgressDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

//        if (mAuth != null) {
//
//            Toast.makeText(this@LoginActivity, mAuth?.currentUser?.email, Toast.LENGTH_LONG).show()
//
//            val intent = Intent(this, ChatActivity::class.java)
//            startActivity(intent)
//
//
//        }


    }

    fun button(view: View) {

        if (verifyFields()) {

            progress = ProgressDialog(this)
            progress?.setMessage("Kotlin")
            progress?.setCancelable(true)
            progress?.show()

            val email = email.text.toString()
            val senha = senha.text.toString()

            mAuth?.signInWithEmailAndPassword(email, senha)?.addOnCompleteListener(this, { task ->

                if (task.isSuccessful) {
                    progress?.dismiss()
                    startActivity(Intent(this@LoginActivity, ChatActivity::class.java))
                    finish()

                } else {
                    Toast.makeText(this@LoginActivity, "error login", Toast.LENGTH_LONG).show()
                    progress?.dismiss()


                }
            })
        }
    }

    private fun verifyFields(): Boolean {

        val retorno = true

        if (email.text.isEmpty()) {
            email.error = "E-mail está vazio"
            return false
        }
        if (senha.text.isEmpty()) {
            senha.error = "Senha está vazio"
            return false
        }

        return retorno


    }

    fun cadastrarButton(view: View) {

        val intent = Intent(this, CadastroActivity::class.java)
        startActivity(intent)


    }


}
