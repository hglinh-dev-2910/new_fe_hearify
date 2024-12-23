import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.new_fe_hearify.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchingScreen(
    modifier: Modifier = Modifier,
    name: String,
    age: Int
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF2DEDE))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(60.dp)
        ) {
            Spacer(modifier = Modifier.height(100.dp))

            // "Thích bạn" and name/age texts in a Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // "Thích bạn" text with background
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color(0x80FFFFFF))
                        .padding(8.dp)
                ) {
                    Text(
                        text = "Thích bạn",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                    )
                }

                // Name and age text with background
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0x80ADD8E6))
                        .padding(8.dp)
                ) {
                    Text(
                        text = "$name, $age",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                    )
                }
            }

            // Image Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .clip(RoundedCornerShape( topStart = 20.dp, topEnd = 20.dp,bottomStart = 20.dp, bottomEnd = 20.dp))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.messi),
                    contentDescription = "profile picture",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Heart and Close buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                IconButton(onClick = { /* Do nothing */ }) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Heart",
                        tint = Color.White,
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(Color.Red)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                IconButton(onClick = { /* Do nothing */ }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        tint = Color.White,
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(Color.LightGray)
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // "Xem trang cá nhân" button with background
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color(0x80ADD8E6))
            ) {
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                ) {
                    Text("Xem trang cá nhân", color = Color.Black)
                }
            }

            // "Danh sách ghép đôi" button with background
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color(0x80ADD8E6))
            ) {
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                ) {
                    Text("Danh sách ghép đôi", color = Color.Black)
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun MatchingPreview() {
    MatchingScreen(
        name = "Messi",
        age = 37
    )
}