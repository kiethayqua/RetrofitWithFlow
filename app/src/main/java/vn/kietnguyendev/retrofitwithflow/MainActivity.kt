package vn.kietnguyendev.retrofitwithflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import vn.kietnguyendev.retrofitwithflow.ui.theme.RetrofitWithFlowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CoroutineScope(Dispatchers.IO).launch {
            val apiHelperImpl = ApiHelperImpl(RetrofitBuilder.userApi)
            apiHelperImpl
                .getUsers()
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    println("KIET_DEBUG_getUsers was failed by exception: ${e.message}")
                }
                .collect {
                    println("KIET_DEBUG_user_list: $it")
                }
        }
        setContent {
            RetrofitWithFlowTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RetrofitWithFlowTheme {
        Greeting("Android")
    }
}