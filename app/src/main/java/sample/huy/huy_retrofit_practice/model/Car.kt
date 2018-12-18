package sample.huy.huy_retrofit_practice.activity.model

import android.util.Log
import javax.inject.Inject

class Car {
    var engine:Engine
    var wheel:Wheel
    @Inject
    constructor(engine: Engine, wheel: Wheel) {
        this.engine = engine
        this.wheel = wheel
    }

    fun drive() {
        Log.d("test", "Driving.....")
    }
}