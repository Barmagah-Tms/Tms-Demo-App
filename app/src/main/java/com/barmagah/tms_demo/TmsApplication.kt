package com.barmagah.tms_demo

import android.app.Application
import com.barmagah.tms_demo.home.network.datasource.CompanyDatasource
import com.barmagah.tms_demo.home.network.datasource.CompanyDatasourceImpl
import com.barmagah.tms_demo.home.network.response.ApiNetworkService
import com.barmagah.tms_demo.home.repository.CompanyRepository
import com.barmagah.tms_demo.home.repository.CompanyRepositoryImpl
import com.barmagah.tms_demo.home.ui.CompanyViewModelFactory
import com.barmagah.tms_demo.system.data.login.db.TmsDatabase
import com.barmagah.tms_demo.system.network.connectivity.ConnectivityInterceptor
import com.barmagah.tms_demo.system.network.connectivity.ConnectivityInterceptorImpl
import com.barmagah.tms_demo.system.network.datasource.SystemDataSource
import com.barmagah.tms_demo.system.network.datasource.SystemDataSourceImpl
import com.barmagah.tms_demo.system.network.response.ApiSystemService
import com.barmagah.tms_demo.system.provider.UserPreferenceProvider
import com.barmagah.tms_demo.system.provider.UserPreferenceProviderImpl
import com.barmagah.tms_demo.system.repository.SystemRepository
import com.barmagah.tms_demo.system.repository.SystemRepositoryImpl
import com.barmagah.tms_demo.system.ui.login.LoginViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class TmsApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@TmsApplication))

        /*
        * System Dependency Injection
        *
        * */

        // Common Binding
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind<UserPreferenceProvider>() with singleton { UserPreferenceProviderImpl(instance()) }

        // database
        bind() from singleton { TmsDatabase(instance()) }
        bind() from singleton { instance<TmsDatabase>().currentUserDao() }

        // Network
        bind() from singleton { ApiSystemService(instance()) }
        bind<SystemDataSource>() with singleton { SystemDataSourceImpl(instance()) }

        // viewModel
        bind() from provider { LoginViewModelFactory(instance(), instance()) }

        // Repository
        bind<SystemRepository>() with singleton {
            SystemRepositoryImpl(
                instance(),
                instance(),
                instance()
            )
        }

        /* Company
        * Application Admin DI
        *
        *
        *
        * */
        // Network
        bind() from singleton { ApiNetworkService(instance(), instance()) }
        bind<CompanyDatasource>() with singleton { CompanyDatasourceImpl(instance()) }

        // viewModel
        bind() from provider { CompanyViewModelFactory(instance()) }

        // Repository
        bind<CompanyRepository>() with singleton {
            CompanyRepositoryImpl(
                instance()
            )
        }

    }
}