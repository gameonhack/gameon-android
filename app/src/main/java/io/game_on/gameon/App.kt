package io.game_on.gameon

import android.app.Application
import com.parse.Parse
import com.parse.ParseObject
import io.game_on.gameon.model.User

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        ParseObject.registerSubclass(User::class.java)

        Parse.initialize(Parse.Configuration.Builder(this)
                .applicationId(BuildConfig.APP_ID)
                .clientKey(BuildConfig.CLIENT_KEY)
                .server(BuildConfig.SERVER_URL)
                .build()
        )

    }

}