package com.example

class Vector<T>(private var capacity: Int = 10) {

    // Any + ? = Any? (nullable)
    private var array: Array<Any?> = arrayOfNulls(capacity)
    private var currentSize: Int = 0

    fun size(): Int = currentSize

    fun isEmpty(): Boolean = currentSize == 0

    fun pushBack(value: T) {
        if (currentSize == capacity) { // array가 꽉 찼을 때
            resize()    // 배열 크기 늘리기 (x2 크기로)
        }
        array[currentSize++] = value
    }

    fun get(index: Int): T {
        if (index < 0 || index >= currentSize) {
            throw IndexOutOfBoundsException("Index: $index, Size: $currentSize")
        }
        @Suppress("UNCHECKED_CAST")
        return array[index] as T    // 타입 캐스팅 (null이 아님을 보장)
    }

    fun set(index: Int, value: T) {
        if (index !in 0 until currentSize) {
            throw IndexOutOfBoundsException("Index: $index, Size: $currentSize")
        }
        array[index] = value
    }

    private fun resize() {
        capacity *= 2 // C++처럼 2배씩 늘리기
        val newArray = arrayOfNulls<Any?>(capacity)
        for (i in 0 until currentSize) { // 현재 크기까지만 복사
            newArray[i] = array[i]
        }
        array = newArray // 새로운 배열로 교체
    }

    fun removeAt(index: Int): T {
        if (index !in 0 until currentSize) {
            throw IndexOutOfBoundsException("Index: $index, Size: $currentSize")
        }

        @Suppress("UNCHECKED_CAST")
        val removedItem = array[index] as T

        for (i in index until currentSize - 1) {
            array[i] = array[i + 1] // 한 칸씩 앞으로 당기기
        }
        array[--currentSize] = null // 마지막 원소 null 초기화

        // shrink array if necessary (optional, for memory efficiency)
        if (currentSize > 0 && currentSize <= capacity / 4) {
            shrink()
        }
        return removedItem
    }

    fun insertAt(index: Int, item: T) {
        if (index !in 0..currentSize) {
            throw IndexOutOfBoundsException("Index: $index, Size: $currentSize")
        }

        if (currentSize == capacity) {
            resize()
        }

        for (i in currentSize downTo index + 1) {
            array[i] = array[i - 1] // 한 칸씩 뒤로 밀기
        }
        array[index] = item
        currentSize++
    }

    fun clear() {
        for (i in 0 until currentSize) {
            array[i] = null
        }
        currentSize = 0
        // Optionally shrink the array back to initial capacity (for long-lived vectors)
        if(capacity > 10){
            shrinkToInitial()
        }
    }

    fun contains(item: T): Boolean {
        for(i in 0 until currentSize){
            if(array[i] == item){
                return true
            }
        }
        return false
    }

    private fun shrink() {
        capacity /= 2 // 반으로 줄임
        val newArray = arrayOfNulls<Any?>(capacity)
        for (i in 0 until currentSize) { // 현재 크기까지만 복사
            newArray[i] = array[i]
        }
        array = newArray // 새로운 배열로 교체
    }

    private fun shrinkToInitial(){
        capacity = 10
        val newArray = arrayOfNulls<Any?>(capacity)
        for(i in 0 until currentSize){
            newArray[i] = array[i]
        }
        array = newArray
    }

    // iterator 구현
    operator fun iterator(): Iterator<T> = object : Iterator<T> {
        private var currentIndex = 0

        override fun hasNext(): Boolean = currentIndex < currentSize

        override fun next(): T {
            if (!hasNext()) {
                throw NoSuchElementException()
            }
            @Suppress("UNCHECKED_CAST")
            return array[currentIndex++] as T
        }
    }
}