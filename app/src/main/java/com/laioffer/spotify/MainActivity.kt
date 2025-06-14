package com.laioffer.spotify

import android.media.Image
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.laioffer.spotify.ui.theme.SpotifyTheme

// customized extend AppCompatActivity
class MainActivity : AppCompatActivity() {
    private val TAG = "lifecycle"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "We are at onCreate()")

        // Compose
//        setContent {
//            SpotifyTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background
//                ) {
////                    Greeting("Android")
////                    HelloContent()
////                    var name by remember {
////                        mutableStateOf("")
////                    }
////                    HelloContentStateless(
////                        name = name,
////                        onNameChange = { name = it}
////                    )
//                    AlbumCover()
//                }
//            }
//        }
    }
}

@Composable
fun AlbumCover() {
    Column {
        Box (modifier = Modifier.size(160.dp)) {
            // https://upload.wikimedia.org/wikipedia/en/d/d1/Stillfantasy.jpg
//            Image(painter = , contentDescription = )
            AsyncImage(
                model = "https://upload.wikimedia.org/wikipedia/en/d/d1/Stillfantasy.jpg",
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = "Still Fantasy",
                modifier = Modifier
                    .padding(start = 2.dp, bottom = 4.dp) // left is start, right is end
                    .align(Alignment.BottomStart),
                style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Bold),
                color = Color.White
            )
        }

        Text(
            text = "Jay Chou",
            modifier = Modifier.padding(top = 4.dp, start = 2.dp),
            style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Bold),
            color = Color.LightGray
        )
    }
}

@Composable
fun ArtistCardBox() {
    Box {
        Text("Alfred Sisley")
        Text("3 minutes ago")
    }
}

@Composable
fun ArtistCardColumn() {
    Column {
        Text("Alfred Sisley")
        Text("3 minutes ago")
    }
}

@Composable
fun ArtistCardRow() {
    Row {
        Text("Alfred Sisley")
        Text("3 minutes ago")
    }
}

@Preview
@Composable
fun PreviewArtistCardBox() {
    SpotifyTheme {
        Surface {
            ArtistCardBox()
        }
    }
}

@Preview
@Composable
fun PreviewArtistCardRow() {
    SpotifyTheme {
        Surface {
            ArtistCardRow()
        }
    }
}

@Preview
@Composable
fun PreviewArtistCardColumn() {
    SpotifyTheme {
        Surface {
            ArtistCardColumn()
        }
    }
}


@Composable
private fun Greeting(name: String) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
//        .padding(24.dp)
        .background(Color.Yellow)
        .padding(24.dp)

    ) {
        Text(text = "Hello,")
        Text(text = name)
    }
}

@Composable
fun HelloContent() {
//    var name: String = "" // state
    // by: delegation
    var name by remember { mutableStateOf("") } // state

    Column {
        if (name.isNotEmpty()) {
            Text(
                modifier = Modifier.padding(bottom = 8.dp),
                text = "Hello! $name",
                style = MaterialTheme.typography.body2
            )
        }
        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
            },
            label = { Text(text = "name") }
        )
    }
}

@Composable
fun HelloContentStateless(name: String, onNameChange: (String) -> Unit) {
    Column {
        if (name.isNotEmpty()) {
            Text(
                modifier = Modifier.padding(bottom = 8.dp),
                text = "Hello! $name",
                style = MaterialTheme.typography.body2
            )
        }
        OutlinedTextField(
            value = name,
            onValueChange = onNameChange,
            label = { Text(text = "name") }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewGreeting() {
    SpotifyTheme {
        Surface {
            HelloContent()
        }
    }
}