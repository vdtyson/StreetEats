package com.versilistyson.androidstreeteats.presentation.ui.authentication.login

import android.content.Context
import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseUser
import com.haroldadmin.vector.*

import com.versilistyson.androidstreeteats.databinding.FragmentLoginBinding
import com.versilistyson.androidstreeteats.di.activityInjector
import com.versilistyson.androidstreeteats.di.util.DaggerViewModelFactory
import com.versilistyson.androidstreeteats.domain.exception.Failure
import com.versilistyson.androidstreeteats.presentation.ui.MainSharedViewModel
import com.versilistyson.androidstreeteats.presentation.ui.common.BaseVectorFragment
import com.versilistyson.androidstreeteats.presentation.util.observe
import com.versilistyson.androidstreeteats.presentation.util.showToastMessage
import java.util.*
import javax.inject.Inject


class LoginFragment : Fragment() {



    @Inject
    lateinit var daggerViewModelFactory: DaggerViewModelFactory

   val loginViewModel: LoginViewModel by viewModels{
       daggerViewModelFactory
   }
    private val mainSharedViewModel: MainSharedViewModel by activityViewModels {
        daggerViewModelFactory
    }

    private lateinit var loginBinding: FragmentLoginBinding

    override fun onAttach(context: Context) {
        this.findNavController()
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        activityInjector.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loginBinding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        loginBinding.lifecycleOwner = this
        loginBinding.loginViewModel = loginViewModel
        return loginBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userObserver = Observer<FirebaseUser>{ firebaseUser ->
            renderToastFromMainActivity(firebaseUser)
        }
        mainSharedViewModel.firebaseUser.observe(viewLifecycleOwner, Observer(::renderToastFromMainActivity))
        loginViewModel.isLoginSuccessful.observe(viewLifecycleOwner, Observer(::renderLoginSuccessState))
        loginViewModel.isLoading.observe(viewLifecycleOwner, Observer(::renderLoadingState))
    }



    private fun renderLoginSuccessState(isLoginSuccessful: Boolean) {
            if(isLoginSuccessful) {
                mainSharedViewModel.getFirebaseUser()
            }
    }
    private fun renderLoadingState(isLoading: Boolean) {
        if(isLoading) {
            loginBinding.overlayForProgressbar.visibility = View.VISIBLE
            loginBinding.loginProgressBar.animate()
        } else {
            loginBinding.overlayForProgressbar.visibility = View.GONE
            loginBinding.loginProgressBar.clearAnimation()
        }
    }
    private fun renderToastFromMainActivity(firebaseUser: FirebaseUser) {
            this.showToastMessage("uid from mainActivity: ${firebaseUser.uid}", Toast.LENGTH_LONG)
    }
}
