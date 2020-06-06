package com.kriticalflare.siesgstarena.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kriticalflare.siesgstarena.R
import com.kriticalflare.siesgstarena.authentication.AuthenticationManager
import com.kriticalflare.siesgstarena.databinding.FragmentOnboardingBinding
import org.koin.core.KoinComponent
import org.koin.core.get

class OnboardingFragment : Fragment(), KoinComponent {

    private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding!!
    private val authenticationManager: AuthenticationManager = get()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonOnboarding.setOnClickListener {
            if (binding.usernameOnboarding.text.toString().isNotEmpty()) {
                authenticationManager.saveRegistration(binding.usernameOnboarding.text.toString())
                findNavController().navigate(R.id.onboarding_to_bottom_nav)
            } else {
                Toast.makeText(context, "Enter a username", Toast.LENGTH_LONG).show()
            }
        }
    }
}
