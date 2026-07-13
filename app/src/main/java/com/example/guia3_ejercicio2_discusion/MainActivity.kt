package com.example.guia3_ejercicio2_discusion

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var etNombres: EditText
    private lateinit var etApellidos: EditText
    private lateinit var etCorreo: EditText
    private lateinit var etTelefono: EditText
    private lateinit var etEdad: EditText
    private lateinit var tvResumen: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        etNombres = findViewById(R.id.etNombres)
        etApellidos = findViewById(R.id.etApellidos)
        etCorreo = findViewById(R.id.etCorreo)
        etTelefono = findViewById(R.id.etTelefono)
        etEdad = findViewById(R.id.etEdad)
        tvResumen = findViewById(R.id.tvResumen)

        val btnValidar = findViewById<Button>(R.id.btnValidar)
        btnValidar.setOnClickListener {
            validarInformacion()
        }

        savedInstanceState?.let { estado ->
            etNombres.setText(estado.getString(KEY_NOMBRES, ""))
            etApellidos.setText(estado.getString(KEY_APELLIDOS, ""))
            etCorreo.setText(estado.getString(KEY_CORREO, ""))
            etTelefono.setText(estado.getString(KEY_TELEFONO, ""))
            etEdad.setText(estado.getString(KEY_EDAD, ""))
            tvResumen.text = estado.getString(KEY_RESUMEN, getString(R.string.summary_empty))
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(KEY_NOMBRES, etNombres.text.toString())
        outState.putString(KEY_APELLIDOS, etApellidos.text.toString())
        outState.putString(KEY_CORREO, etCorreo.text.toString())
        outState.putString(KEY_TELEFONO, etTelefono.text.toString())
        outState.putString(KEY_EDAD, etEdad.text.toString())
        outState.putString(KEY_RESUMEN, tvResumen.text.toString())
    }

    private fun validarInformacion() {
        val nombres = etNombres.text.toString().trim()
        val apellidos = etApellidos.text.toString().trim()
        val correo = etCorreo.text.toString().trim()
        val telefono = etTelefono.text.toString().trim()
        val edadTexto = etEdad.text.toString().trim()

        if (nombres.isEmpty() || apellidos.isEmpty() || correo.isEmpty() || telefono.isEmpty() || edadTexto.isEmpty()) {
            tvResumen.text = "Debe completar todos los campos."
            return
        }

        val edad = edadTexto.toIntOrNull()
        if (edad == null) {
            tvResumen.text = "La edad debe ser un valor numérico válido."
            return
        }

        val resultadoEdad = if (edad >= 18) "Mayor de edad" else "Menor de edad"
        tvResumen.text = """
            Resumen de información:
            Nombres: $nombres
            Apellidos: $apellidos
            Correo electrónico: $correo
            Teléfono celular: $telefono
            Edad: $edad
            Resultado: $resultadoEdad
        """.trimIndent()
    }

    companion object {
        private const val KEY_NOMBRES = "key_nombres"
        private const val KEY_APELLIDOS = "key_apellidos"
        private const val KEY_CORREO = "key_correo"
        private const val KEY_TELEFONO = "key_telefono"
        private const val KEY_EDAD = "key_edad"
        private const val KEY_RESUMEN = "key_resumen"
    }
}
