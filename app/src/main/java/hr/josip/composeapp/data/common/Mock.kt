package hr.josip.composeapp.data.common

import hr.josip.composeapp.data.model.feed.response.Post
import hr.josip.composeapp.data.model.feed.response.Story

object Mock {

    fun getStories(): List<Story> {
        val stories = arrayListOf<Story>()
        stories.apply {
            add(
                Story(
                    id = 0,
                    user = User(
                        id = 0,
                        name = "",
                        avatarUrl = ""
                    ), isRead = true
                )
            )
            add(
                Story(
                    id = 1,
                    user = User(
                        id = 1,
                        name = "Android",
                        avatarUrl = "https://download.logo.wine/logo/Android_(operating_system)/Android_(operating_system)-Robot-Logo.wine.png",
                    ), isRead = false
                )
            )
            add(
                Story(
                    id = 2,
                    user = User(
                        id = 2,
                        name = "Studio",
                        avatarUrl = "https://1.bp.blogspot.com/-LgTa-xDiknI/X4EflN56boI/AAAAAAAAPuk/24YyKnqiGkwRS9-_9suPKkfsAwO4wHYEgCLcBGAsYHQ/s0/image9.png"
                    ), isRead = false
                )
            )
            add(
                Story(
                    id = 3,
                    user = User(
                        id = 3,
                        name = "Kotlin",
                        avatarUrl = "https://download.logo.wine/logo/Kotlin_(programming_language)/Kotlin_(programming_language)-Logo.wine.png"
                    ), isRead = false
                )
            )
            add(
                Story(
                    id = 4,
                    user = User(
                        id = 4,
                        name = "Twitter",
                        avatarUrl = "https://download.logo.wine/logo/Twitter/Twitter-Logo.wine.png"
                    ), isRead = false
                )
            )
            add(
                Story(
                    id = 5,
                    user = User(
                        id = 5,
                        name = "LinkedIn",
                        avatarUrl = "https://download.logo.wine/logo/LinkedIn/LinkedIn-Icon-Logo.wine.png"
                    ), isRead = false
                )
            )

        }
        return stories
    }

    fun getPosts(): List<Post> {
        val posts = arrayListOf<Post>()
        posts.apply {
            add(
                Post(
                    id = 0,
                    user = User(
                        id = 1,
                        name = "Android Developers",
                        avatarUrl = "https://pbs.twimg.com/profile_images/1168932726461935621/VRtfrDXq_400x400.png",
                    ),
                    text = "Meet Jetpack Compose, our new UI toolkit, now in alpha! See how ...\n" +
                        "\n" +
                        "✏️ It's written entirely in @Kotlin\n" +
                        "\uD83D\uDDA5️ Is unbundled from OS\n" +
                        "\uD83D\uDC68\u200D\uD83D\uDCBB Embraces a declarative programming model\n" +
                        "\uD83D\uDE0E And more!\n" +
                        "\n" +
                        "Learn more → goo.gle/2YA7heU",
                    imageUrl = "https://on.notist.cloud/slides/deck3159/large-0.jpg"
                )
            )
        }
        return posts
    }
}
