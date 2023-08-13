package com.muradakhundov.cinemania.activity.home.hilt

import com.muradakhundov.cinemania.activity.home.data.MovieDataSource
import com.muradakhundov.cinemania.activity.home.repo.MovieRepository
import com.muradakhundov.cinemania.activity.home.retrofit.ApiUtils
import com.muradakhundov.cinemania.activity.home.retrofit.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

   @Provides
   @Singleton
   fun provideRepository(mds : MovieDataSource) : MovieRepository{
       return MovieRepository(mds)
   }


   @Provides
   @Singleton
   fun provideDataSource(mdao : MovieDao) : MovieDataSource{
       return MovieDataSource(mdao)
   }

    @Provides
    @Singleton
    fun provideDao() : MovieDao{
        return ApiUtils.getDao()
    }
}