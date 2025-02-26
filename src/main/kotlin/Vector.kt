package com.example

class Vector<T>(private var capacity: Int = 10) {

    private var array: Array<Any?> = arrayOfNulls(capacity)
    private var currentSize: Int = 0

    fun size(): Int = currentSize

    fun isEmpty(): Boolean = currentSize == 0
}