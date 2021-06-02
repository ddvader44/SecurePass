package com.ddvader44.securepass.fragments

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.navArgs
import com.ddvader44.securepass.R
import com.ddvader44.securepass.databinding.FragmentSuccessBinding
import com.google.android.material.snackbar.Snackbar


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

        binding.copyButton.setOnClickListener {
            onCopyClicked()
        }

        return binding.root
    }

    private fun onCopyClicked() {
        copyToClipboard(args.hash)
    }

    private fun copyToClipboard(hash: String) {
        val clipboardManager = requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("Encrypted Text",hash)
        clipboardManager.setPrimaryClip(clipData)
        showSnackbar("Text Copied Successfully!")
    }

    private fun showSnackbar(message: String) {
        val snackBar = Snackbar.make(
            binding.successLayout,
            message,
            Snackbar.LENGTH_SHORT
        )
        snackBar.setAction("OK") {}
        snackBar.setActionTextColor(ContextCompat.getColor(requireContext(),R.color.blue))
        snackBar.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}