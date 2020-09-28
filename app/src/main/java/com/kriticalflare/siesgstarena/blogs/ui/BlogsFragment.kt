package com.kriticalflare.siesgstarena.blogs.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.transition.MaterialFadeThrough
import com.kriticalflare.siesgstarena.databinding.FragmentBlogsBinding
import com.kriticalflare.siesgstarena.models.Resource
import org.koin.androidx.viewmodel.ext.android.viewModel

class BlogsFragment : Fragment() {

    private var _binding: FragmentBlogsBinding? = null
    private val binding get() = _binding!!

    private val blogsViewModel: BlogsViewModel by viewModel()

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
        _binding = FragmentBlogsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        blogsViewModel.getAllBlogs().observe(viewLifecycleOwner, Observer { result ->
            when (result.status) {
                Resource.Status.SUCCESS -> {
                    binding.blogsRecycler.layoutManager = LinearLayoutManager(context)
                    binding.blogsRecycler.adapter =
                        result.data?.let { BlogsAdapter(it, requireContext()) }
                    binding.blogsRecycler.setHasFixedSize(true)
                    binding.loadingProgressbar.visibility = View.GONE
                    binding.blogsRecycler.visibility = View.VISIBLE
                }
                Resource.Status.ERROR -> {
                    Toast.makeText(context, result.message, Toast.LENGTH_LONG).show()
                }
                Resource.Status.LOADING -> {
                    binding.loadingProgressbar.visibility = View.VISIBLE
                    binding.blogsRecycler.visibility = View.GONE
                }
            }
        })

        binding.blogsRefresh.setOnRefreshListener {
            blogsViewModel.refreshBlogs()
            val r = Runnable {
                binding.blogsRefresh.isRefreshing = false
            }
            binding.blogsRefresh.postDelayed(r, 1000)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
