package edu.gustavo.livedataexample

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.*
import android.widget.Toast.*
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private var dato: DatosViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dato = ViewModelProvider(this).get(DatosViewModel::class.java)
        val boton = findViewById<ImageButton>(R.id.button_cambiarTexto)
        boton.setOnClickListener {
            dato!!.datoObservable.value = dato!!.datoObservable.value!! + 1
            actualizarVista(dato!!.toString())
        }
        actualizarVista(dato!!.toString())
        dato!!.datoObservable.observe(this, {
            findViewById<TextView>(R.id.textView_observable).text = dato!!.toString()
        })
        findViewById<CheckBox>(R.id.checkBox_disable).setOnClickListener {
            boton.setEnabled(!(it as CheckBox).isChecked)
        }
        val editor = findViewById<EditText>(R.id.editTextMensaje)
        editor.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                makeText(applicationContext, getString(R.string.cambiandoTexto), Toast.LENGTH_SHORT).show()
                dato!!.setCadena(editor.text.toString())
                actualizarVista(dato!!.toString())
            }
        }
    }

    fun actualizarVista(textoNuevo: String) {
        findViewById<TextView>(R.id.texto_manual).text = textoNuevo
    }

    override fun onRestart() {
        super.onRestart()

        val editor = findViewById<EditText>(R.id.editTextMensaje)
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Titulo de alerta")
        builder.setMessage("Fondo rojo?")
        builder.setPositiveButton("Sí") { dialog, which -> // Acción del botón
            makeText(applicationContext, "Cambiamos el fondo", Toast.LENGTH_SHORT).show()
            editor.setBackgroundColor(Color.RED)
        }
        builder.setNegativeButton("No") { dialog, which ->
            makeText(applicationContext, "No lo has querido cambiar", Toast.LENGTH_SHORT).show()
            editor.setBackgroundColor(Color.WHITE)
        }
        builder.setNeutralButton("Cancelar") { _, _ ->
            makeText(applicationContext, "Has cancelado el diálogo", Toast.LENGTH_SHORT).show()
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}