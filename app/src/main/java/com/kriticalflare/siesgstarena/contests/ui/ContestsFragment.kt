package com.kriticalflare.siesgstarena.contests.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.kriticalflare.siesgstarena.databinding.FragmentContestsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContestsFragment : Fragment() {

    private var _binding: FragmentContestsBinding? = null
    private val binding get() = _binding!!

    private val contestsViewModel: ContestsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentContestsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        when this vs viewLifeCycleOwner
        contestsViewModel.contestsList.observe(viewLifecycleOwner, Observer { contests ->
            when {
//                try isNullorEmpty , ideally we dont send null but i am bad and also livedata
                contests.isEmpty() -> {
                    binding.loadingProgressbar.visibility = View.VISIBLE
                    binding.contestsRecycler.visibility = View.GONE
                }
                contests.isNotEmpty() -> {
                    binding.contestsRecycler.adapter = ContestsAdapter(contests)
                    binding.contestsRecycler.layoutManager = LinearLayoutManager(context)
                    binding.contestsRecycler.setHasFixedSize(true)
                    binding.loadingProgressbar.visibility = View.GONE
                    binding.contestsRecycler.visibility = View.VISIBLE
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
