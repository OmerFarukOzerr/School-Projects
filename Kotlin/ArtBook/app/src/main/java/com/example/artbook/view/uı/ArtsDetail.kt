package com.example.artbook.view.uı

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.example.artbook.R
import com.example.artbook.databinding.FragmentArtDetailBinding
import com.example.artbook.view.util.Resource
import com.example.artbook.view.util.Status
import com.example.artbook.view.viewmodel.ArtsViewModel
import javax.inject.Inject

class ArtsDetail @Inject constructor(
    private val glide : RequestManager,
    private val viewModel : ArtsViewModel

): Fragment(R.layout.fragment_art_detail) {


    private var _binding : FragmentArtDetailBinding? = null
    val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArtDetailBinding.inflate(inflater, container, false)
        val view = binding.root

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeToObservers()

        binding.detailImageView.setOnClickListener {
            val action = ArtsDetailDirections.actionArtsDetailToArtsImage()
            findNavController().navigate(action)
        }
        val callBack = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(callBack)

        binding.detailSaveButton.setOnClickListener {
            viewModel.makeArt(binding.detailArtName.text.toString(),
                binding.detailArtistName.text.toString(),
                binding.detailYear.text.toString())
            findNavController().popBackStack()

        }

    }

    private fun subscribeToObservers() {

        viewModel.imageUrl.observe(viewLifecycleOwner, Observer {url->
           url?.let {
            glide.load(url).into(binding.detailImageView)
           }
        })


        viewModel.insertArtMsg.observe(viewLifecycleOwner, Observer {
            when(it.status) {
                Status.SUCCESS -> {
                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_LONG).show()
                    viewModel.resertArtMessage()
                }

                Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message ?: "hata var kardesh", Toast.LENGTH_LONG).show()

                }

                Status.LOADING -> {

                }
            }
        })

    }




    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}