package io.game_on.gameon.model

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.parse.ParseClassName
import com.parse.ParseObject

@ParseClassName("Post")
class Post: ParseObject() {


    object keys {
        val createdAt = "createdAt"
        val user= "user"
        val content = "content"
        val image = "image"
        val likes = "likes"
        val likesCount = "likesCount"
        val comments = "comments"
        val commentsCount = "commentsCount"
    }

    private var image: Bitmap? = null
    
    /**
     *  The post's content
     */
    var user : User?
        get() = get(Post.keys.user) as User?
        set(value) {
            put(Post.keys.user, value)
        }

    /**
     *  The post's content
     */
    var content : String?
        get() = getString(Post.keys.content)
        set(value) {
            put(Post.keys.content, value)
        }


    fun getImage(callback: (image : Bitmap?, Exception?) -> Unit) {

        if (this.image == null) {
            val imageFile = getParseFile(Post.keys.image) ?: return
            imageFile.getDataInBackground { data, e ->
                if (e != null) {
                    callback(null, e)
                } else {
                    val bitmap = BitmapFactory.decodeByteArray(data, 0, data.size)
                    image = bitmap
                    callback(image, null)
                }
            }
        } else {
            callback(image, null)
        }

    }


}