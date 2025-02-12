package com.splunk.test.presentation.di

import com.splunk.test.presentation.mapper.RepositoryUiMapper
import com.splunk.test.presentation.mapper.RepositoryUiMapperImpl
import com.splunk.test.presentation.utils.color.LanguageColorGenerator
import com.splunk.test.presentation.utils.color.LanguageColorGeneratorImpl
import com.splunk.test.presentation.utils.date.DateConverter
import com.splunk.test.presentation.utils.date.DateConverterImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface MapperModule {

    @Binds
    fun bindLanguageColorGenerator(impl: LanguageColorGeneratorImpl): LanguageColorGenerator

    @Binds
    fun bindDateConverter(impl: DateConverterImpl): DateConverter

    @Binds
    fun bindRepositoryUiMapper(impl: RepositoryUiMapperImpl): RepositoryUiMapper
}