package com.kriticalflare.siesgstarena.contests.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kriticalflare.siesgstarena.R
import com.kriticalflare.siesgstarena.databinding.FragmentContestsBinding
import com.kriticalflare.siesgstarena.models.Resource
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContestsFragment : Fragment() {

    private var _binding: FragmentContestsBinding? = null
    private val binding get() = _binding!!

    private val contestsViewModel: ContestsViewModel by viewModel()

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
        setupToolbar()
//        when this vs viewLifeCycleOwner
        contestsViewModel.getAllContests().observe(viewLifecycleOwner, Observer { result ->
            when (result.status) {
                Resource.Status.SUCCESS -> {
                    binding.contestsRecycler.adapter = result.data?.let { ContestsAdapter(it.reversed()) }
                    binding.contestsRecycler.layoutManager = LinearLayoutManager(context)
                    binding.contestsRecycler.setHasFixedSize(true)
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

    private fun setupToolbar(){
        val toolbar = binding.contestsToolbar
        val appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(
                R.id.contestsFragment,
                R.id.problemsFragment,
                R.id.blogsFragment,
                R.id.usersFragment
            )
        )
        toolbar.setupWithNavController(findNavController(), appBarConfiguration)
    }
}
