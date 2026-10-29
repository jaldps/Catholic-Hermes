import pathlib, sys

TARGET = pathlib.Path(r"C:\Users\Edgli\AppData\Local\hermes\cache\scratch\Catholic-final\app\src\main\java\com\example\ui\screens\NightPrayersScreen.kt")
content = TARGET.read_text(encoding="utf-8")

repls = [
    # imports
    ("import androidx.compose.material3.Text\n",
     "import androidx.compose.material3.Text\nimport androidx.compose.material3.TextAlign\n"),
    ("import androidx.compose.ui.text.font.FontFamily\n",
     ""),

    # Tab bar: icon size + spacing + typography
    ('Icon(Icons.Default.NightsStay, contentDescription = null, modifier = Modifier.size(16.dp))',
     'Icon(Icons.Default.NightsStay, contentDescription = null, modifier = Modifier.size(MaterialTheme.identity.navIconSize))'),
    ('Spacer(modifier = Modifier.width(6.dp))\n                        Text(\n                            text = when (currentLanguage) {\n                                AppLanguage.ES -> "Completas (Breviario)"\n                                AppLanguage.EN -> "Roman Compline"\n                                AppLanguage.PT -> "Completas (Breviário)"\n                            },\n                            fontSize = 12.sp,\n                            fontWeight = if (selectedNightOfficeTab == 0) FontWeight.Bold else FontWeight.Normal',
     'Spacer(modifier = Modifier.width(MaterialTheme.spacing.xs))\n                        Text(\n                            text = when (currentLanguage) {\n                                AppLanguage.ES -> "Completas (Breviario)"\n                                AppLanguage.EN -> "Roman Compline"\n                                AppLanguage.PT -> "Completas (Breviário)"\n                            },\n                            style = MaterialTheme.typography.labelSmall,\n                            fontWeight = if (selectedNightOfficeTab == 0) FontWeight.Bold else FontWeight.Normal'),
    ('text = when (currentLanguage) {\n                            AppLanguage.ES -> "Examen Ignaciano"\n                            AppLanguage.EN -> "Ignatian Examen"\n                            AppLanguage.PT -> "Exame Inaciano"\n                        },\n                        fontSize = 12.sp,\n                        fontWeight = if (selectedNightOfficeTab == 1) FontWeight.Bold else FontWeight.Normal',
     'text = when (currentLanguage) {\n                            AppLanguage.ES -> "Examen Ignaciano"\n                            AppLanguage.EN -> "Ignatian Examen"\n                            AppLanguage.PT -> "Exame Inaciano"\n                        },\n                        style = MaterialTheme.typography.labelSmall,\n                        fontWeight = if (selectedNightOfficeTab == 1) FontWeight.Bold else FontWeight.Normal'),

    # ComplineView LazyColumn padding
    ('.padding(16.dp),\n        verticalArrangement = Arrangement.spacedBy(16.dp)',
     '.padding(MaterialTheme.spacing.lg),\n        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.lg)'),

    # Header ElevatedCard - shape + padding
    ('shape = RoundedCornerShape(18.dp),\n                colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.45f))\n            ) {\n                Column(modifier = Modifier.padding(18.dp)) {',
     'shape = MaterialTheme.shapes.large,\n                colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.45f))\n            ) {\n                Column(modifier = Modifier.padding(MaterialTheme.spacing.lg)) {'),
    # Header title typography
    ('fontWeight = FontWeight.Bold,\n                                fontSize = 16.sp,\n                                color = MaterialTheme.colorScheme.primary\n                            )\n                            Text(\n                                text = "Liturgia oficial de la Iglesia antes del reposo de la noche",\n                                fontSize = 12.sp,',
     'style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),\n                                color = MaterialTheme.colorScheme.primary\n                            )\n                            Text(\n                                text = "Liturgia oficial de la Iglesia antes del reposo de la noche",\n                                style = MaterialTheme.typography.bodySmall,'),
    # Header button color + icon + text
    ('contentColor = Color.White\n                            )\n                        ) {\n                            Icon(Icons.Default.VolumeUp, contentDescription = null, modifier = Modifier.size(16.dp))\n                            Spacer(modifier = Modifier.width(6.dp))\n                            Text("Rezar", fontSize = 12.sp)',
     'contentColor = MaterialTheme.colorScheme.onPrimary\n                            )\n                        ) {\n                            Icon(Icons.Default.VolumeUp, contentDescription = null, modifier = Modifier.size(MaterialTheme.identity.actionIconSize))\n                            Spacer(modifier = Modifier.width(MaterialTheme.spacing.xs))\n                            Text("Rezar", style = MaterialTheme.typography.labelSmall)'),

    # Antiphons ElevatedCard
    ('shape = RoundedCornerShape(16.dp),\n                colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surface)\n            ) {\n                Column(modifier = Modifier.padding(16.dp)) {',
     'shape = MaterialTheme.shapes.medium,\n                colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surface)\n            ) {\n                Column(modifier = Modifier.padding(MaterialTheme.spacing.md)) {'),
    # Antiphons header text
    ('fontWeight = FontWeight.Bold,\n                        fontSize = 11.sp,\n                        color = MaterialTheme.colorScheme.secondary,\n                        letterSpacing = 1.sp',
     'style = MaterialTheme.typography.labelSmall.copy(\n                            fontWeight = FontWeight.Bold,\n                            letterSpacing = MaterialTheme.identity.titleTracking\n                        ),\n                        color = MaterialTheme.colorScheme.secondary'),
    # Antiphons LazyRow spacing
    ('Spacer(modifier = Modifier.height(8.dp))\n\n                    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp))',
     'Spacer(modifier = Modifier.height(MaterialTheme.spacing.sm))\n\n                    LazyRow(horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.sm))'),
    ('label = { Text(antiphon.titleLatin, fontSize = 12.sp) }',
     'label = { Text(antiphon.titleLatin, style = MaterialTheme.typography.bodySmall) }'),
    # Antiphons title + season text
    ('Spacer(modifier = Modifier.height(12.dp))\n\n                    Text(\n                        text = currentAntiphon.titleTranslation.get(currentLanguage),\n                        fontWeight = FontWeight.Bold,\n                        fontSize = 14.sp,',
     'Spacer(modifier = Modifier.height(MaterialTheme.spacing.md))\n\n                    Text(\n                        text = currentAntiphon.titleTranslation.get(currentLanguage),\n                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),'),
    ('fontSize = 11.sp,\n                        color = MaterialTheme.colorScheme.onSurfaceVariant\n                    )\n\n                    Spacer(modifier = Modifier.height(10.dp))\n\n                    Text(\n                        text = currentAntiphon.latinText,\n                        fontFamily = FontFamily.Serif,\n                        fontSize = 13.sp,\n                        lineHeight = 20.sp,',
     'style = MaterialTheme.typography.bodySmall,\n                        color = MaterialTheme.colorScheme.onSurfaceVariant\n                    )\n\n                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.sm))\n\n                    Text(\n                        text = currentAntiphon.latinText,\n                        style = MaterialTheme.typography.bodySmall.copy(lineHeight = MaterialTheme.spacing.lg),'),
    ('Spacer(modifier = Modifier.height(10.dp))\n\n                    Text(\n                        text = currentAntiphon.translatedText.get(currentLanguage),\n                        fontFamily = FontFamily.Serif,\n                        fontSize = 13.sp,\n                        lineHeight = 22.sp,',
     'Spacer(modifier = Modifier.height(MaterialTheme.spacing.sm))\n\n                    Text(\n                        text = currentAntiphon.translatedText.get(currentLanguage),\n                        style = MaterialTheme.typography.bodySmall.copy(lineHeight = MaterialTheme.spacing.xl),'),
    # Antiphons listen button
    ('Spacer(modifier = Modifier.height(12.dp))\n\n                    ElevatedButton(\n                        onClick = {\n                            ttsManager.speakSingle(currentAntiphon.translatedText.get(currentLanguage))\n                        },\n                        colors = ButtonDefaults.elevatedButtonColors(\n                            containerColor = MaterialTheme.colorScheme.primary,\n                            contentColor = Color.White\n                        )\n                    ) {\n                        Icon(Icons.Default.VolumeUp, contentDescription = null, modifier = Modifier.size(16.dp))\n                        Spacer(modifier = Modifier.width(6.dp))\n                        Text("Escuchar ${currentAntiphon.titleLatin}", fontSize = 12.sp)',
     'Spacer(modifier = Modifier.height(MaterialTheme.spacing.md))\n\n                    ElevatedButton(\n                        onClick = {\n                            ttsManager.speakSingle(currentAntiphon.translatedText.get(currentLanguage))\n                        },\n                        colors = ButtonDefaults.elevatedButtonColors(\n                            containerColor = MaterialTheme.colorScheme.primary,\n                            contentColor = MaterialTheme.colorScheme.onPrimary\n                        )\n                    ) {\n                        Icon(Icons.Default.VolumeUp, contentDescription = null, modifier = Modifier.size(MaterialTheme.identity.actionIconSize))\n                        Spacer(modifier = Modifier.width(MaterialTheme.spacing.xs))\n                        Text("Escuchar ${currentAntiphon.titleLatin}", style = MaterialTheme.typography.labelSmall)'),

    # ComplineCard
    ('shape = RoundedCornerShape(16.dp),\n        colors = CardDefaults.cardColors(\n            containerColor = if (isHighlighted) MaterialTheme.colorScheme.primary.copy(alpha = 0.08f) else MaterialTheme.colorScheme.surface\n        ),',
     'shape = MaterialTheme.shapes.medium,\n        colors = CardDefaults.cardColors(\n            containerColor = if (isHighlighted) MaterialTheme.colorScheme.primary.copy(alpha = 0.08f) else MaterialTheme.colorScheme.surface\n        ),'),
    ('Column(modifier = Modifier.padding(16.dp)) {',
     'Column(modifier = Modifier.padding(MaterialTheme.spacing.md)) {'),
    ('text = stepLabel,\n                    fontWeight = FontWeight.Bold,\n                    fontSize = 11.sp,\n                    color = MaterialTheme.colorScheme.secondary,\n                    letterSpacing = 1.sp',
     'text = stepLabel,\n                    style = MaterialTheme.typography.labelSmall.copy(\n                        fontWeight = FontWeight.Bold,\n                        letterSpacing = MaterialTheme.identity.titleTracking\n                    ),\n                    color = MaterialTheme.colorScheme.secondary'),
    ('text = if (showLatin) "Ocultar Latín" else "Ver Latín",\n                    fontSize = 11.sp,\n                    fontWeight = FontWeight.Medium,',
     'text = if (showLatin) "Ocultar Latín" else "Ver Latín",\n                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Medium),'),
    ('Spacer(modifier = Modifier.height(6.dp))\n\n            Text(\n                text = latinTitle,\n                fontWeight = FontWeight.Bold,\n                fontSize = 14.sp,',
     'Spacer(modifier = Modifier.height(MaterialTheme.spacing.xs))\n\n            Text(\n                text = latinTitle,\n                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),'),
    ('Spacer(modifier = Modifier.height(8.dp))\n                    Text(\n                        text = latinText,\n                        fontFamily = FontFamily.Serif,\n                        fontSize = 12.sp,\n                        lineHeight = 18.sp,',
     'Spacer(modifier = Modifier.height(MaterialTheme.spacing.sm))\n                    Text(\n                        text = latinText,\n                        style = MaterialTheme.typography.bodySmall.copy(lineHeight = MaterialTheme.spacing.md),'),
    ('Spacer(modifier = Modifier.height(10.dp))\n\n            Text(\n                text = translatedText,\n                fontFamily = FontFamily.Serif,\n                fontSize = 13.sp,\n                lineHeight = 22.sp,',
     'Spacer(modifier = Modifier.height(MaterialTheme.spacing.sm))\n\n            Text(\n                text = translatedText,\n                style = MaterialTheme.typography.bodySmall.copy(lineHeight = MaterialTheme.spacing.xl),'),

    # IgnatianExamenView padding
    ('.padding(16.dp),\n        verticalArrangement = Arrangement.spacedBy(16.dp)',
     '.padding(MaterialTheme.spacing.lg),\n        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.lg)'),

    # IgnatianExamenView header card
    ('shape = RoundedCornerShape(18.dp),\n                colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)\n            ) {\n                Column(modifier = Modifier.padding(18.dp)) {',
     'shape = MaterialTheme.shapes.large,\n                colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)\n            ) {\n                Column(modifier = Modifier.padding(MaterialTheme.spacing.lg)) {'),
    ('fontWeight = FontWeight.Bold,\n                        fontSize = 16.sp,\n                        color = MaterialTheme.colorScheme.primary\n                    )\n                    Spacer(modifier = Modifier.height(6.dp))\n                    Text(\n                        text = "5 pasos para revisar el día con sinceridad ante Dios antes de dormir y recibir la paz de Cristo.",\n                        fontSize = 12.sp,\n                        lineHeight = 18.sp,',
     'style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),\n                        color = MaterialTheme.colorScheme.primary\n                    )\n                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.xs))\n                    Text(\n                        text = "5 pasos para revisar el día con sinceridad ante Dios antes de dormir y recibir la paz de Cristo.",\n                        style = MaterialTheme.typography.bodySmall,\n                        lineHeight = MaterialTheme.spacing.md,'),
    ('Spacer(modifier = Modifier.height(12.dp))\n\n                    val progressPercent',
     'Spacer(modifier = Modifier.height(MaterialTheme.spacing.md))\n\n                    val progressPercent'),
    ('fontSize = 11.sp,\n                        fontWeight = FontWeight.Bold,\n                        color = MaterialTheme.colorScheme.secondary',
     'style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),\n                        color = MaterialTheme.colorScheme.secondary'),

    # Step Card
    ('shape = RoundedCornerShape(16.dp),\n                colors = CardDefaults.cardColors(\n                    containerColor = if (isStepDone) MaterialTheme.colorScheme.primary.copy(alpha = 0.05f) else MaterialTheme.colorScheme.surface\n                ),',
     'shape = MaterialTheme.shapes.medium,\n                colors = CardDefaults.cardColors(\n                    containerColor = if (isStepDone) MaterialTheme.colorScheme.primary.copy(alpha = 0.05f) else MaterialTheme.colorScheme.surface\n                ),'),
    ('Column(modifier = Modifier.padding(16.dp)) {',
     'Column(modifier = Modifier.padding(MaterialTheme.spacing.md)) {'),
    ('fontWeight = FontWeight.Bold,\n                            fontSize = 14.sp,',
     'style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),'),
    ('modifier = Modifier.size(16.dp))\n                            } else {\n                                Text("Hecho", fontSize = 11.sp)',
     'modifier = Modifier.size(MaterialTheme.identity.actionIconSize))\n                            } else {\n                                Text("Hecho", style = MaterialTheme.typography.labelSmall)'),
    ('Spacer(modifier = Modifier.height(8.dp))\n\n                    Text(\n                        text = step.description.get(currentLanguage),\n                        fontSize = 12.sp,\n                        lineHeight = 18.sp,',
     'Spacer(modifier = Modifier.height(MaterialTheme.spacing.sm))\n\n                    Text(\n                        text = step.description.get(currentLanguage),\n                        style = MaterialTheme.typography.bodySmall,\n                        lineHeight = MaterialTheme.spacing.md,'),
    ('Spacer(modifier = Modifier.height(10.dp))\n\n                    Surface(\n                        shape = RoundedCornerShape(10.dp),',
     'Spacer(modifier = Modifier.height(MaterialTheme.spacing.sm))\n\n                    Surface(\n                        shape = MaterialTheme.shapes.small,'),
    ('Column(modifier = Modifier.padding(12.dp)) {',
     'Column(modifier = Modifier.padding(MaterialTheme.spacing.md)) {'),
    ('text = "Oración sugerida:",\n                            fontWeight = FontWeight.Bold,\n                            fontSize = 11.sp,',
     'text = "Oración sugerida:",\n                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),'),
    ('Spacer(modifier = Modifier.height(4.dp))\n                            Text(\n                                text = step.guidedPrayer.get(currentLanguage),\n                                fontFamily = FontFamily.Serif,\n                                fontSize = 13.sp,\n                                lineHeight = 20.sp,',
     'Spacer(modifier = Modifier.height(MaterialTheme.spacing.xs))\n                            Text(\n                                text = step.guidedPrayer.get(currentLanguage),\n                                style = MaterialTheme.typography.bodySmall.copy(lineHeight = MaterialTheme.spacing.lg),'),
    ('Spacer(modifier = Modifier.height(10.dp))\n\n                    Row(',
     'Spacer(modifier = Modifier.height(MaterialTheme.spacing.sm))\n\n                    Row('),
    ('modifier = Modifier.size(14.dp))\n                            Spacer(modifier = Modifier.width(6.dp))\n                            Text("Escuchar", fontSize = 11.sp)',
     'modifier = Modifier.size(MaterialTheme.identity.actionIconSize))\n                            Spacer(modifier = Modifier.width(MaterialTheme.spacing.xs))\n                            Text("Escuchar", style = MaterialTheme.typography.labelSmall)'),
    ('colors = ButtonDefaults.elevatedButtonColors(\n                                containerColor = MaterialTheme.colorScheme.primary,\n                                contentColor = Color.White\n                            )',
     'colors = ButtonDefaults.elevatedButtonColors(\n                                containerColor = MaterialTheme.colorScheme.primary,\n                                contentColor = MaterialTheme.colorScheme.onPrimary\n                            )'),

    # Completion ElevatedCard
    ('shape = RoundedCornerShape(16.dp),\n                colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.primary)\n            ) {\n                Column(\n                        modifier = Modifier\n                            .fillMaxWidth()\n                            .padding(20.dp),',
     'shape = MaterialTheme.shapes.medium,\n                colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.primary)\n            ) {\n                Column(\n                        modifier = Modifier\n                            .fillMaxWidth()\n                            .padding(MaterialTheme.spacing.xl),'),
    ('modifier = Modifier.size(36.dp))\n                        Spacer(modifier = Modifier.height(8.dp))\n                        Text("¡Examen Completado!", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.White)',
     'modifier = Modifier.size(MaterialTheme.identity.actionIconSize * 2))\n                        Spacer(modifier = Modifier.height(MaterialTheme.spacing.sm))\n                        Text("¡Examen Completado!", style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold), color = MaterialTheme.colorScheme.onPrimary)'),
    ('Spacer(modifier = Modifier.height(4.dp))\n                        Text(\n                            text = "Descansa en la paz y el perdón de Nuestro Señor Jesucristo. Que la Virgen María y los santos Ángeles custodien tu sueño.",\n                            fontSize = 12.sp,\n                            color = Color.White.copy(alpha = 0.9f),\n                            textAlign = androidx.compose.ui.text.style.TextAlign.Center',
     'Spacer(modifier = Modifier.height(MaterialTheme.spacing.xs))\n                        Text(\n                            text = "Descansa en la paz y el perdón de Nuestro Señor Jesucristo. Que la Virgen María y los santos Ángeles custodien tu sueño.",\n                            style = MaterialTheme.typography.bodySmall,\n                            color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.9f),\n                            textAlign = TextAlign.Center'),
]

missing = []
applied = 0
for i, (old, new) in enumerate(repls):
    if old not in content:
        missing.append(f"[{i}] {old[:80]}")
    else:
        content = content.replace(old, new, 1)
        applied += 1

TARGET.write_text(content, encoding="utf-8")
print(f"Applied: {applied}/{len(repls)} replacements")
if missing:
    print("MISSING patches:")
    for m in missing:
        print(" ", m)
else:
    print("All patches matched.")

print(f"File: {len(content)} bytes, {content.count(chr(10))} lines")

assert "fontSize =" not in content, "fontSize remains"
assert "fontFamily = FontFamily.Serif" not in content, "FontFamily.Serif remains"
assert "Color.White" not in content, "Color.White remains"
assert "MaterialTheme.spacing" in content
assert "MaterialTheme.shapes" in content
assert "MaterialTheme.typography" in content
assert "MaterialTheme.identity" in content
assert "import androidx.compose.material3.TextAlign" in content
print("All assertions passed.")
