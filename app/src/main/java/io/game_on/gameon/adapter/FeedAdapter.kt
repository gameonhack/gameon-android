package io.game_on.gameon.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.RelativeLayout
import io.game_on.gameon.R
import io.game_on.gameon.model.Post
import kotlinx.android.synthetic.main.adapter_post.view.*

/**
 * Created by eduardoirias on 7/1/17.
 */

class FeedAdapter(val items: List<Post>, val listener: (Post) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.adapter_post, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val post = items[position]

        holder?.itemView?.text_post?.text = post.content
        holder?.itemView?.image_post?.setImageBitmap(null)

        val lay = LinearLayout.LayoutParams(holder!!.itemView.card_image.layoutParams)

        if (post.has(Post.keys.image)) {
            lay.setMargins(20, -60, 20, 20)
        } else {
            lay.setMargins(20, 20, 20, 20)
        }

        holder!!.itemView.card_image.layoutParams = lay
        holder!!.itemView.requestLayout()

        post.getImage { image, exception ->
            if (exception == null) {
                holder?.itemView?.image_post?.setImageBitmap(image)
            }
        }
        holder?.itemView?.text_date?.text = post?.createdAt.toLocaleString()
        holder?.itemView?.text_name?.text = post?.user?.name

        post?.user!!.getImage { image, exception ->
            holder!!.itemView.image_user.setImageBitmap(image)
        }

        holder!!.itemView.setOnClickListener {
            listener(post)
        }
    }

    override fun getItemCount() = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}