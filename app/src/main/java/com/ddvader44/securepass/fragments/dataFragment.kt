package com.ddvader44.securepass.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.ddvader44.securepass.adapter.PasswordAdapter
import com.ddvader44.securepass.databinding.FragmentDataBinding
import com.ddvader44.securepass.db.PasswordDatabase

class dataFragment : Fragment() {

    private var _binding: FragmentDataBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDataBinding.inflate(inflater, container, false)

        val database = activity?.let {
            Room.databaseBuilder(it, PasswordDatabase::class.java, "password_database")
                .allowMainThreadQueries()
                .build()
        }

        val allPasswords = database?.passwordDao()?.getAllPassword()

        binding.passwordRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = allPasswords?.let { PasswordAdapter(it) }
        }

        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}