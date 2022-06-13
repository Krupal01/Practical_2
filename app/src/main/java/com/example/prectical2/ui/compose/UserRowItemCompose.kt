package com.example.prectical2.ui.compose

import android.annotation.SuppressLint
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.prectical2.R
import com.example.prectical2.model.ItemsItem
import com.google.accompanist.coil.rememberCoilPainter

@Composable
fun UserRowItem(
    itemsItem: ItemsItem,
    onCardClick : () -> Unit,
    isSaved: Boolean,
    onCheckedChange : (Boolean) ->Unit
){
    Card(
        modifier = Modifier
            .padding(all = 3.dp)
            .fillMaxWidth()
            .clickable(onClick = onCardClick),
        shape = RoundedCornerShape(5.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 10.dp)
        ) {
            val image = rememberCoilPainter(request = itemsItem.profileImage , fadeIn = true)
            Image(painter = image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(80.dp)
                    .width(80.dp)
                    .clip(shape = RoundedCornerShape(5.dp))
                    .align(alignment = Alignment.CenterVertically)
            )
            Column(modifier = Modifier
                .weight(1F)
                .align(alignment = Alignment.CenterVertically)) {
                Text(text = itemsItem.displayName.toString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 3.dp, horizontal = 10.dp),
                    fontSize = 20.sp,
                    maxLines = 2,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    overflow = TextOverflow.Ellipsis
                )
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 3.dp, horizontal = 10.dp)) {
                    Text(
                        text = itemsItem.getPrefixReputation().toString(),
                        color = Color.Black,
                        modifier = Modifier.padding(horizontal = 3.dp),
                        fontSize = 14.sp,
                    )
                    BadgeCounts(
                        colorResourceId = R.color.gold,
                        badge = itemsItem.getPrefixBadgeGold().toString(),
                        modifier = Modifier.padding(horizontal = 3.dp)
                    )
                    BadgeCounts(
                        colorResourceId = R.color.silver,
                        badge = itemsItem.getPrefixBadgeSilver().toString(),
                        modifier = Modifier.padding(horizontal = 3.dp)
                    )
                    BadgeCounts(
                        colorResourceId = R.color.bronze,
                        badge = itemsItem.getPrefixBadgeBronze().toString(),
                        modifier = Modifier.padding(horizontal = 3.dp)
                    )

                }
            }

            BookMarkButton(isChecked = isSaved , onCheckedChange = onCheckedChange )

        }

    }
}

@Composable
fun BadgeCounts(colorResourceId : Int, badge: String , modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.ic_baseline_circle_16),
            colorFilter = ColorFilter.tint(colorResource(id = colorResourceId)),
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(100))
        )
        Text(
            text = badge,
            color = Color.Black,
            fontSize = 14.sp,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun BookMarkButton(
    isChecked : Boolean,
    onCheckedChange : (Boolean) -> Unit
){
    IconToggleButton(
        checked = isChecked,
        onCheckedChange = onCheckedChange
    ) {
        val tint = if (isChecked) Color.Yellow else Color.Gray
        Icon(
            imageVector = if (isChecked) Icons.Filled.Star else Icons.Filled.Star,
            contentDescription = null,
            tint = tint
        )
    }
}

