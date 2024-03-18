package fr.chann.pokedex.presentation.view.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import fr.chann.pokedex.R
import fr.chann.pokedex.presentation.viewstate.PokemonCardViewState

@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun GridItem(pokemon: PokemonCardViewState,
             onImageClick: (String) -> Unit,
             onFavoriteClick: (String) -> Unit,
             onCrossClick: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        onClick = {
            onImageClick(pokemon.id)
        }
    ) {
        Column {
            Row {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically)
                ) {
                    Text(text = pokemon.title, style = MaterialTheme.typography.titleMedium)
                    GlideImage(model = pokemon.image, contentDescription = null,
                        Modifier
                            .aspectRatio(ratio = 1f)
                            .fillMaxWidth())
                }
            }
            Row (
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    shape = RoundedCornerShape(0.dp),
                    modifier = Modifier.weight(1f),
                    onClick = {
                        onCrossClick(pokemon.id)
                    }
                ) {
                    Image(painter = painterResource(R.drawable.ic_cross), contentDescription = null)
                }
                Spacer(Modifier.width(1.dp))
                Button(
                    shape = RoundedCornerShape(0.dp),
                    modifier = Modifier.weight(1f),
                    onClick = {
                        onFavoriteClick(pokemon.id)
                    }
                ) {
                    Image(painter = (
                            if (pokemon.favorite > 0)
                                painterResource(R.drawable.ic_baseline_favorite)
                            else
                                painterResource(R.drawable.ic_baseline_favorite_disabled)
                            ),
                        contentDescription = null
                    )
                }
            }
        }
    }
}