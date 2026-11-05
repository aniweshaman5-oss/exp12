package com.example.exp12

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.exp12.databinding.ActivityUserDetailBinding
import com.example.exp12.model.User

class UserDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        val user = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(UsersActivity.EXTRA_USER, User::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra(UsersActivity.EXTRA_USER) as? User
        }

        user?.let { displayUserDetails(it) }
    }

    private fun displayUserDetails(user: User) {
        with(binding) {
            // Profile Card
            tvProfileNumber.text = user.id.toString()
            tvProfileName.text = user.name
            tvProfileUsername.text = if (user.username.startsWith("@")) user.username else "@${user.username}"

            // Contact Info
            tvEmail.text = user.email
            tvPhone.text = user.phone
            tvWebsite.text = user.website

            // Address
            tvStreet.text = user.address.street
            tvSuite.text = user.address.suite
            tvCity.text = user.address.city
            tvZipcode.text = user.address.zipcode

            // Company Details
            tvCompanyName.text = user.company.name
            tvCompanyCatchphrase.text = user.company.catchPhrase
            tvCompanyBs.text = user.company.bs
        }
    }
}
