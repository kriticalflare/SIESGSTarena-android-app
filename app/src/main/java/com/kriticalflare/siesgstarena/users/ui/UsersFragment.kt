package com.kriticalflare.siesgstarena.users.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment

import com.kriticalflare.siesgstarena.R

class UsersFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_users, container, false)
    }
}