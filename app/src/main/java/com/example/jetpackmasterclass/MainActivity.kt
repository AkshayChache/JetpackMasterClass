package com.example.jetpackmasterclass

import android.app.ProgressDialog.show
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import com.example.jetpackmasterclass.ui.theme.JetpackMasterClassTheme
import com.example.jetpackmasterclass.ui.theme.Typography
import org.intellij.lang.annotations.JdkConstants
import java.time.format.TextStyle

class MainActivity : ComponentActivity() {

    private val viewModel: CalculatorViewModel by viewModels()
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            JetpackMasterClassTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    contentWindowInsets = WindowInsets.systemBars,
                    topBar = {
                        AppTopBar(
                            title = "Calculator",
                            onBackClick = {   },
                            onActionClick = {  }
                        )
                    } ) { innerPadding ->
                    Calculator(modifier = Modifier.padding(innerPadding),viewModel)
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Calculator(modifier: Modifier,viewModel: CalculatorViewModel) {
    var display = viewModel.display
    Box(modifier = modifier.fillMaxSize().background(Color.White)) {
        Column {
           OutlinedTextField(value = display, onValueChange = {},
               Modifier.fillMaxWidth().padding(25.dp)
               .height(150.dp),
               enabled = true,
               readOnly = true,
               textStyle = androidx.compose.ui.text.TextStyle(
                   textAlign = TextAlign.End,
                   fontSize = 28.sp
               )
           )
             Spacer(Modifier.fillMaxWidth().height(10.dp))
             CreateRow(listOf("9","8","7","/"),
                 onUpdate = { it-> viewModel.onInput(it)})
             CreateRow(listOf("6","5","4","x"),
                 onUpdate = {it -> viewModel.onInput(it)})
             CreateRow(listOf("3","2","1","+"),
                 onUpdate = {it -> viewModel.onInput(it)})
            CreateRow(
                listOf("C", "0", ".", "="),
                onUpdate = { it ->
                    when (it) {
                        "=" -> viewModel.onEquals()
                        "C" -> viewModel.onClear()
                        else -> viewModel.onInput(it)
                    }
                })
            }
    }

}

@Composable
fun CreateRow(list: List<String>,onUpdate:(String) -> Unit){
        Row(modifier = Modifier.fillMaxWidth(),
            Arrangement.SpaceEvenly)
        {
            list.forEach { it ->
                Button(onClick = {onUpdate(it)}) {
                    Text(it)
                }
            }
        }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    title: String,
    onBackClick: () -> Unit,
    onActionClick: () -> Unit
) {
    TopAppBar(
        title = { Text(title) },

        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
        },

        actions = {
            IconButton(onClick = onActionClick) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Menu"
                )
            }
        }
    )
}