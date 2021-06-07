package com.example.maheshstores

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.maheshstores.admin.AdminLoginActivity
import com.example.maheshstores.guest.NonAdminLoginActivity

class FirstPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_page)
    }

    fun onClickAdminLogin(view: View)
    {
        val intent = Intent(this, AdminLoginActivity::class.java)
        startActivity(intent)
    }

    fun onClickNonAdminLogin(view: View)
    {
        val intent = Intent(this, NonAdminLoginActivity::class.java)
        startActivity(intent)
    }
}