package sample.huy.huy_retrofit_practice.activity.model

import com.google.gson.annotations.SerializedName

class Post {
    @SerializedName("id")
    var id:Int = -1

    @SerializedName("title")
    var title:String = ""


    constructor(_title:String) {
        title = _title
    }

    constructor(_id: Int, _title:String) {
        id = _id
        title = _title
    }
    companion object {
        val TAG = "Post"
    }
}