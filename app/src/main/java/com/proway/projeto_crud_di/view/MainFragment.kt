package com.proway.projeto_crud_di.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.proway.projeto_crud_di.R
import com.proway.projeto_crud_di.databinding.ActivitySplashBinding
import com.proway.projeto_crud_di.databinding.MainFragmentBinding
import com.proway.projeto_crud_di.view_model.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.main_fragment) {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = MainFragmentBinding.bind(view)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)


        CoroutineScope(Dispatchers.Main).launch {
            val numeroDeRegistrosPercorridos = CoroutineScope(Dispatchers.Main).async {
                checkNames()
            }
            val registros = numeroDeRegistrosPercorridos.await()
            binding.numTextView.text = "Número de nomes percorrido foi de: $registros"
        }

        viewModel.getRepositories()
        viewModel.getUsers()

    }

    suspend fun checkNames(): Int {
        val listOfNames = listOf("Arthur", "Aline", "Joaquim", "Pedro")
        listOfNames.forEach {
            binding.message.text = it
            delay(2000)
        }
        return listOfNames.size
    }

}