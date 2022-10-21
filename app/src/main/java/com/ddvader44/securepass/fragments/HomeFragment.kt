package com.ddvader44.securepass.fragments

import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.ddvader44.securepass.R
import com.ddvader44.securepass.databinding.FragmentHomeBinding
import com.ddvader44.securepass.db.Password
import com.ddvader44.securepass.db.PasswordDatabase
import com.ddvader44.securepass.viewmodels.HomeViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onResume() {
        super.onResume()
        val websites = resources.getStringArray(R.array.websites)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.drop_down, websites)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        setHasOptionsMenu(true)

        binding.generateBtn.setOnClickListener {
            onGenerateClicked()
        }


        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun onGenerateClicked() {
        if (binding.plainText.text.isEmpty() || binding.platform.text.isEmpty()) {
            showSnackbar("Please enter something first!")
        } else {
            lifecycleScope.launch {
                applyAnimations()
                binding.plainText.text.clear()
                binding.platform.text.clear()
                navigateToSuccess(getHashData())
            }
        }
    }


    private suspend fun applyAnimations() {
        binding.generateBtn.isClickable = false
        binding.title.animate().alpha(0f).duration = 400L
        binding.generateBtn.animate().alpha(0f).duration = 400L
        binding.textInputLayout.animate()
            .alpha(0f)
            .translationXBy(1200f)
            .duration = 400L
        binding.plainText.animate()
            .alpha(0f)
            .translationXBy(-1200f)
            .duration = 400L
        binding.platform.animate()
            .alpha(0f)
            .translationXBy(1200f)
            .duration = 400L

        delay(300)

        binding.successBackground.animate().alpha(1f).duration = 600L
        binding.successBackground.animate().rotationBy(720f).duration = 600L
        binding.successBackground.animate().scaleXBy(900f).duration = 800L
        binding.successBackground.animate().scaleYBy(900f).duration = 800L

        delay(300)

        binding.successImage.animate().alpha(1f).duration = 1000L

        delay(1500L)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getHashData(): String {
        val algorithm = binding.autoCompleteTextView.text.toString()
        val plainText = binding.plainText.text.toString()
        val app = binding.platform.text.toString()
        val output = homeViewModel.getHash(plainText, algorithm)

        val database = activity?.let {
            Room.databaseBuilder(it, PasswordDatabase::class.java, "password_database")
                .allowMainThreadQueries()
                .build()
        }

        database?.passwordDao()?.insertPassword(Password(null, type = algorithm, hashed = output,app = app))

        return output

    }

    private fun showSnackbar(message: String) {
        val snackBar = Snackbar.make(
            binding.rootLayout,
            message,
            Snackbar.LENGTH_SHORT
        )
        snackBar.setAction("OK") {}
        snackBar.setActionTextColor(ContextCompat.getColor(requireContext(),R.color.blue))
        snackBar.show()
    }

    private fun navigateToSuccess(hash: String) {
        val directions = HomeFragmentDirections.actionHomeFragmentToSuccessFragment(hash)
        findNavController().navigate(directions)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.clear_menu) {
            binding.plainText.text.clear()
            binding.platform.text.clear()
            showSnackbar("Cleared!")
            return true
        }
        if(item.itemId == R.id.show_passwords){
            findNavController().navigate(R.id.action_homeFragment_to_dataFragment2)
        }
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}