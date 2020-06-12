package com.kriticalflare.siesgstarena.ac_stats.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.kriticalflare.siesgstarena.databinding.FragmentAcStatsBinding
import com.kriticalflare.siesgstarena.models.Resource
import org.koin.androidx.viewmodel.ext.android.viewModel

class AcStatsFragment : Fragment() {

    private var _binding: FragmentAcStatsBinding? = null
    private val binding get() = _binding!!

    private val statsViewModel: StatsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAcStatsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        statsViewModel.getAllStats().observe(viewLifecycleOwner, Observer { result ->
            when (result.status) {
                Resource.Status.SUCCESS -> {
                    binding.statsRecycler.layoutManager = GridLayoutManager(context, 2)
                    binding.statsRecycler.adapter =
                        result.data?.let { StatsAdapter(it) }
                    binding.statsRecycler.setHasFixedSize(true)
                    binding.statsProgress.visibility = View.GONE
                    binding.statsRecycler.visibility = View.VISIBLE
                }
                Resource.Status.ERROR -> {
                    Toast.makeText(context, result.message, Toast.LENGTH_LONG).show()
                }
                Resource.Status.LOADING -> {
                    binding.statsProgress.visibility = View.VISIBLE
                    binding.statsRecycler.visibility = View.GONE
                }
            }
        })
    }
}
