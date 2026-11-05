package com.example.exp12

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exp12.adapter.UserAdapter
import com.example.exp12.databinding.ActivityUsersBinding
import com.example.exp12.model.User
import com.example.exp12.network.UserApiClient
import kotlinx.coroutines.launch

class UsersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUsersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        binding.recyclerViewUsers.layoutManager = LinearLayoutManager(this)

        binding.btnRetry.setOnClickListener {
            loadUsers()
        }

        loadUsers()
    }

    private fun loadUsers() {
        binding.progressBar.visibility = View.VISIBLE
        binding.layoutError.visibility = View.GONE
        binding.recyclerViewUsers.visibility = View.GONE

        lifecycleScope.launch {
            val result = UserApiClient.fetchUsers()
            binding.progressBar.visibility = View.GONE

            result.onSuccess { users ->
                if (users.isEmpty()) {
                    binding.tvErrorMessage.text = "No users found."
                    binding.layoutError.visibility = View.VISIBLE
                } else {
                    binding.recyclerViewUsers.visibility = View.VISIBLE
                    val adapter = UserAdapter(users) { user ->
                        openUserDetail(user)
                    }
                    binding.recyclerViewUsers.adapter = adapter
                }
            }.onFailure { exception ->
                binding.tvErrorMessage.text = exception.localizedMessage ?: "Failed to fetch users. Check internet connection."
                binding.layoutError.visibility = View.VISIBLE
            }
        }
    }

    private fun openUserDetail(user: User) {
        val intent = Intent(this, UserDetailActivity::class.java).apply {
            putExtra(EXTRA_USER, user)
        }
        startActivity(intent)
    }

    companion object {
        const val EXTRA_USER = "extra_user"
    }
}
