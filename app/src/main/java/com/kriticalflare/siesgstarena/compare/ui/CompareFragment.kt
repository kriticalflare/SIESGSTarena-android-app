package com.kriticalflare.siesgstarena.compare.ui

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.textfield.TextInputLayout
import com.kriticalflare.siesgstarena.R
import com.kriticalflare.siesgstarena.databinding.FragmentCompareBinding
import com.kriticalflare.siesgstarena.models.Compare
import com.kriticalflare.siesgstarena.models.Resource
import org.koin.androidx.viewmodel.ext.android.viewModel

class CompareFragment : Fragment() {

    private var _binding: FragmentCompareBinding? = null
    private val binding get() = _binding!!

    private val compareViewModel: CompareViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCompareBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.compareBattleButton.setOnClickListener {
            getComparison()
        }
    }

    private fun getComparison() {
        if (validateInput()) {
            val username1 = binding.compareUsername1.editText?.text.toString()
            val username2 = binding.compareUsername2.editText?.text.toString()
            compareViewModel.getComparison(username1, username2)
                .observe(viewLifecycleOwner, Observer { result ->
                    when (result.status) {
                        Resource.Status.SUCCESS -> {
                            val user1Result = result.data!![0]
                            val user2Result = result.data[1]

                            binding.compareResult.text = requireContext().getString(
                                R.string.compare_result,
                                computeWinner(user1Result, user2Result)
                            )

                            bindStats(user1Result, user2Result)

                            binding.compareProgress.visibility = View.GONE
                            binding.compareResultGroup.visibility = View.VISIBLE
                        }
                        Resource.Status.ERROR -> {
                            binding.compareProgress.visibility = View.GONE
                            binding.compareResultGroup.visibility = View.GONE
                            binding.compareEmptyText.visibility = View.VISIBLE
                            Toast.makeText(context, result.message, Toast.LENGTH_LONG).show()
                        }
                        Resource.Status.LOADING -> {
                            binding.compareEmptyText.visibility = View.GONE
                            binding.compareResultGroup.visibility = View.GONE
                            binding.compareProgress.visibility = View.VISIBLE
                        }
                    }
                })
        }
    }

    private fun bindStats(user1: Compare, user2: Compare) {
        bindUser1Data(user1)
        bindUser2Data(user2)

        bindStatColors(
            user1.performance.accepted,
            user2.performance.accepted,
            binding.compareAcceptedUser1,
            binding.compareAcceptedUser2
        )

        bindStatColors(
            user1.performance.wrongAnswer,
            user2.performance.wrongAnswer,
            binding.compareWrongUser1,
            binding.compareWrongUser2
        )
        bindStatColors(
            user1.performance.compilationError,
            user2.performance.compilationError,
            binding.compareCompilationUser1,
            binding.compareCompilationUser2
        )
        bindStatColors(
            user1.performance.runtimeError,
            user2.performance.runtimeError,
            binding.compareRuntimeUser1,
            binding.compareRuntimeUser2
        )
        bindStatColors(
            user1.performance.timeLimitExceeded,
            user2.performance.timeLimitExceeded,
            binding.compareTimelimitUser1,
            binding.compareTimelimitUser2
        )
    }

    @Suppress("DEPRECATION")
    private fun bindStatColors(
        user1Stat: Int,
        user2Stat: Int,
        user1TextView: TextView,
        user2TextView: TextView
    ) {
        if (user1Stat > user2Stat) {
            if (Build.VERSION.SDK_INT >= 23) {
                user1TextView.setTextColor(
                    resources.getColor(
                        R.color.green,
                        requireContext().theme
                    )
                )
                user2TextView.setTextColor(resources.getColor(R.color.red, requireContext().theme))
            } else {
                user1TextView.setTextColor(resources.getColor(R.color.green))
                user2TextView.setTextColor(resources.getColor(R.color.red))
            }
        } else if (user1Stat < user2Stat) {
            if (Build.VERSION.SDK_INT >= 23) {
                user1TextView.setTextColor(resources.getColor(R.color.red, requireContext().theme))
                user2TextView.setTextColor(
                    resources.getColor(
                        R.color.green,
                        requireContext().theme
                    )
                )
            } else {
                user1TextView.setTextColor(resources.getColor(R.color.red))
                user2TextView.setTextColor(resources.getColor(R.color.green))
            }
        } else {
            if (Build.VERSION.SDK_INT >= 23) {
                user1TextView.setTextColor(resources.getColor(R.color.blue, requireContext().theme))
                user2TextView.setTextColor(resources.getColor(R.color.blue, requireContext().theme))
            } else {
                user1TextView.setTextColor(resources.getColor(R.color.blue))
                user2TextView.setTextColor(resources.getColor(R.color.blue))
            }
        }
    }

    private fun bindUser1Data(data: Compare) {
        binding.compareAcceptedUser1.text = data.performance.accepted.toString()
        binding.compareWrongUser1.text = data.performance.wrongAnswer.toString()
        binding.compareCompilationUser1.text = data.performance.compilationError.toString()
        binding.compareRuntimeUser1.text = data.performance.runtimeError.toString()
        binding.compareTimelimitUser1.text = data.performance.timeLimitExceeded.toString()
    }

    private fun bindUser2Data(data: Compare) {
        binding.compareAcceptedUser2.text = data.performance.accepted.toString()
        binding.compareWrongUser2.text = data.performance.wrongAnswer.toString()
        binding.compareCompilationUser2.text = data.performance.compilationError.toString()
        binding.compareRuntimeUser2.text = data.performance.runtimeError.toString()
        binding.compareTimelimitUser2.text = data.performance.timeLimitExceeded.toString()
    }

    private fun computeWinner(user1: Compare, user2: Compare): String {
        if (user1.performance.accepted >= user2.performance.accepted) {
            return user1.user.name
        }
        return user2.user.name
    }

    private fun validateInput(): Boolean {
        val username1 = binding.compareUsername1.editText?.text.toString()
        val username2 = binding.compareUsername2.editText?.text.toString()

        if (username1.isBlank()) {
            displayInputError(binding.compareUsername1, "Invalid username")
            return false
        }
        if (username2.isBlank()) {
            displayInputError(binding.compareUsername2, "Invalid username")
            return false
        }
        if (username1 == username2) {
            displayInputError(binding.compareUsername2, "both usernames are same")
            return false
        }

        binding.compareUsername1.isErrorEnabled = false
        binding.compareUsername2.isErrorEnabled = false

        return true
    }

    private fun displayInputError(
        inputLayout: TextInputLayout,
        message: String
    ) {
        inputLayout.error = message
    }
}
