package com.example.a20230703_urvishpatel_nycschools

fun removeDuplicates(str: String): String {

    // apple -> aple

    val map = HashMap<Char, Char>()
    val buffer = StringBuffer()
    str.forEach {
        if (!map.containsKey(it)) {
            map[it] = it
            buffer.append(it)
        }
    }
    val s = String(buffer)
    return s
}