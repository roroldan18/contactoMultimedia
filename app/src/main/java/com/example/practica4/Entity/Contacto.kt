package com.example.practica4.Entity

class Contacto {
    var id: Int? = null
    var nombre: String? = null
    var apellidos: String? =  null
    var dni: String? = null
    var sexo: String? = null
    var fecha_nacimiento: String? = null
    var correo: String? = null
    var profesion: String? = null
    var acepta_terminos: Boolean? = null

    constructor(id:Int?, nombre: String?, apellido: String?, dni: String?, sexo:String?, fecha_nacimiento: String?, correo: String?, profesion: String?, acepta_terminos: Boolean?) {
        this.id = id
        this.nombre = nombre
        this.apellidos = apellido
        this.fecha_nacimiento = fecha_nacimiento
        this.sexo = sexo
        this.dni = dni
        this.correo = correo
        this.profesion = profesion
        this.acepta_terminos = acepta_terminos
    }

    constructor(nombre: String?, apellido: String?, dni: String?, sexo:String?, fecha_nacimiento: String?, correo: String?, profesion: String?, acepta_terminos: Boolean?) {
        this.nombre = nombre
        this.apellidos = apellido
        this.fecha_nacimiento = fecha_nacimiento
        this.sexo = sexo
        this.dni = dni
        this.correo = correo
        this.profesion = profesion
        this.acepta_terminos = acepta_terminos
    }

    constructor() {
        this.nombre = ""
        this.apellidos = ""
        this.fecha_nacimiento = ""
        this.sexo = ""
        this.dni = ""
        this.correo = ""
        this.profesion = ""
        this.acepta_terminos = false
    }


}