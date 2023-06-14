package com.example.artbook.view.uı

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.artbook.R
import com.example.artbook.databinding.FragmentArtsBinding
import com.example.artbook.databinding.FragmentImageApiBinding
import com.example.artbook.view.util.Status
import com.example.artbook.view.uı.adapter.ImageApıAdapter
import com.example.artbook.view.viewmodel.ArtsViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class ArtsImage @Inject constructor(
    private val adapter : ImageApıAdapter,
    private val viewwModel : ArtsViewModel

): Fragment(R.layout.fragment_image_api) {

    private var _binding : FragmentImageApiBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentImageApiBinding.inflate(inflater,container,false)
        val view = binding.root

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageRecyclerView.adapter = adapter
        binding.imageRecyclerView.layoutManager = GridLayoutManager(requireContext(),3)

        var job : Job? = null

        binding.imageSearchBar.addTextChangedListener {
            job?.cancel()
            job = lifecycleScope.launch {
                delay(1000)
                it?.let {
                    if (it.toString().isNotEmpty()) {
                        viewwModel.searchImage(it.toString())
                    }
                }
            }
        }

        subscribeToObservers()

        //burada detailda aldığımz urly buradan veriyoruz
        adapter.customOnClickListener {
            viewwModel.setSelectedImageUrl(it)
            findNavController().popBackStack()
        }

    }

    fun subscribeToObservers() {

        viewwModel.imageList.observe(viewLifecycleOwner, Observer {response->
            when(response.status) {

                Status.SUCCESS -> {
                    val urls = response.data?.hits?.map {imageResult ->
                        imageResult.previewURL
                    }
                    adapter.images = urls ?: arrayListOf()
                    binding.progressBar.visibility = View.GONE
                }

                Status.ERROR-> {
                    Toast.makeText(requireContext(),response.message ?: "hataaa", Toast.LENGTH_LONG).show()
                    binding.progressBar.visibility = View.GONE
                }

                Status.LOADING-> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        })


    }




    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }
}