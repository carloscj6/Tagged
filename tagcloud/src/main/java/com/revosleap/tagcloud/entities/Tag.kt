package com.revosleap.tagcloud.entities

data class Tag(var id: Int, var text: String?) {
    var attrs: Map<*, *>? = null
}
