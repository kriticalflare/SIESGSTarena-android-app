package com.kriticalflare.siesgstarena.problemset.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.kriticalflare.siesgstarena.R
import com.kriticalflare.siesgstarena.databinding.FragmentProblemsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class ProblemsFragment : Fragment() {

    private var _binding: FragmentProblemsBinding? = null
    private val binding get() = _binding!!

    private val problemsViewModel: ProblemsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProblemsBinding.inflate(inflater, container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        problemsViewModel.problemSetList.observe(viewLifecycleOwner, Observer {problems->
            when {
//                try isNullorEmpty , ideally we dont send null but i am bad and also livedata
                problems.isEmpty() -> {
                    binding.loadingProgressbar.visibility = View.VISIBLE
                    binding.problemsRecycler.visibility = View.GONE
                }
                problems.isNotEmpty() -> {
                    binding.problemsRecycler.adapter = ProblemsAdapter(problems,requireContext())
                    binding.problemsRecycler.layoutManager = LinearLayoutManager(context)
                    binding.problemsRecycler.setHasFixedSize(true)
                    binding.loadingProgressbar.visibility = View.GONE
                    binding.problemsRecycler.visibility = View.VISIBLE
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
