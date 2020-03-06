package pe.solera.demopracticaltest.application

import android.app.Application



class DemoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
     /*   startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@DemoApplication)
            modules(
                listOf(
                    viewModelsModule, useCaseModule, networkModule, preferencesModule,
                    fileModule
                )
            )
        }
        getKoin().setProperty(NAME_BASE_URL, BuildConfig.BASE_URL)
        getKoin().setProperty(ENCRYPTION_KEY, getString(R.string.encryption_key))*/
    }
}