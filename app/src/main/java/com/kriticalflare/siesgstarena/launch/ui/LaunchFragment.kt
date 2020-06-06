package com.kriticalflare.siesgstarena.launch.ui

import android.os.Handler
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.kriticalflare.siesgstarena.R
import com.kriticalflare.siesgstarena.authentication.AuthenticationManager
import org.koin.core.KoinComponent
import org.koin.core.get

class LaunchFragment : Fragment(R.layout.fragment_launch), KoinComponent {

    private val handler = Handler()

    private val finishSplash: Runnable = Runnable {
        val authenticationManager: AuthenticationManager = get()

        if (authenticationManager.isAuthenticated()) {
            Navigation.findNavController(requireView())
                .navigate(R.id.splash_to_bottom_nav)
        } else {
            Navigation.findNavController(requireView()).navigate(R.id.splash_to_onboarding)
        }
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(finishSplash, 1L)
    }

    override fun onDestroyView() {
        handler.removeCallbacks(finishSplash)
        super.onDestroyView()
    }
}
