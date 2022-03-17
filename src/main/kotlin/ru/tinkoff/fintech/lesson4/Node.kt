package ru.tinkoff.fintech.lesson4

data class Node<T>(val elem: T, var next: Node<T>? = null) 
