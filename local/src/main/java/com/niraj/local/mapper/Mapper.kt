package com.niraj.local.mapper

interface Mapper<T, E, K> {

    fun from(e: E): T

    fun to(key: K, t: T): E

}