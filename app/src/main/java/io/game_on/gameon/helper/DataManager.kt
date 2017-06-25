package io.game_on.gameon.helper

import android.util.Log
import com.parse.Parse
import com.parse.ParseQuery
import com.parse.ParseObject
import com.parse.FindCallback



public class DataManager private constructor() {

    init { println("This ($this) is a singleton") }

    private object Holder { val INSTANCE = DataManager() }

    companion object {
        val instance: DataManager by lazy { Holder.INSTANCE }
    }

    fun getPosts() {
        val query = ParseQuery.getQuery<ParseObject>("Post")
        query.findInBackground { objects, e ->
            if (e == null) {
                Log.d("score", "Retrieved " + objects.size + " posts")
            } else {
                Log.d("score", "Error: " + e!!.message)
            }
        }

    }
}