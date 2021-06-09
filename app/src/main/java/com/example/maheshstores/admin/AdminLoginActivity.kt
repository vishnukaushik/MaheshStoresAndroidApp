package com.example.maheshstores.admin

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.widget.Button
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
    lateinit var btn_login: Button
    lateinit var progress: ProgressBar
    private val componentRetriever = ComponentsRetriever()

    private val callback = object : Callback<BooleanResponse> {
        override fun onResponse(call: Call<BooleanResponse>, response: retrofit2.Response<BooleanResponse>)
        {
            btn_login.visibility = View.VISIBLE
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
            btn_login.visibility = View.VISIBLE
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
        btn_login = findViewById(R.id.bt_login)
        progress = findViewById(R.id.progress)
        etUserId = findViewById(R.id.et_user_id)
        etPassword = findViewById(R.id.et_password)
        btn_login.setOnClickListener {
            if(isNetworkConnected())
                login()
            else
            {
                progress.visibility = View.GONE
                btn_login.visibility = View.VISIBLE
                AlertDialog.Builder(this).setTitle("Could not connect to the server")
                    .setMessage("Server not reachable")
                    .setPositiveButton(android.R.string.ok) { _, _ -> }
                    .setIcon(android.R.drawable.ic_dialog_alert).show()
            }
        }
    }

    private fun isNetworkConnected(): Boolean {
        val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager //1
        val networkInfo = connectivityManager.activeNetworkInfo //2
        return networkInfo != null && networkInfo.isConnected //3
    }

    fun login()
    {
        progress.visibility = View.VISIBLE
        btn_login.visibility = View.GONE
        val userId = etUserId.text.toString()
        val password = etPassword.text.toString()
        componentRetriever.adminLogin(userId, password, callback)
    }
}

