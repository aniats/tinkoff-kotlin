package ru.tinkoff.homework.fintech.lesson4

data class Node<T>(val elem: T, var next: Node<T>? = null)
