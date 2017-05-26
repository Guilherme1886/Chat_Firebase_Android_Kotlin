package br.com.examples.kotlinappandroid

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_cadastro.*

class CadastroActivity : AppCompatActivity() {

    var mAuth: FirebaseAuth? = null
    var progress: ProgressDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        mAuth = FirebaseAuth.getInstance()


    }

    fun cadastraUser(view: View) {

        if (!email_cadastrar.text.isEmpty() || (!senha_cadastrar.text.isEmpty())) {

            progress = ProgressDialog(this)
            progress?.setMessage("Kotlin")
            progress?.setCancelable(true)
            progress?.show()

            val email = email_cadastrar.text.toString()
            val senha = senha_cadastrar.text.toString()

            mAuth?.createUserWithEmailAndPassword(email, senha)?.addOnCompleteListener(this, { task ->

                if (task.isSuccessful) {

                    Toast.makeText(this@CadastroActivity, "Cadastrado com sucesso", Toast.LENGTH_LONG).show()
                    progress?.dismiss()

                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this@CadastroActivity, "Houve um erro ao cadastrar", Toast.LENGTH_LONG).show()
                    progress?.dismiss()

                }
            })


        }


    }


}
