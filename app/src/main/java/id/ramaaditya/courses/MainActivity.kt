package id.ramaaditya.courses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.ramaaditya.courses.data.DataSource
import id.ramaaditya.courses.model.Topic
import id.ramaaditya.courses.ui.theme.CoursesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge() // Mengaktifkan tampilan penuh layar
        super.onCreate(savedInstanceState)
        setContent {
            CoursesTheme { // Mengatur tema untuk aplikasi
                Surface(
                    modifier = Modifier
                        .fillMaxSize() // Mengisi seluruh ukuran layar
                        .statusBarsPadding(), // Menambahkan padding untuk area status
                    color = MaterialTheme.colorScheme.background // Menggunakan warna latar belakang dari tema
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Red) // Mengubah background menjadi merah
                    ) {
                        TopicColumn( // Menampilkan kolom topik
                            modifier = Modifier.padding(
                                start = dimensionResource(R.dimen.padding_small),
                                top = dimensionResource(R.dimen.padding_small),
                                end = dimensionResource(R.dimen.padding_small),
                            )
                        )
                    }
                }
            }
        }
    }
}

// Komponen untuk menampilkan daftar topik dalam kolom
@Composable
fun TopicColumn(modifier: Modifier = Modifier) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)), // Mengatur jarak antar item
        modifier = modifier
    ) {
        items(DataSource.topics) { topic -> // Mengambil data topik dari DataSource
            TopicCard(topic) // Menampilkan card untuk setiap topik
        }
    }
}

// Komponen untuk menampilkan detail setiap topik dalam card
@Composable
fun TopicCard(topic: Topic, modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier
            .fillMaxWidth() // Mengambil lebar penuh
            .padding(dimensionResource(R.dimen.padding_medium)), // Padding untuk card
        colors = CardDefaults.cardColors(containerColor = Color.LightGray) // Ubah warna background Card
    ) {
        Row {
            Box {
                Image(
                    painter = painterResource(id = topic.imageRes), // Mengambil gambar dari sumber daya
                    contentDescription = null,
                    modifier = modifier
                        .size(width = 68.dp, height = 68.dp)
                        .aspectRatio(1f), // Menjaga rasio aspek gambar
                    contentScale = ContentScale.Crop // Memotong gambar agar sesuai dengan ukuran
                )
            }

            Column {
                Text(
                    text = stringResource(id = topic.name), // Menampilkan nama topik
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.padding_medium),
                        top = dimensionResource(R.dimen.padding_medium),
                        end = dimensionResource(R.dimen.padding_medium),
                        bottom = dimensionResource(R.dimen.padding_small)
                    )
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(R.drawable.ic_grain), // Mengambil ikon dari sumber daya
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = dimensionResource(R.dimen.padding_medium))
                    )
                    Text(
                        text = topic.availableCourses.toString(), // Menampilkan jumlah kursus yang tersedia
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_small))
                    )
                }
            }
        }
    }
}

// Fungsi untuk preview tampilan card topik
@Preview(showBackground = true)
@Composable
fun TopicPreview() {
    CoursesTheme {
        val topic = Topic(R.string.photography, 321, R.drawable.photography) // Contoh topik
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopicCard(topic = topic) // Menampilkan card topik di preview
        }
    }
}
