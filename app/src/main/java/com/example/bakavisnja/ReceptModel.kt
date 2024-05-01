package com.example.bakavisnja

class ReceptModel {

    var id:String = ""
    var naziv:String = ""
    var sastojci:String = ""
    var koraci:String = ""
    var kategorija:String = ""
    var vreme:String = ""
    var slika:String = ""

    constructor()

    constructor(
        id:String,
        naziv: String,
        sastojci: String,
        koraci:String,
        kategorija: String,
        vreme:String,
        slika: String
        ) {
        this.id = id
        this.naziv = naziv
        this.kategorija = kategorija
        this.koraci = koraci
        this.slika = slika
        this.sastojci = sastojci
        this.vreme = vreme
    }
}