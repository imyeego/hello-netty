package com.imyeego.kotlin


fun main() {
    var button = Button()
    button.listener = object : Listener{
        override fun onNext(i: Int) {
            print(i)
        }
    }

    makeButton(button)

    val text = Text<String>()
    text.setOnWatchListener { println(it) }
    text.setTextAndListener("liuzhao") {
        print(it)
        print("end")
    }

}

fun makeButton(button: Button?) {
    button?.listener?.onNext(5)
}

class Button {
    lateinit var listener: Listener
}
interface Listener {
    fun onNext(i: Int)
}