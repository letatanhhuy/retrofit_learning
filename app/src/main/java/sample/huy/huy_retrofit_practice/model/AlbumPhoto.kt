package sample.huy.huy_retrofit_practice.activity.model

class AlbumPhoto {
    var id:Int = -1
        get() = id
        set(value) {
            if(value > 0)
                field = value
        }
    var title:String = ""

    constructor(_id: Int, _title:String) {
        id = _id
        title = _title
    }
    companion object {
        val TAG = "AlbumPhoto"
    }
}