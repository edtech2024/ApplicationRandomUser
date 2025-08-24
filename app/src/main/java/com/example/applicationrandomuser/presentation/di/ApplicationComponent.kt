package com.example.applicationrandomuser.presentation.di

import android.content.Context
import com.example.applicationrandomuser.data.di.DataModule
import com.example.applicationrandomuser.domain.di.DomainModule
import com.example.applicationrandomuser.presentation.fragment.DetailFragment
import com.example.applicationrandomuser.presentation.fragment.MainFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Component(modules = [DataModule::class, DomainModule::class, PresentationModule::class] )
@Singleton
interface ApplicationComponent {

    @Component.Factory
    interface SingletonComponentFactory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }

    fun inject(fragment: MainFragment)
    fun inject(fragment: DetailFragment)

}