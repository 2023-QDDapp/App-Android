package com.example.qddapp

import android.content.Context

class DataPreferences(context: Context) {

    val preferences = context.getSharedPreferences("pref_filter", Context.MODE_PRIVATE)
    val editor = preferences.edit()

    fun guardarToken(token: String) {
        editor.putString("token", token)
        editor.apply()
    }

    fun guardarUserId(id: Int) {
        editor.putInt("id_usuario", id)
        editor.apply()
    }

    fun sacarUserId() = preferences.getInt("id_usuario", 0)

    fun eliminarDatos() {
        editor.clear()
        editor.apply()
    }

    fun guardarSesion(correo: String, password: String) {
        editor.putString("correo", correo)
        editor.putString("password", password)
        editor.apply()
    }

    fun sacarCorreo() = preferences.getString("correo", "alvaro.garvin@escuelaestech.es")
    fun sacarContrasena() = preferences.getString("password", "password")

    fun guardarNombre(name: String) {
        editor.putString("name", name)
        editor.apply()
    }

    fun sacarNombre() = preferences.getString("name", "Juan Jimenez Gutierrez").toString()

    fun guardarFechaNacimiento(fecha_nacimiento: String) {
        editor.putString("fecha_nacimiento", fecha_nacimiento)
        editor.apply()
    }

    fun sacarFechaNacimiento() = preferences.getString("fecha_nacimiento", "2003-11-09").toString()
    fun guardarTelefono(telefono: String) {
        editor.putString("telefono", telefono)
        editor.apply()
    }

    fun sacarTelefono() = preferences.getString("telefono", "123456789").toString()

    fun guardarDescripcion(descripcion_registro: String) {
        editor.putString("descripcion_registro", descripcion_registro)
        editor.apply()
    }

    fun sacarDescripcion() = preferences.getString("descripcion_registro", "Prueba").toString()

    fun guardarPreferencias(preferencias: String) {
        editor.putString("preferencias", preferencias)
        editor.apply()
    }

    fun sacarPreferencias() = preferences.getString("preferencias", "[1,2,3]").toString()

    /* EVENTO */

    fun guardarEvento(titulo: String, fecha_inicio: String, fecha_fin: String, descripcion: String, abierto: Boolean, location: String, latitud: String, longitud: String, categoria: Int){
        editor.putString("tituloEvento", titulo)
        editor.putString("fecha_inicio", fecha_inicio)
        editor.putString("fecha_fin", fecha_fin)
        editor.putString("descripcion", descripcion)
        editor.putBoolean("abierto", abierto)
        editor.putString("location", location)
        editor.putString("latitud", latitud)
        editor.putString("longitud", longitud)
        editor.putInt("categoria", categoria)
        editor.apply()
    }

    fun guardarCategoriaBuscar(id_categoria: Int){
        editor.putInt("categoria", id_categoria)
    }

    fun sacarCategoriaBuscar() = preferences.getInt("categoria", 1)

    fun sacarTituloEvento() = preferences.getString("tituloEvento", "TituloPrueba").toString()

    fun sacarFechaInicioEvento() = preferences.getString("fecha_inicio", "dd/mm/yyyy 00:00").toString()

    fun sacarFechaFinEvento() = preferences.getString("fecha_fin", "dd/mm/yyyy 00:00").toString()

    fun sacardescripcionEvento() = preferences.getString("descripcion", "Esto es una descripcion de prueba").toString()


    fun sacarTipoEvento() = preferences.getBoolean("abierto", true)

    fun sacarLocationEvento() = preferences.getString("location", "Linares, España").toString()

    fun sacarLatitudEvento() = preferences.getString("latitud", "38.09383808469612").toString()

    fun sacarLongitudEvento() = preferences.getString("longitud", "-3.6361829317832846").toString()

    fun sacarCategoriaEvento() = preferences.getInt("categoria", 1)
}