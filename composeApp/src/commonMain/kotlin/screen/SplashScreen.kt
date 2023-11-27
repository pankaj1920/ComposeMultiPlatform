package screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.app.composemultiplatform.SharedResource
import dev.icerock.moko.resources.compose.painterResource
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.ExperimentalResourceApi


object SplashScreen : Screen {
/*    LaunchedEffect(state.navigateToMain){
        delay(3000L)
        if (state.navigateToMain) {
            navigateToMain()
        } else {
            navigateToLogin()
        }
    }*/



    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(Unit){
            delay(3000L)
            navigator.replace(LoginScreen)
        }

        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(painterResource(SharedResource.images.img_hair), null)

        }
    }
}