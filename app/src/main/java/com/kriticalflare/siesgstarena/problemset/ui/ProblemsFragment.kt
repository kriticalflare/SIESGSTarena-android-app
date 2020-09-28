package com.kriticalflare.siesgstarena.problemset.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionManager
import com.google.android.material.transition.MaterialFadeThrough
import com.kriticalflare.siesgstarena.databinding.FragmentProblemsBinding
import com.kriticalflare.siesgstarena.models.Resource
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class ProblemsFragment : Fragment() {

    private var _binding: FragmentProblemsBinding? = null
    private val binding get() = _binding!!

    private val problemsViewModel: ProblemsViewModel by viewModel()

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
        _binding = FragmentProblemsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var firstRun = true
        problemsViewModel.getAllProblemSets().observe(viewLifecycleOwner, Observer { result ->
            when (result.status) {
                Resource.Status.SUCCESS -> {
                    binding.problemsRecycler.adapter =
                        result.data?.let { ProblemsAdapter(it.reversed(), requireContext()) }
                    binding.problemsRecycler.layoutManager = LinearLayoutManager(context)
                    binding.problemsRecycler.setHasFixedSize(true)
                    if (firstRun) {
                        firstRun = firstRun.not()
                        val fadeThrough = MaterialFadeThrough()
                        TransitionManager.beginDelayedTransition(binding.root, fadeThrough)
                    }
                    binding.loadingProgressbar.visibility = View.GONE
                    binding.problemsRecycler.visibility = View.VISIBLE
                }
                Resource.Status.ERROR -> {
                    Toast.makeText(context, result.message, Toast.LENGTH_LONG).show()
                }
                Resource.Status.LOADING -> {
                    binding.loadingProgressbar.visibility = View.VISIBLE
                    binding.problemsRecycler.visibility = View.GONE
                }
            }
        })

        binding.problemsRefresh.setOnRefreshListener {
            problemsViewModel.refreshProblemSet()
            val r = Runnable {
                binding.problemsRefresh.isRefreshing = false
            }
            binding.problemsRefresh.postDelayed(r, 1000)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
