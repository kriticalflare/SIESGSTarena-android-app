package com.kriticalflare.siesgstarena.contests.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionManager
import com.google.android.material.transition.MaterialFadeThrough
import com.kriticalflare.siesgstarena.databinding.FragmentContestsBinding
import com.kriticalflare.siesgstarena.models.Resource
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContestsFragment : Fragment() {

    private var _binding: FragmentContestsBinding? = null
    private val binding get() = _binding!!

    private val contestsViewModel: ContestsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialFadeThrough()
        exitTransition = MaterialFadeThrough()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentContestsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var firstRun = true
//        when this vs viewLifeCycleOwner
        contestsViewModel.getAllContests().observe(viewLifecycleOwner, { result ->
            when (result.status) {
                Resource.Status.SUCCESS -> {
                    binding.contestsRecycler.adapter = result.data?.let { ContestsAdapter(it.reversed(), requireContext()) }
                    binding.contestsRecycler.layoutManager = LinearLayoutManager(context)
                    binding.contestsRecycler.setHasFixedSize(true)
                    if (firstRun) {
                        firstRun = firstRun.not()
                        val fadeThrough = MaterialFadeThrough()
                        TransitionManager.beginDelayedTransition(binding.root, fadeThrough)
                    }
                    binding.loadingProgressbar.visibility = View.GONE
                    binding.contestsRecycler.visibility = View.VISIBLE
                }
                Resource.Status.ERROR -> {
                    Toast.makeText(context, result.message, Toast.LENGTH_LONG).show()
                }
                Resource.Status.LOADING -> {
                    binding.loadingProgressbar.visibility = View.VISIBLE
                    binding.contestsRecycler.visibility = View.GONE
                }
            }
        })

        binding.contestsRefresh.setOnRefreshListener {
            contestsViewModel.refreshContests()
            val r = Runnable {
                binding.contestsRefresh.isRefreshing = false
            }
            binding.contestsRefresh.postDelayed(r, 1000)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
