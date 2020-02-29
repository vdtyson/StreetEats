package com.versilistyson.androidstreeteats.presentation.ui.authentication.signup

import androidx.lifecycle.viewModelScope
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import com.versilistyson.androidstreeteats.data.firebase.models.AccountType
import com.versilistyson.androidstreeteats.domain.entities.BusinessInfo
import com.versilistyson.androidstreeteats.domain.entities.CustomerInfo
import com.versilistyson.androidstreeteats.domain.entities.UserInfo
import com.versilistyson.androidstreeteats.domain.exception.Failure
import com.versilistyson.androidstreeteats.domain.usecase.CreateCustomerAccount
import com.versilistyson.androidstreeteats.domain.usecase.CreateUserWithEmail
import com.versilistyson.androidstreeteats.domain.usecase.CreateBusinessAccount
import com.versilistyson.androidstreeteats.presentation.ui.common.BaseViewModel
import com.versilistyson.androidstreeteats.presentation.ui.common.PageState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

sealed class SignupPageState(
    userAccountType: AccountType,
    isSignupSuccessful: Boolean,
    errorType: SignUpErrorType
) : PageState() {
    enum class SignUpErrorType {
        SERVER,
        BAD_CREDENTIALS
    }

    data class BusinessSignup(
        val businessLogo: String = "",
        val businessName: String = "",
        val isSignupSuccessful: Boolean = false,
        val errorType: SignUpErrorType
    ) : SignupPageState(AccountType.BUSINESS, isSignupSuccessful, errorType)

    data class CustomerSignup(
        val username: String = "",
        val isSignupSuccessful: Boolean = false,
        val errorType: SignUpErrorType
    ) : SignupPageState(AccountType.CUSTOMER, isSignupSuccessful, errorType)
}

class SignupViewModel
@AssistedInject constructor(
    @Assisted initialPageState: SignupPageState,
    private val createUserWithEmail: CreateUserWithEmail,
    private val createBusinessAccount: CreateBusinessAccount,
    private val createCustomerAccount: CreateCustomerAccount
) : BaseViewModel<SignupPageState>(initialPageState) {

    @AssistedInject.Factory
    interface Factory {
        fun create(initialPageState: SignupPageState): SignupViewModel
    }

    fun signupCustomerAccountWithEmail(
        email: String,
        password: String,
        customerInfo: CustomerInfo,
        userInfo: UserInfo
    ) = viewModelScope.launch {
        setLoadingState(true)
        launch(Dispatchers.IO) {
            createUserAndAccountWithEmail(this, email, password, userInfo) { uid ->
                createCustomerAccount(this, CreateCustomerAccount.Params(uid, customerInfo)) {
                    it.fold(::handleSignupFailure) {
                        setState {
                            val state = currentState as SignupPageState.CustomerSignup
                            state.copy(
                                username = customerInfo.userName,
                                isSignupSuccessful = true
                            )
                        }
                    }
                }
            }
        }
    }

    fun signupBusinessAccountWithEmail(
        email: String,
        password: String,
        businessInfo: BusinessInfo,
        userInfo: UserInfo
    ) = viewModelScope.launch {
        setLoadingState(true)
        launch(Dispatchers.IO) {
            createUserAndAccountWithEmail(this, email, password, userInfo) {uid ->
                createBusinessAccount(this, CreateBusinessAccount.Params(uid, businessInfo)) {
                    it.fold(::handleSignupFailure) {
                        setState {
                            val state = currentState as SignupPageState.BusinessSignup
                            state.copy(
                                businessLogo = businessInfo.vendorLogoUrl,
                                businessName = businessInfo.vendorName,
                                isSignupSuccessful = true
                            )
                        }
                    }
                }
            }
        }
    }

    private fun createUserAndAccountWithEmail(
        scope: CoroutineScope,
        email: String,
        password: String,
        userInfo: UserInfo,
        fn: (uid: String) -> Any
    ) {
        createUserWithEmail(
            scope,
            CreateUserWithEmail.Params(email, password, userInfo)
        ) {
            it.fold(::handleSignupFailure) { authResult ->
                fn(authResult.user!!.uid)
            }
        }
    }

    private fun handleSignupFailure(failure: Failure) {
        TODO()
    }
}