package com.enzo.wwcam.wct.params

data class WctItem(var id: String, val name: String) {
    override fun toString(): String {
        return name
    }
}