package com.example.qddapp.UI.popUp

import android.content.res.Resources
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.qddapp.FragmentError
import com.example.qddapp.Modelos.ContinuarRegistroBody
import com.example.qddapp.MyApp
import com.example.qddapp.R
import com.example.qddapp.databinding.FragmentPermisosBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentPermisos : DialogFragment() {

    private lateinit var binding: FragmentPermisosBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentPermisosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setWidthPercent(90)

        /** Navegación **/

        binding.siemprePermiso.setOnClickListener {
            terminarRegistro()
        }

        binding.encendidaPermisos.setOnClickListener {
            terminarRegistro()
        }

        binding.noPermisos.setOnClickListener {
            val permisos2PopUp = FragmentPermisos2()
            permisos2PopUp.show((activity as AppCompatActivity).supportFragmentManager, "AndroidCenter")
            dismiss()
        }

        binding.terminosPermisos.setOnClickListener {
            val terminosPopUp = FragmentTerminosYCondiciones()
            terminosPopUp.show((activity as AppCompatActivity).supportFragmentManager, "AndroidCenter")
        }

        binding.cerrarPermisos.setOnClickListener {
            dismiss()
        }
    }

    fun DialogFragment.setWidthPercent(percentage: Int) {
        val percent = percentage.toFloat() / 100
        val dm = Resources.getSystem().displayMetrics
        val rect = dm.run { Rect(0, 0, widthPixels, heightPixels) }
        val percentWidth = rect.width() * percent
        dialog?.window?.setLayout(percentWidth.toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    fun DialogFragment.setFullScreen() {
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    fun terminarRegistro(){
        val myApp = (requireActivity().application as MyApp)
        val miRepositorio = (requireActivity().application as MyApp).repositorio

        val array = myApp.datos.sacarPreferencias()
        val preferencias = array.removeSurrounding("[", "]").replace(" ","").split(",").map { it.toInt() }

        val continuarRegistroBody = ContinuarRegistroBody(myApp.datos.sacarNombre(), myApp.datos.sacarTelefono(), myApp.datos.sacarFechaNacimiento(), myApp.datos.sacarDescripcion(), myApp.datos.sacarImagen(), preferencias.toIntArray())

        CoroutineScope(Dispatchers.IO).launch {
            val response = miRepositorio.continuarRegistroBody(myApp.datos.sacarUserId(), continuarRegistroBody)
            Log.d("dato_registroCompelto", response.toString())
            withContext(Dispatchers.Main) {
                if (response.isSuccessful && response.code() == 200) {
                    val respuesta = response.body()
                    respuesta?.let {
                        CoroutineScope(Dispatchers.IO).launch {
                            val response = miRepositorio.login(myApp.datos.sacarCorreo().toString(), myApp.datos.sacarContrasena().toString())
                            val token = response.body()
                            token?.let {
                                //TODO SHARED PREFERENCE CON USUARIO Y CONTRASEÑA PARA QUE SI ESTAN GUARDADOS, HACE EL VALIDATE AUTOMATICAMENTE
                                myApp.datos.guardarToken(it.token)
                                myApp.datos.guardarUserId(it.user_id)
                            }
                        }
                        findNavController().navigate(R.id.action_fragmentPreferencias_to_fragmentPantallaCarga)
                        dismiss()
                    }
                } else {
                    FragmentError().show(childFragmentManager, "Tag")
                }
            }
        }
    }
}