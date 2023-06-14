package com.example.artbook.view.uı

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.RequestManager
import com.example.artbook.R
import com.example.artbook.databinding.FragmentArtsBinding
import com.example.artbook.view.uı.adapter.ArtsAdapter
import com.example.artbook.view.viewmodel.ArtsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


class Arts @Inject constructor(
    private val artsAdapter: ArtsAdapter,
    private val viewModel: ArtsViewModel

) : Fragment(R.layout.fragment_arts) {

    private var _binding : FragmentArtsBinding? = null
    private val binding get() = _binding!!


    private val swipeCallBack = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val layoutPosition = viewHolder.layoutPosition
            val selectedArt = artsAdapter.arts[layoutPosition]
            viewModel.deleteArt(selectedArt)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentArtsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ArtRecyclerView.adapter = artsAdapter
        binding.ArtRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        ItemTouchHelper(swipeCallBack).attachToRecyclerView(binding.ArtRecyclerView)

        subscribeToObservers()
        
        binding.fab.setOnClickListener {
            val action = ArtsDirections.actionArtsToArtsDetail()
            findNavController().navigate(action)

        }
    }

    fun subscribeToObservers () {
        viewModel.artList.observe(viewLifecycleOwner, Observer { artList ->
            artsAdapter.arts = artList
        })






    }

    override fun onDestroy() {
        super.onDestroy()

    _binding = null
    }

}