package com.versilistyson.androidstreeteats.di.app.component

import android.app.Application
import com.versilistyson.androidstreeteats.di.app.module.AppModule
import com.versilistyson.androidstreeteats.di.app.module.ServiceModule
import com.versilistyson.androidstreeteats.di.app.module.ViewModelModule
import com.versilistyson.androidstreeteats.di.scope.ApplicationScope
import com.versilistyson.androidstreeteats.presentation.ui.MainActivity
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [AppModule::class, ViewModelModule::class, ServiceModule::class])
interface AppComponent {

    fun inject(target: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application) : AppComponent
    }

}