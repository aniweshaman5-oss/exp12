package com.example.exp12.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exp12.databinding.ItemUserBinding
import com.example.exp12.model.User

class UserAdapter(
    private val users: List<User>,
    private val onUserClick: (User) -> Unit
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    inner class UserViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        with(holder.binding) {
            tvUserNumber.text = user.id.toString()
            tvName.text = user.name
            tvUsername.text = if (user.username.startsWith("@")) user.username else "@${user.username}"
            tvEmail.text = user.email

            root.setOnClickListener {
                onUserClick(user)
            }
        }
    }

    override fun getItemCount(): Int = users.size
}
