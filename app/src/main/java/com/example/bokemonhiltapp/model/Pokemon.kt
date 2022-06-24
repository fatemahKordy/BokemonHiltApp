package com.example.bokemonhiltapp.model

data class Pokemon ( val name:String,  var url:String)


data class pokemonResponse (
    private val count:Int,
    private var next:String,
    private var previous:String?,
    val results:List<Pokemon>) // moshi converter only accept List not arrayList