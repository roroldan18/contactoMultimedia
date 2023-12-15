package com.example.practica4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Spinner
import com.example.practica4.Controller.ContactoDBHelper
import com.example.practica4.Entity.Contacto

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Obtener los datos del formularios
        val nombreApellido = findViewById<EditText>(R.id.nombreEdit)
        val dni = findViewById<EditText>(R.id.dniEdit)
        val sexo = findViewById<RadioGroup>(R.id.radioGroup)
        var sexoSeleccionado = ""
        val fecha_nacimiento = findViewById<EditText>(R.id.datePicker)
        val correo = findViewById<EditText>(R.id.emailEdit)
        val profesion = findViewById<Spinner>(R.id.spinner)
        val acepta_terminos = findViewById<CheckBox>(R.id.terminosCheck)

        // Obtener el botón de guardar y Recuperar
        val guardar = findViewById<Button>(R.id.botonEnviar)
        val recuperar = findViewById<Button>(R.id.botonRecuperar)

        // Crear el objeto ContactoDBHelper
        val contactoDBHelper = ContactoDBHelper(this)

        // Validar campos del formulario

        // 1. No puede haber campos vacíos.

        fun validarCampos(){
            if (nombreApellido.text.toString().isEmpty()){
                nombreApellido.error = "El campo no puede estar vacío"
            }
            if (dni.text.toString().isEmpty()){
                dni.error = "El campo no puede estar vacío"
            }
            if (fecha_nacimiento.text.toString().isEmpty()){
                fecha_nacimiento.error = "El campo no puede estar vacío"
            }
            if (correo.text.toString().isEmpty()){
                correo.error = "El campo no puede estar vacío"
            }
        }

        // 2. El DNI debe tener 9 caracteres.
        fun checkDNI(){
            if (dni.text.toString().length != 9){
                dni.error = "El DNI debe tener 9 caracteres"
            }
        }

        // 3. El correo debe tener un @ y debe ser válido.
        fun checkCorreo(){
            if (correo.text.toString().contains("@")){
                correo.error = "El correo debe tener un @"
            }

            val regex = Regex("^[A-Za-z0-9+_.-]+@(.+)\$")
            if (regex.matches(correo.text.toString())){
                correo.error = "El correo debe ser válido"
            }
        }

        // 4. El nombre y apellidos deben tener al menos un espacio " ".
        fun checkNombreApellido(){
            if (nombreApellido.text.toString().contains(" ")){
                nombreApellido.error = "El nombre y apellidos deben tener al menos un espacio"
            }
        }

        // 5. La fecha de nacimiento debe ser anterior a la fecha actual.
        fun checkDate(){
            val fecha = fecha_nacimiento.text.toString()
            val fechaActual = System.currentTimeMillis()
            val fechaNacimiento = fecha.toLong()

            if (fechaNacimiento > fechaActual){
                fecha_nacimiento.error = "La fecha de nacimiento debe ser anterior a la fecha actual"
            }
        }

        // 6. El usuario debe aceptar los términos y condiciones.

        fun checkTerminos(){
            if (!acepta_terminos.isChecked){
                acepta_terminos.error = "El usuario debe aceptar los términos y condiciones"
            }
        }



        // Guardar los datos del formulario
        guardar.setOnClickListener {
            validarCampos()
            checkDNI()
            checkCorreo()
            checkNombreApellido()
            checkDate()
            checkTerminos()

            // Separar el nombreApellido en dos variables: nombre y apellidos
            // Antes del primer espacio está el nombre y después los apellidos
            // Ejemplo: "Juan Pérez" -> nombre = "Juan" y apellidos = "Pérez"
            val partes = nombreApellido.text.toString().split(" ")
            var nombre = partes[0]
            var apellidos = partes.subList(1, partes.size).joinToString(" ")

            // Obtener sexo del radioGroup
            val selectedSex = sexo.checkedRadioButtonId
            if (selectedSex == R.id.radioHombre){
                sexoSeleccionado = "Hombre"
            } else if (selectedSex == R.id.radioMujer){
                sexoSeleccionado = "Mujer"
            } else {
                sexoSeleccionado = "Otro"
            }


            val contacto = Contacto(
                nombre,
                apellidos,
                dni.text.toString(),
                sexoSeleccionado,
                fecha_nacimiento.text.toString(),
                correo.text.toString(),
                profesion.selectedItem.toString(),
                acepta_terminos.isChecked
            )

            contactoDBHelper.saveContact(contacto)
        }





    }
}