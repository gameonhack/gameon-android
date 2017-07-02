package io.game_on.gameon.helper

import android.util.Log
import com.parse.Parse
import com.parse.ParseQuery
import com.parse.ParseObject
import com.parse.FindCallback
import io.game_on.gameon.model.Post

public class DataManager private constructor() {

    init { println("This ($this) is a singleton") }

    private object Holder { val INSTANCE = DataManager() }

    companion object {
        val instance: DataManager by lazy { Holder.INSTANCE }
    }

    fun getPosts(block : (posts : List<Post>?, exception: Exception?) -> Unit) {

        val query = ParseQuery.getQuery(Post::class.java)
        query.orderByDescending(Post.keys.createdAt)
        query.include(Post.keys.user)
        query.findInBackground {
            objects, e ->

            if (e == null) {
                for (post in objects) {
                    Log.d("score", post.user!!.name + ": " + post.content)
                }
                Log.d("score", "Retrieved " + objects.size + " posts")
            } else {
                Log.d("score", "Error: " + e!!.message)
            }

            block(objects, e)
        }

    }
}