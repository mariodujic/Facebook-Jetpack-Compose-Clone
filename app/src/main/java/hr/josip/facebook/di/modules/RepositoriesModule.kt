package hr.josip.facebook.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import hr.josip.facebook.domain.Repositories
import hr.josip.facebook.domain.repositories.FeedRepository

@Module
@InstallIn(ActivityComponent::class)
abstract class RepositoriesModule {

    @Binds
    abstract fun bindFeedRepository(feedRepository: FeedRepository): Repositories.FeedRepository

}