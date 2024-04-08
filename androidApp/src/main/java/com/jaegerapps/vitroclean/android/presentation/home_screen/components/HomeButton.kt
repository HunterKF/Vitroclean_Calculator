package com.jaegerapps.vitroclean.android.presentation.home_screen.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jaegerapps.vitroclean.android.TrivitroTheme
import com.jaegerapps.vitroclean.android.presentation.components.boxShadow

/*Home Button is for displaying navigation buttons to the user from the home screen
* They take an icon and a string to show where the user is navigating to*/
@Composable
fun HomeButton(
    text: String,
    icon: Int,
    onClick: () -> Unit,
    contentDescription: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.primary,
) {
    Box(modifier = modifier
        .boxShadow()
        .background(MaterialTheme.colors.background)
        .clickable { onClick() }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 18.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.padding(end = 18.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    tint = color,
                    modifier = Modifier
                        .size(32.dp),
                    contentDescription = contentDescription
                )
            }
            Box(
                modifier = Modifier.height(32.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = text,
                    style = MaterialTheme.typography.h3,
                    color = color
                )
            }
        }
    }
}

@Preview
@Composable
fun HomeButtonPreview() {
    TrivitroTheme {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colors.surface)
                .padding(horizontal = 12.dp, vertical = 16.dp)
        ) {
            HomeButton(
                text = "Calculate by filter",
                icon = com.jaegerapps.vitroclean.R.drawable.icon_calculator,
                onClick = {  },
                contentDescription = "go to calculate",
            )
            Spacer(Modifier.height(25.dp))
            HomeButton(
                text = "Calculate by cubic feet",
                icon = com.jaegerapps.vitroclean.R.drawable.icon_cube,
                onClick = {  },
                contentDescription = "go to calculate",
            )
            Spacer(Modifier.height(25.dp))
            HomeButton(
                text = "Calculate by sand",
                icon = com.jaegerapps.vitroclean.R.drawable.icon_sand,
                onClick = {  },
                contentDescription = "go to calculate",
            )
            Spacer(Modifier.height(25.dp))
            HomeButton(
                text = "FAQs",
                icon = com.jaegerapps.vitroclean.R.drawable.icon_question,
                onClick = {  },
                contentDescription = "go to calculate",
            )
            Spacer(Modifier.height(25.dp))
            HomeButton(
                text = "Contact Us",
                icon = com.jaegerapps.vitroclean.R.drawable.icon_message,
                onClick = {  },
                contentDescription = "go to calculate",
            )
        }
    }
}
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomeButtonPreview_DARK() {
    TrivitroTheme {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colors.surface)
                .padding(horizontal = 12.dp, vertical = 16.dp)
        ) {
            HomeButton(
                text = "Calculate by filter",
                icon = com.jaegerapps.vitroclean.R.drawable.icon_calculator,
                onClick = {  },
                contentDescription = "go to calculate",
            )
            Spacer(Modifier.height(25.dp))
            HomeButton(
                text = "Calculate by cubic feet",
                icon = com.jaegerapps.vitroclean.R.drawable.icon_cube,
                onClick = {  },
                contentDescription = "go to calculate",
            )
            Spacer(Modifier.height(25.dp))
            HomeButton(
                text = "Calculate by sand",
                icon = com.jaegerapps.vitroclean.R.drawable.icon_sand,
                onClick = {  },
                contentDescription = "go to calculate",
            )
            Spacer(Modifier.height(25.dp))
            HomeButton(
                text = "FAQs",
                icon = com.jaegerapps.vitroclean.R.drawable.icon_question,
                onClick = {  },
                contentDescription = "go to calculate",
            )
            Spacer(Modifier.height(25.dp))
            HomeButton(
                text = "Contact Us",
                icon = com.jaegerapps.vitroclean.R.drawable.icon_message,
                onClick = {  },
                contentDescription = "go to calculate",
            )
        }
    }
}