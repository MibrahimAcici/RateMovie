package com.ibrahim.ratemovie.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibrahim.ratemovie.R
import com.ibrahim.ratemovie.adapter.RateMovieAdapter
import com.ibrahim.ratemovie.databinding.FragmentTenBinding

class TenFragment:Fragment() {
    private var _binding: FragmentTenBinding? = null
    private val binding get() = _binding!!

    private lateinit var rateMovieAdapter: RateMovieAdapter
    private val viewModel: RateMovieViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTenBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        observeRatedList()
    }

    private fun observeRatedList() {
        viewModel.getRatedMoviesByRate(10).observe(viewLifecycleOwner) {
            rateMovieAdapter.setList(it)
        }
    }

    private fun initAdapter() {
        rateMovieAdapter = RateMovieAdapter()
        binding.recyclerView.adapter = rateMovieAdapter
        //Adapterin Ekrandaki Görünümü
        val layoutManager = LinearLayoutManager(context)
        binding.recyclerView.layoutManager = layoutManager
    }

    //Fragment Destroy edildiğinde binding boşaltılsın diye
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}