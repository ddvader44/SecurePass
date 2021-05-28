package com.ddvader44.securepass.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.ddvader44.securepass.R
import com.ddvader44.securepass.databinding.FragmentSuccessBinding


class SuccessFragment : Fragment() {

    private val args :SuccessFragmentArgs by navArgs()

    private var _binding : FragmentSuccessBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSuccessBinding.inflate(inflater, container, false)

        binding.hashTextView.text = args.hash

        return binding.root
    }

}