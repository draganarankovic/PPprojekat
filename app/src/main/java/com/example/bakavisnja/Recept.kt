package com.example.bakavisnja

data class Recept(var naziv : String ?= null, var slika : String ?= null,
                  var id : String ?= null, var kategorija : String ?= null,
                  var sastojci : String ?= null, var uid : String ?= null,
                  var vreme : String ?= null, var koraci : String ?= null)
