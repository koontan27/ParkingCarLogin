package com.example.parkinglogin.ui


import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.parkinglogin.R
import kotlinx.android.synthetic.main.fragment_login.*
import com.example.parkinglogin.databinding.FragmentLoginBinding

/**
 * A simple [Fragment] subclass.
 */
class loginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentLoginBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false)

        binding.btnLogin.setOnClickListener{view: View->
            binding.apply {
                var username = username.text.toString()
                var password = password.text.toString()
                if(username == "admin" && password == "1234"){
                    view.findNavController()
                        .navigate(R.id.carSlot)
                }else{
                    Toast.makeText(getActivity(), "Username or Password Invalid",
                        Toast.LENGTH_LONG).show();
                }
            }
            val context = this.getContext()
            val inputMethodManager =
                context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
        return binding.root
    }


}
