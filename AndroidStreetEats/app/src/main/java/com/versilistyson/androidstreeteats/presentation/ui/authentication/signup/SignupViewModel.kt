package com.versilistyson.androidstreeteats.presentation.ui.authentication.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.haroldadmin.vector.VectorState
import com.haroldadmin.vector.VectorViewModel
import com.versilistyson.androidstreeteats.domain.common.Either
import com.versilistyson.androidstreeteats.domain.entities.CustomerInfo
import com.versilistyson.androidstreeteats.domain.entities.VendorInfo
import com.versilistyson.androidstreeteats.domain.exception.Failure
import com.versilistyson.androidstreeteats.domain.usecase.CreateCustomerAccount
import com.versilistyson.androidstreeteats.domain.usecase.CreateUserWithEmail
import com.versilistyson.androidstreeteats.domain.usecase.CreateVendorAccount
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
data class SignupState(
    val failure: Failure? = null,
    val authProgress: AuthProgress = AuthProgress.Idle,
    val accountSignupType: AccountSignupType = AccountSignupType.None
) : VectorState {
    enum class AccountSignupType {
        None,
        Vendor,
        Customer
    }
    enum class AuthProgress {
        Idle,
        Loading,
        Success,
        Failure
    }
}
class SignupViewModel
@Inject constructor(
    initialState: SignupState,
    private val createUserWithEmail: CreateUserWithEmail,
    private val createVendorAccount: CreateVendorAccount,
    private val createCustomerAccount: CreateCustomerAccount
) : VectorViewModel<SignupState>(initialState) {

    fun vendorSignUpWithEmail(vendorInfo: VendorInfo, email: String, password: String) = viewModelScope.launch {
        setState {
            copy(authProgress = SignupState.AuthProgress.Loading, accountSignupType = SignupState.AccountSignupType.Vendor)
        }
        launch {
            createUserWithEmail(this, CreateUserWithEmail.Params(email, password)) {result ->
                when(result) {
                    is Either.Right ->
                        writeNewVendorAccount(this, result.right, vendorInfo)
                    is Either.Left ->
                        handleSignUpFailure(result.left)
                }
            }
        }

    }

    fun customerSignUpWithEmail(customerInfo: CustomerInfo, email: String, password: String) = viewModelScope.launch {
        setState {
            copy(authProgress = SignupState.AuthProgress.Loading)
        }
        launch {
            createUserWithEmail(this, CreateUserWithEmail.Params(email, password)) {result ->
                when(result) {
                    is Either.Right ->
                        writeNewCustomerAccount(this, result.right, customerInfo)
                    is Either.Left ->
                        handleSignUpFailure(result.left)
                }

            }
        }
    }

    private fun writeNewCustomerAccount(scope: CoroutineScope, authResult: AuthResult, customerInfo: CustomerInfo) {
        createCustomerAccount(scope, CreateCustomerAccount.Params(authResult.user!!.uid, customerInfo)) { result ->
            when(result) {
                is Either.Right -> {
                    setState { copy(authProgress = SignupState.AuthProgress.Success) }
                }
            }

        }
    }
    private fun writeNewVendorAccount(scope: CoroutineScope, authResult: AuthResult, vendorInfo: VendorInfo) {
        createVendorAccount(scope, CreateVendorAccount.Params(authResult.user!!.uid, vendorInfo)) {result ->
            when(result) {
                is Either.Right -> {
                    setState {
                        copy(authProgress = SignupState.AuthProgress.Success)
                    }
                }
                is Either.Left ->
                    handleSignUpFailure(result.left)
            }
        }
    }

    private fun handleSignUpFailure(failure: Failure) {
        setState {
            copy(failure = failure, authProgress = SignupState.AuthProgress.Failure)
        }
    }
}