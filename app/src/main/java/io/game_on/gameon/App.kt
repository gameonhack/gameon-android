package io.game_on.gameon

import android.app.Application
import android.util.Log
import com.parse.Parse

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Parse.initialize(Parse.Configuration.Builder(this)
                .applicationId(BuildConfig.APP_ID)
                .clientKey(BuildConfig.CLIENT_KEY)
                .server(BuildConfig.SERVER_URL)
                .build()
        )
    }

}