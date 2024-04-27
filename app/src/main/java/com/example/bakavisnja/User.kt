package com.example.bakavisnja

import android.provider.ContactsContract.CommonDataKinds.Email

class User {
    var korisnik: String? = null
    var email: String? = null
    var uid: String? = null

    constructor(){}

    constructor(korisnik: String?, email: String, uid: String){
        this.korisnik = korisnik
        this.email = email
        this.uid = uid
    }
}