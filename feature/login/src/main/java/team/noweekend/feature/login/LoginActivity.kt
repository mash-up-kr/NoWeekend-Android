package team.noweekend.feature.login

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import team.noweekend.feature.login.screen.LoginRoute

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginRoute(
                modifier = Modifier.fillMaxSize(),
                navigateToOnboarding = {
                    Toast.makeText(this, "로그인 성공!", Toast.LENGTH_SHORT).show()
                },
                navigateToGoogleSignUp = { googleSignUp() },
                showGoogleSignUpErrorToast = {
                    Toast.makeText(this, "구글 계정이 없습니다.", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }

    private fun googleSignUp() {
        val intent = Intent(Settings.ACTION_ADD_ACCOUNT).apply {
            putExtra(Settings.EXTRA_ACCOUNT_TYPES, arrayOf("com.google"))
        }
        startActivity(intent)
    }
}
