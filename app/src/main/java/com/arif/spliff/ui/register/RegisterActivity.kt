package com.arif.spliff.ui.register

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.arif.spliff.databinding.FragmentRegisterBinding
import com.arif.spliff.model.register.RequestRegisterUser
import com.arif.spliff.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {
    lateinit var binding: FragmentRegisterBinding
    lateinit var progressDialog: ProgressDialog

    var isTermsChecked = false


    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progressDialog = ProgressDialog(this)

        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]

        binding.checkBox.setOnCheckedChangeListener { _, _isTermsChecked ->
            isTermsChecked = _isTermsChecked
        }


        binding.createBtn.setOnClickListener {


            val registerUser = RequestRegisterUser(
                binding.nameEditText.text.toString().trim(),
                binding.companyEditText.text.toString().trim(),
                binding.phoneEditText.text.toString().trim(),
                binding.emailEditText.text.toString().trim(),
                binding.passwordEditText.text.toString().trim(),
                binding.conformPasswordEditText.text.toString().trim(),
                binding.addressEditText.text.toString().trim(),
            )

            if (isTermsChecked) {

                progressDialog.setTitle("Please Wait !")
                progressDialog.setMessage("Registering...")
                progressDialog.show()
                viewModel.registerUserVM(registerUser)
            } else {

                Toasty.error(
                    this,
                    "You have to agree with terms & conditions To continue!",
                    Toast.LENGTH_SHORT,
                    true
                ).show();


            }


        }

        viewModel.responseData.observe(this) {

            if (it.success == true) {
                progressDialog.dismiss()



                if (!it.message?.body.isNullOrEmpty()) {
                    Toasty.success(this, "Success!", Toast.LENGTH_SHORT, true).show()
                }

                startActivity(Intent(this, MainActivity::class.java))
                finish()


            } else {
                progressDialog.dismiss()
                Log.i("TAG", "success: ${it.message} ")

                if (!it.message?.email.isNullOrEmpty()) {
                    binding.emailEditText.error = it.message?.email?.get(0)
                }


                if (!it.message?.phone.isNullOrEmpty()) {
                    binding.phoneEditText.error = it.message?.phone?.get(0)
                }


                if (!it.message?.name.isNullOrEmpty()) {
                    binding.nameEditText.error = it.message?.name?.get(0)
                }

                if (!it.message?.password.isNullOrEmpty()) {
                    binding.nameEditText.error = it.message?.password?.get(0)
                }


                if (!it.message?.passwordConfirmation.isNullOrEmpty()) {
                    binding.nameEditText.error = it.message?.passwordConfirmation?.get(0)
                }

            }


        }


    }
}