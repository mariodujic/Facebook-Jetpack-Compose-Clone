package hr.josip.facebook.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import hr.josip.facebook.data.source.FacebookSource
import hr.josip.facebook.data.source.FacebookSourceImpl

@Module
@InstallIn(ActivityComponent::class)
abstract class SourceModule {

    @Binds
    abstract fun bindFacebookSource(facebookSourceImpl: FacebookSourceImpl): FacebookSource
}