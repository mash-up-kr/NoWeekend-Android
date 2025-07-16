package team.noweekend.feature.login

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import team.noweekend.core.common.android.extension.showToast
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.navigator.delegate.LoginNavigationDelegate
import team.noweekend.feature.login.screen.LoginRoute
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {

    @Inject
    lateinit var loginNavigationDelegate: LoginNavigationDelegate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NWKTheme {
                LoginRoute(
                    modifier = Modifier.fillMaxSize(),
                    navigateToOnboarding = {
                        loginNavigationDelegate.navigateToOnboard(
                            activity = this@LoginActivity,
                            intentBuilder = null, // TODO : 필요시 추가
                        )
                    },
                    navigateToGoogleSignUp = { startGoogleSignUpScreen() },
                    showGoogleSignUpErrorToast = {
                        showToast("이용 가능한 구글 계정이 없습니다.")
                    },
                    showGoogleLoginSuccessToast = {
                        showToast("로그인 성공!")
                    },
                    showCancelGoogleAuthToast = {
                        showToast("로그인을 취소했습니다.")
                    },
                    showErrorToast = {
                        showToast("오류가 발생했습니다. 다시 시도해주세요.")
                    },
                )
            }
        }
    }

    private fun startGoogleSignUpScreen() {
        val intent = Intent(Settings.ACTION_ADD_ACCOUNT).apply {
            putExtra(Settings.EXTRA_ACCOUNT_TYPES, arrayOf("com.google"))
        }
        startActivity(intent)
    }
}
