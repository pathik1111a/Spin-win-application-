package in.pathik.spinnwin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                SpinNWinScreen()
            }
        }
    }
}

@Composable
private fun SpinNWinScreen() {
    var coins by remember { mutableIntStateOf(100) }
    var status by remember { mutableStateOf("Welcome! Try a free demo spin.") }
    var scratchRevealed by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF101326)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(Color(0xFF191D3A), Color(0xFF101326))
                    )
                )
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "SPIN N WIN",
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.Black
            )
            Text(
                text = "Play • Collect demo coins",
                color = Color(0xFFB8BDE5),
                fontSize = 14.sp
            )
            Spacer(Modifier.height(24.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF282F58))
            ) {
                Column(Modifier.padding(20.dp)) {
                    Text("YOUR DEMO BALANCE", color = Color(0xFFB8BDE5))
                    Text(
                        text = "🪙 $coins coins",
                        color = Color.White,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(Modifier.height(24.dp))
            Text("🎡", fontSize = 90.sp)
            Text("FREE DEMO SPIN", color = Color.White, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(10.dp))
            Button(
                onClick = {
                    val reward = listOf(5, 10, 15, 20).random()
                    coins += reward
                    status = "Demo result: +$reward coins!"
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("SPIN NOW")
            }

            Spacer(Modifier.height(22.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF282F58))
            ) {
                Column(
                    modifier = Modifier.padding(18.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("🎟️  SCRATCH CARD", color = Color.White, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = if (scratchRevealed) "You revealed 10 demo coins!" else "Tap below to reveal your demo card",
                        color = Color(0xFFDBDDF7)
                    )
                    Spacer(Modifier.height(8.dp))
                    OutlinedButton(
                        onClick = {
                            if (!scratchRevealed) {
                                scratchRevealed = true
                                coins += 10
                                status = "Scratch card: +10 demo coins!"
                            } else {
                                status = "This demo card has already been scratched."
                            }
                        }
                    ) {
                        Text(if (scratchRevealed) "REVEALED" else "SCRATCH")
                    }
                }
            }

            Spacer(Modifier.height(18.dp))
            Text(status, color = Color(0xFFFFD66B), fontWeight = FontWeight.SemiBold)
            Spacer(Modifier.weight(1f))
            Text(
                "Demo only • Coins have no cash or diamond value",
                color = Color(0xFF9DA3CC),
                fontSize = 11.sp
            )
        }
    }
}
