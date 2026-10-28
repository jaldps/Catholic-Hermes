package com.example.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Church
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.VolunteerActivism
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.models.AppLanguage
import com.example.ui.theme.AppThemePreset

private data class ExploreItem(
    val destination: AppDestination,
    val icon: ImageVector,
    val titleEn: String,
    val titleEs: String,
    val titlePt: String,
    val descriptionEn: String,
    val descriptionEs: String,
    val descriptionPt: String
)

private val exploreItems = listOf(
    ExploreItem(
        AppDestination.HABITS_VIRTUES, Icons.Default.Shield,
        "Habits & virtues", "Hábitos y virtudes", "Hábitos e virtudes",
        "Build a steady rule of life", "Construye una regla de vida", "Construa uma regra de vida"
    ),
    ExploreItem(
        AppDestination.PRAYERS, Icons.Default.VolunteerActivism,
        "Prayers & novenas", "Oraciones y novenas", "Orações e novenas",
        "Daily prayer, the Angelus and novenas", "Oración diaria, Ángelus y novenas", "Oração diária, Angelus e novenas"
    ),
    ExploreItem(
        AppDestination.PILGRIMAGE_HABITS, Icons.Default.Place,
        "Shrines & pilgrimage", "Santuarios y peregrinación", "Santuários e peregrinação",
        "Discover sacred places", "Descubre lugares sagrados", "Descubra lugares sagrados"
    ),
    ExploreItem(
        AppDestination.DOCTRINE, Icons.Default.Church,
        "Church & doctrine", "Iglesia y doctrina", "Igreja e doutrina",
        "Tradition, history and parishes", "Tradición, historia y parroquias", "Tradição, história e paróquias"
    ),
    ExploreItem(
        AppDestination.CHURCHES_NEWS, Icons.Default.Newspaper,
        "Church news", "Noticias de la Iglesia", "Notícias da Igreja",
        "Churches, resources and updates", "Iglesias, recursos y novedades", "Igrejas, recursos e novidades"
    ),
    ExploreItem(
        AppDestination.SPIRITUAL_LIFE, Icons.Default.Star,
        "Journal & examination", "Diario y examen", "Diário e exame",
        "Reflect, track progress and play quizzes", "Reflexiona, registra avances y juega", "Reflita, acompanhe o progresso e responda quizzes"
    )
)

private fun localized(language: AppLanguage, en: String, es: String, pt: String): String = when (language) {
    AppLanguage.EN -> en
    AppLanguage.ES -> es
    AppLanguage.PT -> pt
}

@Composable
fun MoreScreen(
    currentLanguage: AppLanguage,
    themePreset: String,
    onNavigate: (AppDestination) -> Unit
) {
    val isMaterial = AppThemePreset.normalizeId(themePreset) == AppThemePreset.MATERIAL.id
    val isApple = AppThemePreset.normalizeId(themePreset) == AppThemePreset.APPLE.id
    val titleColor = MaterialTheme.colorScheme.onBackground

    if (isMaterial) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 152.dp),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                Column(modifier = Modifier.padding(bottom = 4.dp)) {
                    Text(
                        text = localized(currentLanguage, "Explore your faith", "Explora tu fe", "Explore sua fé"),
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = titleColor
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = localized(currentLanguage, "Tools for prayer, learning and daily practice", "Herramientas para orar, aprender y vivir la fe", "Ferramentas para oração, estudo e prática diária"),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            items(exploreItems) { item ->
                Card(
                    modifier = Modifier.fillMaxWidth().clickable { onNavigate(item.destination) },
                    shape = MaterialTheme.shapes.large,
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                        Surface(
                            shape = MaterialTheme.shapes.medium,
                            color = MaterialTheme.colorScheme.primaryContainer,
                            modifier = Modifier.size(44.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(item.icon, contentDescription = null, tint = MaterialTheme.colorScheme.onPrimaryContainer, modifier = Modifier.size(23.dp))
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = localized(currentLanguage, item.titleEn, item.titleEs, item.titlePt),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = titleColor
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            text = localized(currentLanguage, item.descriptionEn, item.descriptionEs, item.descriptionPt),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = if (isApple) 20.dp else 24.dp, vertical = 22.dp),
            verticalArrangement = Arrangement.spacedBy(if (isApple) 0.dp else 8.dp)
        ) {
            item {
                Column(modifier = Modifier.padding(bottom = 16.dp)) {
                    Text(
                        text = localized(currentLanguage, "Explore", "Explorar", "Explorar"),
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = if (isApple) FontWeight.SemiBold else FontWeight.Bold,
                        color = titleColor
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = localized(currentLanguage, "More ways to pray, study and live the faith.", "Más formas de orar, estudiar y vivir la fe.", "Mais formas de rezar, estudar e viver a fé."),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            items(exploreItems.size) { index ->
                val item = exploreItems[index]
                if (isApple) {
                    Row(
                        modifier = Modifier.fillMaxWidth().clickable { onNavigate(item.destination) }.padding(vertical = 13.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            shape = MaterialTheme.shapes.small,
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            modifier = Modifier.size(44.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(item.icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(22.dp))
                            }
                        }
                        Spacer(modifier = Modifier.width(14.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = localized(currentLanguage, item.titleEn, item.titleEs, item.titlePt),
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Medium,
                                color = titleColor
                            )
                            Spacer(modifier = Modifier.height(3.dp))
                            Text(
                                text = localized(currentLanguage, item.descriptionEn, item.descriptionEs, item.descriptionPt),
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        Text("›", color = MaterialTheme.colorScheme.onSurfaceVariant, style = MaterialTheme.typography.headlineSmall)
                    }
                    if (index != exploreItems.lastIndex) {
                        HorizontalDivider(
                            modifier = Modifier.padding(start = 58.dp),
                            color = MaterialTheme.colorScheme.outline.copy(alpha = 0.42f),
                            thickness = 0.5.dp
                        )
                    }
                } else {
                    Row(
                        modifier = Modifier.fillMaxWidth().clickable { onNavigate(item.destination) }.padding(vertical = 14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            shape = MaterialTheme.shapes.extraSmall,
                            color = MaterialTheme.colorScheme.primaryContainer,
                            modifier = Modifier.size(40.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(item.icon, contentDescription = null, tint = MaterialTheme.colorScheme.onPrimaryContainer, modifier = Modifier.size(21.dp))
                            }
                        }
                        Spacer(modifier = Modifier.width(14.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = localized(currentLanguage, item.titleEn, item.titleEs, item.titlePt),
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.SemiBold,
                                color = titleColor
                            )
                            Spacer(modifier = Modifier.height(3.dp))
                            Text(
                                text = localized(currentLanguage, item.descriptionEn, item.descriptionEs, item.descriptionPt),
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                    if (index != exploreItems.lastIndex) {
                        HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.55f), thickness = 1.dp)
                    }
                }
            }
        }
    }
}
