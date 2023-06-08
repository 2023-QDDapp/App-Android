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

    fun guardarImagen(img: String){
        editor.putString("foto", img)
        editor.apply()
    }

    fun sacarImagen() = preferences.getString("foto", "data:image/jpeg;base64,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IORX3JouoxavodjqMWPLuYEmGRjggGgDRooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooA+av2idba58R6do6MTHb2/nMo6b3Pp9APzrxWvSPjmJB8UtQ3n5THFs/wB3YP65rzegAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACvr/4MXzX3wt0kt9+IPEef7rHH6Yr5Ar6w+A0TJ8MrdmHElxKVGPfH9KAPT6KKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKAPlr9oO28n4gwS87ZrGNuvcFh+fFeS17j+0jb7da0W55+e3kTr6MD/AFrw6gAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACvsP4P2y23ws0VdpBZGkPry5NfHgGTgda+3PAdt9k8A6DAOosoiT9VB9PegDo6KKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKAPCv2krYtpOh3IA+SaRDz6gH+lfOtfUP7Q1qJ/AFtMoyYL5DkdgVYf4V8vUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQA+JS0yKDglgK+7tKgFrpFnBjBjt0X6YUCvh/Qbf7X4g063C5MtzHGF9csB/WvuuMbY154A7DrQA+iiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiikLYXPb3oA85+OFt9p+FuotjJieFx/38A/rXyPXq3xL+Kes6prGs6DBJEmjh2tvKCAl9p+8W65yK8poAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigDp/h3bfbPiFoMJG5ftsbEYz0Of6V9sA5Ga+EdE1m88P6rb6nYOqXMBLRsyhgGwRnB+tfT/wAHPHN9400O8XVSJLyzkCvIq7d6sOCQO/BoA9PooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigApCCQf6GlooA+GvF0DW/jHWomySl7MOf8AfNYtd38YtNOm/E3Vl24SdxOp9dwBP65rhKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAK+g/2bISLXX58ZUyRKM8dAxr58r6l/Z+0z7F8P5Lthhry6eRTjqq4X+YNAHrVFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFAHzp+0bo3l6npWsIuRLE1u5HqpyP0J/KvC6+uvjRoX9ufDm9ZELTWJW6TjnC/e/wDHSa+RaACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAVRuYAdScV9ueB9I/sHwPpOmlMPFarvH+0Rlv1Jr5L+HOh/8JF480rT2XdEZhJLj+4vzH+WPxr7UACrx0HoKAHUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAV7m3jurWW3mQPFKhRl9VIwRXxL4v8P3HhjxPf6TOrf6PKdjEffQ8q34jFfcVcj4v+Hvh7xp5Uuq2zG4iGEmifa2PQnuKAPi6irmrWD6Xq95YSAhrad4jn1UkVToAKKKKACiiigAooooAKKKKACiiigAooooAKKK9U+Dfw90zxrNqU2rpM1tahAqxvtDOxPBP0H60Adb+zv4XZFv8AxJcxkBh9mtiw6jOXI/QfnXv1VNP0+00mwisbCCOC1hASOOMYCirdABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAfI3xt0X+yPiReSKMQ3qi5T6nhv1BrzmvpT9obw99r8OWWuRLlrKXypSD1jfoT9GA/OvmugAooooAKKKKACiiigAooooAKKKKACiiigAr6w+BWiNpXw7huWTEt/K05yeQudq/oM/jXy5o+mzaxrFnp1uu6W5mWJR7k4r7k0rT4NK0mz0+AARW0SxKB6KMUAXqKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAyfEWjw+IfD1/pdwQY7mBo89gSOD17HBr4h1PT59L1O6sLlNk1vI0bqexBwa+9K+bP2gPCDWGtReI7ZP8AR73EdxgfdlA4PXuB+hoA8TooooAKKKKACiiigAooooAKKKKACiipIYHuJUiiVnkdgqqBySaAPXv2f/DB1LxTNrlwhNvp6YjyOsrDA/ADP5ivp2uR+HXhVfCHgyz04qBcuPOuSB1kbr+XA6dq66gAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigArlfiJY2d/wDD7W47uNXjS1eVefus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").toString()

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
        editor.putInt("categoriaBuscar", id_categoria)
    }

    fun borrarCategoriaBuscar() {
        editor.remove("categoriaBuscar")
        editor.apply()
    }

    fun sacarCategoriaBuscar() = preferences.getInt("categoriaBuscar", 1)

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