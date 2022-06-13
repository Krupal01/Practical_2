package com.example.prectical2.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.prectical2.R
import com.example.prectical2.model.ItemsItem
import com.google.accompanist.coil.rememberCoilPainter

@Composable
fun DetailScreen(
    itemsItem : ItemsItem
){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {Text(
                    text = "Account Id : ${itemsItem.accountId}",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )},
            )
        }
    )
    {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(all = 15.dp)
        ) {

            fun getLinkAnnotatedString(string: String): AnnotatedString {
                return buildAnnotatedString {

                    val mStartIndex = 0
                    val mEndIndex = string.length
                    append(string)
                    addStyle(
                        style = SpanStyle(
                            color = Color.Blue,
                            textDecoration = TextDecoration.Underline
                        ), start = mStartIndex, end = mEndIndex
                    )

                addStringAnnotation(
                    tag = "URL",
                    annotation = string,
                    start = 0,
                    end = mEndIndex
                )

                }
            }

            val (
                ProfileImage,
                UserName,
                IsEmployee,
                UserType,
                UserId,
                LocationLabel,
                LocationValue,
                LinkLabel,
                LinkValue,
                BadgeRow
            ) = createRefs()

            val image = rememberCoilPainter(request = itemsItem.profileImage , fadeIn = true)
            Image(
                painter = image,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .padding(all = 10.dp)
                    .height(150.dp)
                    .width(150.dp)
                    .clip(shape = RoundedCornerShape(100))
                    .constrainAs(ProfileImage) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )

            Text(
                text = itemsItem.displayName.toString(),
                modifier = Modifier
                    .constrainAs(UserName){
                        top.linkTo(ProfileImage.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 30.sp
            )

            Row(
                modifier = Modifier.padding(all = 5.dp).constrainAs(BadgeRow){
                    top.linkTo(UserName.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            ) {
                BadgeCounts(
                    colorResourceId = R.color.gold,
                    badge = itemsItem.getPrefixBadgeGold().toString(),
                    modifier = Modifier.padding(horizontal = 5.dp)
                )
                BadgeCounts(
                    colorResourceId = R.color.silver,
                    badge = itemsItem.getPrefixBadgeSilver().toString(),
                    modifier = Modifier.padding(horizontal = 5.dp)
                )
                BadgeCounts(
                    colorResourceId = R.color.bronze,
                    badge = itemsItem.getPrefixBadgeBronze().toString(),
                    modifier = Modifier.padding(horizontal = 5.dp)
                )
            }

            Text(
                text = "is employee : ${itemsItem.isEmployee}",
                modifier = Modifier
                    .padding(vertical = 15.dp, horizontal = 5.dp)
                    .constrainAs(IsEmployee) {
                        top.linkTo(BadgeRow.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                color = Color.Gray,
                fontSize = 18.sp
            )

            Text(
                text = "user type : ${itemsItem.userType}",
                modifier = Modifier
                    .padding(vertical = 15.dp, horizontal = 5.dp)
                    .constrainAs(UserType) {
                        top.linkTo(IsEmployee.bottom)
                        start.linkTo(parent.start)
                    },
                color = Color.Gray,
                fontSize = 18.sp
            )

            Text(
                text = "user Id : ${itemsItem.userId}",
                modifier = Modifier
                    .padding(vertical = 15.dp, horizontal = 5.dp)
                    .constrainAs(UserId) {
                        top.linkTo(IsEmployee.bottom)
                        end.linkTo(parent.end)
                    },
                color = Color.Gray,
                fontSize = 18.sp
            )

            Text(
                text = "Location : ",
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .constrainAs(LocationLabel) {
                        top.linkTo(UserType.bottom)
                        start.linkTo(parent.start)
                    },
                color = Color.Gray,
                fontSize = 18.sp
            )

            val locationLink = getLinkAnnotatedString(itemsItem.location.toString())
            ClickableText(
                text = locationLink,
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .constrainAs(LocationValue) {
                        top.linkTo(LocationLabel.top)
                        start.linkTo(LocationLabel.end)
                        bottom.linkTo(LocationLabel.bottom)
                    },
                onClick = {

                }
            )


            Text(
                text = "Link : ",
                modifier = Modifier
                    .padding(horizontal = 5.dp, vertical = 5.dp)
                    .constrainAs(LinkLabel) {
                        top.linkTo(LocationLabel.bottom)
                        start.linkTo(parent.start)
                    },
                color = Color.Gray,
                fontSize = 18.sp
            )

            val link = getLinkAnnotatedString(itemsItem.link.toString())
            ClickableText(
                text = link,
                modifier = Modifier
                    .padding(horizontal = 5.dp, vertical = 5.dp)
                    .fillMaxWidth(.8f)
                    .constrainAs(LinkValue) {
                        top.linkTo(LinkLabel.top)
                        start.linkTo(LinkLabel.end)
                    },
                onClick = {

                },
                overflow = TextOverflow.Ellipsis,
                maxLines = 3,
            )
        }
    }

}