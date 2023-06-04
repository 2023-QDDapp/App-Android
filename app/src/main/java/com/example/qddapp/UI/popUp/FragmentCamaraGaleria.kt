package com.example.qddapp.UI.popUp

import android.content.res.Resources
import android.graphics.Rect
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.qddapp.MyViewModel
import com.example.qddapp.databinding.FragmentCamaraGaleriaBinding
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class FragmentCamaraGaleria : DialogFragment() {

    private lateinit var binding:FragmentCamaraGaleriaBinding
//    private lateinit var uriCamera: Uri
//    private val model by viewModels<MyViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCamaraGaleriaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setWidthPercent(90)

        binding.cerrarCamaraGaleria.setOnClickListener {
            dismiss()
        }

//        binding.camara.setOnClickListener {
//            tomarImagenCamara()
//        }

//        binding.galeria.setOnClickListener {
//            tomarImagenGaleria()
//        }
    }

    fun DialogFragment.setWidthPercent(percentage: Int) {
        val percent = percentage.toFloat() / 100
        val dm = Resources.getSystem().displayMetrics
        val rect = dm.run { Rect(0, 0, widthPixels, heightPixels) }
        val percentWidth = rect.width() * percent
        dialog?.window?.setLayout(percentWidth.toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
    }

//    private fun tomarImagenCamara() {
//        val archivoFoto = crearArchivoParaFoto() //Esto me devuelve un File
//
//        //packageName es esto: com.estech.takephotosample
//        uriCamera = FileProvider.getUriForFile(requireContext(), "com.example.qddapp.fileprovider", archivoFoto)
//
//        resultadoCamara.launch(uriCamera)
//    }
//
//    private val resultadoCamara = registerForActivityResult( //Esto es para hacer una foto de la camara y nos devuelve un booleano
//        ActivityResultContracts.TakePicture()
//    ) { guardado ->
//        if(guardado){
//            model.imageUri.value = uriCamera
//        } else {
//            val rutaArchivo = getExternalFilesDir("/Imagenes/" + uriCamera.path)
//            rutaArchivo?.let{
//                val resultado = rutaArchivo.delete()
//                if(resultado) Toast.makeText(requireContext(), "Imagen Eliminada", Toast.LENGTH_SHORT).show()
//                else Toast.makeText(requireContext(), "ERROR al borrar", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//
//    @Throws(IOException::class)
//    private fun crearArchivoParaFoto(): File {
//        //nombre de archivo con fecha y hora actual
//        val timeStamp: String =
//            SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
//        // ruta a la carpeta privada de la App
//        val storageDir: File? = getExternalFilesDir("/Imagenes/")
//        // Crea un objeto File con el nombre de archivo, la extensión y la carpeta donde se almacena el archivo
//        return File.createTempFile(
//            "JPEG_${timeStamp}_", /* prefijo */
//            ".jpg", /* sufijo */
//            storageDir /* directorio */
//        )
//    }

//    private fun tomarImagenGaleria(){
//        resultadoTomarImagen.launch(arrayOf("image/*"))
//    }
//
//    private val resultadoTomarImagen = registerForActivityResult( //Esto sirve para abrir un documento, y te devuelve una URI
//        ActivityResultContracts.OpenDocument()
//    ){ uri ->
//        model.imageUri.value = uri
//    }
}