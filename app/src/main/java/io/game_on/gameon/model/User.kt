package io.game_on.gameon.model

/**
 * Created by eduardoirias on 7/1/17.
 */
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.parse.ParseClassName
import com.parse.ParseUser

@ParseClassName("_User")
class User: ParseUser() {

    object keys {
        val name = "name"
        val bio = "bio"
    }

    private var image: Bitmap? = null

    /**
     *  The post's content
     */
    var name : String?
        get() = getString(User.keys.name)
        set(value) {
            put(User.keys.name, value)
        }

    /**
     *  The post's content
     */
    var bio : String?
        get() = getString(User.keys.bio)
        set(value) {
            put(User.keys.bio, value)
        }


    fun getImage(callback: (image : Bitmap?, Exception?) -> Unit) {

        if (this.image == null) {
            val imageFile = getParseFile("image") ?: return
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