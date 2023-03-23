package com.example.loginusingmvvm

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.loginusingmvvm.data.network.Resource
import com.example.loginusingmvvm.data.responses.LoginResponse
import com.example.loginusingmvvm.databinding.ActivityLoginBinding
import com.example.loginusingmvvm.ui.auth.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel by viewModels<AuthViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_login)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //binding.progressbar.visible(false)
        //binding.buttonLogin.enable(false)

        /*binding.editTextTextPassword.addTextChangedListener {
            val email = binding.editTextTextEmailAddress.text.toString().trim()
            binding.buttonLogin.enable(email.isNotEmpty() && it.toString().isNotEmpty())
        }*/


        binding.buttonLogin.setOnClickListener {
            login()
        }

        viewModel.loginResponse.observe(this) {
            when (it) {
                is Resource.Success -> {
                    //Toast.makeText(this@LoginActivity, "Loading...", Toast.LENGTH_LONG).show()
                    processLogin(it.value)
                }



                is Resource.Failure->{
                    if(it.isNetworkError)
                        Toast.makeText(this@LoginActivity, "Please check your internet connection", Toast.LENGTH_SHORT).show()
                    if(it.errorCode==200)
                    {
                        Toast.makeText(this@LoginActivity, "Success...", Toast.LENGTH_LONG).show()
                    }


                }
                else -> {

                }
            }
        }
    }

    private fun processLogin(value: LoginResponse)
    {
        System.out.println(value.output)
        //Toast.makeText(this@LoginActivity,"Success:" +  value?.output?.get(0)?.message,Toast.LENGTH_SHORT).show()
        if(value.output.get(0).success.equals("yes"))
        {
            Toast.makeText(this@LoginActivity,"Login Successful",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this@LoginActivity,"Login Error",Toast.LENGTH_SHORT).show()
        }
    }

    private fun login() {
        val email = binding.editTextTextEmailAddress.text.toString().trim()
        val password = binding.editTextTextPassword.text.toString().trim()
        viewModel.login(email, password)
    }
}