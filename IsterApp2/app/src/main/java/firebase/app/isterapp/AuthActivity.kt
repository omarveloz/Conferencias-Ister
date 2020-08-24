package firebase.app.isterapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        //Splash
        Thread.sleep(1500)
        setTheme(R.style.AppTheme)
        
        // Configuracion
        setup()
    }

    private fun setup() {

        title="Iniciar Sesion"

        btnRegistrar.setOnClickListener{
            if(etEmail.text.isNotEmpty() && etClave.text.isNotEmpty()) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(etEmail.text.toString(),
                    etClave.text.toString()).addOnCompleteListener{

                        if (it.isSuccessful) {
                            showWelcome()
                            showHome(it.result?.user?.email ?: "", ProviderType.BASIC)

                        } else {
                            showAlert()
                        }
                }
            }
        }

        btnIngresar.setOnClickListener{
            if(etEmail.text.isNotEmpty() && etClave.text.isNotEmpty()) {
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(etEmail.text.toString(),
                        etClave.text.toString()).addOnCompleteListener{

                    if (it.isSuccessful) {
                        showHome(it.result?.user?.email ?: "", ProviderType.BASIC)
                    } else {
                        showAlert()
                    }
                }
            }
        }

    }

    private fun showAlert() {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()

    }

    private fun showWelcome() {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Registro Exitoso")
        builder.setMessage("Bienvenidx a la aplicación para conferencias del ISTER  2021")
        builder.setPositiveButton("Ingresar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()

    }

    private fun showHome(email: String, provider: ProviderType) {
        val pasodehome = Intent(this,InicioActivity::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(pasodehome)
    }

}
