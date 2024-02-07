package com.jaegerapps.trivitro_calculator.android.presentation.calculator_screen.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jaegerapps.trivitro_calculator.android.R
import com.jaegerapps.trivitro_calculator.android.TrivitroTheme
import com.jaegerapps.trivitro_calculator.android.core.theme.SeaGreen
import com.jaegerapps.trivitro_calculator.android.presentation.components.boxShadow
import com.jaegerapps.trivitro_calculator.shared.domain.models.PoolFilter

@Composable
fun StatDisplay(
    text: String,
    subtext: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .boxShadow()
            .background(MaterialTheme.colors.background),
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = text,
                    style = MaterialTheme.typography.h2,
                    fontWeight = FontWeight.Normal
                )
            }
            Box(
                modifier = Modifier
                    .background(SeaGreen)
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = subtext,
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onSecondary
                )
            }
        }
    }
}

@Preview
@Composable
fun StatDisplayPreview() {
    TrivitroTheme {
        Column(
            modifier = Modifier
                .padding(19.dp)
                .background(MaterialTheme.colors.background),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(25.dp)
        ) {
            StatDisplay(
                text = "175", subtext = "Recommended Sand Load Lbs."
            )
            StatDisplay(
                text = "140", subtext = "Recommended Vitroclean (VFA) Load Lbs."
            )
            StatDisplay(
                text = "0", subtext = "Recommended Vitroclean Pebble (VF8) Load Lbs."
            )
            StatDisplay(
                text = "3", subtext = "50 lb. Bags of Vitroclean VFA"
            )
            StatDisplay(
                text = "0", subtext = "50 lb. Bags of Pebble (VF8)."
            )
        }
    }
}
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun StatDisplayPreview_DARK() {
    TrivitroTheme {
        Column(
            modifier = Modifier
                .padding(19.dp)
                .background(MaterialTheme.colors.background),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(25.dp)
        ) {
            StatDisplay(
                text = "175", subtext = "Recommended Sand Load Lbs."
            )
            StatDisplay(
                text = "140", subtext = "Recommended Vitroclean (VFA) Load Lbs."
            )
            StatDisplay(
                text = "0", subtext = "Recommended Vitroclean Pebble (VF8) Load Lbs."
            )
            StatDisplay(
                text = "3", subtext = "50 lb. Bags of Vitroclean VFA"
            )
            StatDisplay(
                text = "0", subtext = "50 lb. Bags of Pebble (VF8)."
            )
        }
    }
}

@Composable
fun StatDisplayColumn(selectedFilter: PoolFilter) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(1f).testTag("stat display"),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(25.dp)
    ) {
        if (selectedFilter.model != "" && selectedFilter.manufacturer != "") {
            item {
                Spacer(Modifier.height(18.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = selectedFilter.model,
                        style = MaterialTheme.typography.h2.copy(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp
                        )
                    )
                    Text(
                        text = selectedFilter.manufacturer,
                        style = MaterialTheme.typography.body1.copy(
                            fontSize = 16.sp
                        )
                    )
                }
            }
        } else {
            item {
                Spacer(Modifier.height(18.dp))
            }
        }

        item {
            StatDisplay(
                text = selectedFilter.recommendedSandLoad.toString(),
                subtext = stringResource(R.string.recommended_sand_load_lbs)
            )
        }
        item {
            StatDisplay(
                text = selectedFilter.recommendedVitrocleanVfaLoad.toString(),
                subtext = stringResource(R.string.recommended_vitroclean_vfa_load_lbs)
            )
        }
        item {
            StatDisplay(
                text = selectedFilter.recommendedPebble.toString(),
                subtext = stringResource(R.string.recommended_vitroclean_pebble_vf8_load_lbs)
            )
        }
        item {
            StatDisplay(
                text = selectedFilter.fiftyBagVitroclean.toString(),
                subtext = stringResource(R.string.recommended_50_lb_bags_of_vitroclean_vfa)
            )
        }
        item {
            StatDisplay(
                text = selectedFilter.fiftyBagPebble.toString(),
                subtext = stringResource(R.string.recommended_50_lb_bags_of_pebble_vf8)
            )
            Spacer(Modifier.height(18.dp))
        }
    }
}