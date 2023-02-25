package com.arif.spliff.ui.login

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.arif.spliff.databinding.ActivityLoginBinding
import com.arif.spliff.model.login.RequestLogin
import com.arif.spliff.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding


    lateinit var progressDialog: ProgressDialog


    private lateinit var viewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)



        progressDialog = ProgressDialog(this)

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]



        binding.loginBtn.setOnClickListener {

            val loginUser = RequestLogin(
                binding.emailEditText.text.toString().trim(),
                binding.passwordEditText.text.toString().trim(),
            )


            progressDialog.setTitle("Please Wait !")
            progressDialog.setMessage("loging in...")
            progressDialog.show()
            viewModel.loginUserVM(loginUser)


        }


        viewModel.responseLoginData.observe(this) {

            if (it.success == true) {
                progressDialog.dismiss()
                Toasty.success(this, "${it.message?.body?.get(0)}", Toast.LENGTH_SHORT, true).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                progressDialog.dismiss()
                Toasty.error(this, "${it.message?.body?.get(0)}", Toast.LENGTH_SHORT, true).show()
            }


        }
    }
}