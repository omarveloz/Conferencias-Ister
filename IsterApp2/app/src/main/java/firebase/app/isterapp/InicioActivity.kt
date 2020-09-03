package firebase.app.isterapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_inicio.*

enum class ProviderType {
   BASIC
}

class InicioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        // Configuracion
        val bundle= intent.extras
        val email = bundle?.getString("email")
        val provider = bundle?.getString("provider")
        setup(email ?: "", provider ?: "")

        //Boton Ingresar
        btnGo.setOnClickListener {
            val intento1 = Intent(this, PrincipalActivity::class.java)
            startActivity(intento1)
        }

    }

    private fun setup(email: String, provider: String) {
        title="Introducción"
        tvUsuario.text = email
        //tvClave.text = provider

        btnCerrar.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }

    }

    //

}
