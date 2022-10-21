package com.ddvader44.securepass.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ddvader44.securepass.databinding.PasswordRowBinding
import com.ddvader44.securepass.db.Password

class PasswordAdapter(private val allPasswords: List<Password>) : RecyclerView.Adapter<PasswordAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: PasswordRowBinding)
        :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //val view = LayoutInflater.from(parent.context).inflate(R.layout.password_row, parent, false)
        //return ViewHolder(view)
        val binding = PasswordRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        with(holder) {
            binding.type.text = allPasswords[position].app
            binding.hashed.text = allPasswords[position].hashed
        }


    }

    override fun getItemCount() = allPasswords.size



}
