package com.example.github_api.di

import com.example.github_api.BuildConfig.BASE_URL
import com.example.github_api.BuildConfig.DEBUG
import com.example.github_api.data.RepositoriesService
import com.example.github_api.data.dataSource.RepositoriesRemoteDataSource
import com.example.github_api.data.repository.RepositoriesListRepositoryImpl
import com.example.github_api.domain.repository.RepositoriesListRepository
import com.example.github_api.domain.useCase.GetRepositoryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    fun provideBaseUrl() = BASE_URL

    @Provides
    @Singleton
    fun provideOkHttpClient() = if (DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else OkHttpClient
        .Builder()
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    @Provides
    fun provideRepositoriesService(retrofit: Retrofit): RepositoriesService =
        retrofit.create(RepositoriesService::class.java)

    @Provides
    fun provideRepositoriesRemoteDataSource(repositoryService: RepositoriesService) =
        RepositoriesRemoteDataSource(repositoryService)

    @Provides
    fun provideRepositoryListRepository(repositoryRemoteDataSource: RepositoriesRemoteDataSource): RepositoriesListRepository =
        RepositoriesListRepositoryImpl(repositoryRemoteDataSource)

    @Provides
    fun provideGetRepositoryUseCase(repositoriesListRepository: RepositoriesListRepository) =
        GetRepositoryUseCase(repositoriesListRepository)


}