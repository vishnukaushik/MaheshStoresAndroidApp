package com.example.maheshstores.admin

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.example.maheshstores.R
import com.example.maheshstores.api.ComponentsRetriever
import com.example.maheshstores.dbresponse.BooleanResponse
import retrofit2.Call
import retrofit2.Callback

class AdminLoginActivity : AppCompatActivity() {
    lateinit var etUserId: EditText
    lateinit var etPassword: EditText
    lateinit var progress: ProgressBar
    private val componentRetriever = ComponentsRetriever()

    private val callback = object : Callback<BooleanResponse> {
        override fun onResponse(call: Call<BooleanResponse>, response: retrofit2.Response<BooleanResponse>)
        {
            progress.visibility = View.GONE
            val success = response.body()?.success
            val message = response.body()?.message
            if(success=="1")
            {
                val intent = Intent(this@AdminLoginActivity,AdminActivity::class.java)
                startActivity(intent)
            }
            else
            {
                AlertDialog.Builder(this@AdminLoginActivity).setTitle("Unsuccessful")
                    .setMessage(message)
                    .setPositiveButton(android.R.string.ok) { _, _ -> }.show()
            }
        }

        override fun onFailure(call: Call<BooleanResponse>, t: Throwable) {
            progress.visibility = View.GONE
            AlertDialog.Builder(this@AdminLoginActivity).setTitle("Could not connect to the server")
                .setMessage("Server not reachable")
                .setPositiveButton(android.R.string.ok) { _, _ -> }
                .setIcon(android.R.drawable.ic_dialog_alert).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_login)
        progress = ProgressBar(this)
        etUserId = findViewById(R.id.et_user_id)
        etPassword = findViewById(R.id.et_password)
    }

    fun login(view: View)
    {
        val userId = etUserId.text.toString()
        val password = etPassword.text.toString()
        componentRetriever.adminLogin(userId, password, callback)
    }
}

