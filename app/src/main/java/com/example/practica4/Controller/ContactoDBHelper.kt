package com.example.practica4.Controller

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.practica4.Entity.Contacto


class ContactoDBHelper: SQLiteOpenHelper {
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "contactos.db"
    }

    constructor(context: Context): super(context, DATABASE_NAME, null, DATABASE_VERSION) {
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE contactos (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, apellidos TEXT, dni TEXT, sexo TEXT, fecha_nacimiento TEXT, correo TEXT, profesion TEXT, acepta_terminos INTEGER)")

    }

    fun saveContact(db: SQLiteDatabase?, contacto: Contacto) {
        db?.insert("contactos", null, toContentValues(contacto))

    }

    fun toContentValues(contacto: Contacto): ContentValues {
        val values = ContentValues()

        values.put("nombre", contacto.nombre)
        values.put("apellidos", contacto.apellidos)
        values.put("dni", contacto.dni)
        values.put("sexo", contacto.sexo)
        values.put("fecha_nacimiento", contacto.fecha_nacimiento)
        values.put("correo", contacto.correo)
        values.put("profesion", contacto.profesion)
        values.put("acepta_terminos", contacto.acepta_terminos)

        return values
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE contactos")
        onCreate(db)
    }

    fun getContactos(db: SQLiteDatabase?): ArrayList<Contacto> {
        val contactos = ArrayList<Contacto>()
        val cursor = db?.rawQuery("SELECT * FROM contactos", null)

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val contacto = Contacto()
                    contacto.id = cursor.getInt(0)
                    contacto.nombre = cursor.getString(1)
                    contacto.apellidos = cursor.getString(2)
                    contacto.dni = cursor.getString(3)
                    contacto.sexo = cursor.getString(4)
                    contacto.fecha_nacimiento = cursor.getString(5)
                    contacto.correo = cursor.getString(6)
                    contacto.profesion = cursor.getString(7)
                    contacto.acepta_terminos = cursor.getInt(8) == 1

                    contactos.add(contacto)
                } while (cursor.moveToNext())
            }
        }

        return contactos
    }
}