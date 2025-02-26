package com.example

class BinarySearchTree<K: Comparable<K>, V> {

    private var root: Node<K, V>? = null

    private data class Node<K, V>(
        var key: K,
        var value: V,
        var left: Node<K, V>? = null,
        var right: Node<K, V>? = null
    )

    fun isEmpty(): Boolean = root == null

    fun insert(key: K, value: V) {
        root = insertRecursive(root, key, value)
    }

    private fun insertRecursive(node: Node<K, V>?, key: K, value: V): Node<K, V> {
        if (node == null) {
            return Node(key, value)
        }

        when {
            key < node.key -> node.left = insertRecursive(node.left, key, value)
            key > node.key -> node.right = insertRecursive(node.right, key, value)
            else -> node.value = value
        }
        return node
    }

    fun search(key: K): V? {
        return searchRecursive(root, key)
    }

    private fun searchRecursive(node: Node<K, V>?, key: K): V? {
        if (node == null) {
            return null
        }

        return when {
            key < node.key -> searchRecursive(node.left, key)
            key > node.key -> searchRecursive(node.right, key)
            else -> node.value  // 키 찾음.
        }
    }
}