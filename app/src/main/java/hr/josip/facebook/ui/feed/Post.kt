package hr.josip.facebook.ui.feed

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import hr.josip.facebook.R
import hr.josip.facebook.data.model.feed.response.Comment
import hr.josip.facebook.data.model.feed.response.Post
import hr.josip.facebook.shared.manager.user.UserManager
import hr.josip.facebook.ui.common.GlideImage
import hr.josip.facebook.ui.common.Input
import hr.josip.facebook.ui.common.UserPicture
import hr.josip.facebook.ui.shared.compose.*
import java.text.SimpleDateFormat
import java.util.*

const val POST_DATE_FORMAT_PATTERN = "MMM dd HH:mm"

@Composable
fun PostItem(
    post: Post,
    userManager: UserManager,
    onClick: (Post) -> Unit,
    onLikeClicked: (Post) -> Unit,
    onAddComment: (Post, String) -> Unit,
    onShareClicked: (Post) -> Unit
) {
    Surface(
        color = MaterialTheme.colors.surface,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .clickable(onClick = { onClick.invoke(post) })
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            UserDetails(post)
            PostContent(post)
            ReactionsContent(post, userManager, onLikeClicked, onAddComment, onShareClicked)
        }
    }
}

@Composable
private fun UserDetails(post: Post) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        UserPicture(post.user)
        Column(
            modifier = Modifier.fillMaxWidth().padding(start = 8.dp),
        ) {
            Text(
                text = post.user.name,
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onSurface
            )
            Text(
                text = SimpleDateFormat(POST_DATE_FORMAT_PATTERN, Locale.ENGLISH).format(post.date),
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.onSurface
            )
        }
    }
}



@Composable
private fun PostContent(post: Post) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = post.text,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(vertical = 8.dp),
            color = MaterialTheme.colors.onSurface
        )
        post.imageUrl?.let { imageUrl ->
            GlideImage(
                model = imageUrl,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
        }
    }
}

@Composable
private fun ReactionsContent(
    post: Post,
    userManager: UserManager,
    onLikeClicked: (Post) -> Unit,
    onAddComment: (Post, String) -> Unit,
    onShareClicked: (Post) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth().padding(top = 4.dp)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Likes(post, onLikeClicked)
            Comments(post)
            Share(post, onShareClicked)
        }
        PostComments(post)
        Input(
            user = userManager.getCurrentActiveUser(),
            hintLabel = stringResource(id = R.string.comment_hint),
            onSendClick = { onAddComment.invoke(post, it) }
        )
    }
}

@Composable
private fun Likes(post: Post, onLikeClicked: (Post) -> Unit) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(MaterialTheme.colors.surface)
            .clickable(onClick = { onLikeClicked.invoke(post) })
    ) {
        Row(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
            Image(
                vectorResource(id = R.drawable.ic_thumb_up),
                modifier = Modifier.preferredSize(24.dp).align(Alignment.CenterVertically),
                colorFilter = if (post.isLikedByCurrentUser) ColorFilter.tint(blue) else ColorFilter.tint(
                    MaterialTheme.colors.onSurface
                )
            )
            Text(
                text = post.likes.toString(),
                style = MaterialTheme.typography.body2,
                color = if (post.isLikedByCurrentUser) blue else MaterialTheme.colors.onSurface,
                modifier = Modifier.padding(start = 8.dp).align(Alignment.CenterVertically)
            )
        }
    }

}

@Composable
private fun Comments(post: Post) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(MaterialTheme.colors.surface)
    ) {
        Row(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
            Image(
                vectorResource(id = R.drawable.ic_comment),
                modifier = Modifier.preferredSize(24.dp).align(Alignment.CenterVertically),
                colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface)
            )
            Text(
                text = post.comments.size.toString(),
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onSurface,
                modifier = Modifier.padding(start = 8.dp).align(Alignment.CenterVertically)
            )
        }
    }
}

@Composable
private fun Share(post: Post, onShareClicked: (Post) -> Unit) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(MaterialTheme.colors.surface)
            .clickable(onClick = { onShareClicked.invoke(post) })
    ) {
        Row(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
            Image(
                vectorResource(id = R.drawable.ic_share),
                modifier = Modifier.preferredSize(24.dp).align(Alignment.CenterVertically),
                colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface)
            )
            Text(
                text = post.shares.toString(),
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onSurface,
                modifier = Modifier.padding(start = 8.dp).align(Alignment.CenterVertically)
            )
        }
    }
}

@Composable
private fun PostComments(post: Post) {
    Column(modifier = Modifier.fillMaxSize().padding(horizontal = 8.dp)) {
        post.comments.forEach {
            AddPostComment(it)
        }
    }
}

@Composable
private fun AddPostComment(comment: Comment) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        UserPicture(comment.user)
        Card(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 8.dp),
            backgroundColor = MaterialTheme.colors.secondary,
            elevation = 0.dp
        ) {
            Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                Row {
                    Text(
                        text = comment.user.name,
                        style = MaterialTheme.typography.subtitle1,
                        color = blue,
                    )
                    Text(
                        modifier = Modifier.padding(start = 8.dp, top = 3.dp),
                        text = SimpleDateFormat(POST_DATE_FORMAT_PATTERN, Locale.ENGLISH).format(comment.date),
                        style = MaterialTheme.typography.caption,
                        color = MaterialTheme.colors.onSecondary
                    )
                }
                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = comment.text,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onSecondary
                )
            }
        }
    }
}