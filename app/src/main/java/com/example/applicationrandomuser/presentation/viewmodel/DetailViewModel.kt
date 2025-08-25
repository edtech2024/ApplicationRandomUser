package com.example.applicationrandomuser.presentation.viewmodel

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.example.applicationrandomuser.R
import com.example.applicationrandomuser.domain.converter.StringValue
import com.example.applicationrandomuser.domain.model.*
import com.example.applicationrandomuser.presentation.fragment.DetailFragment
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.parcelize.parcelableCreator
import javax.inject.Inject

class DetailViewModel @Inject constructor(val bundle: Bundle?, val context: DetailFragment.OnItemCloseListener?) : ViewModel() {

    private val _gender: MutableStateFlow<String> = MutableStateFlow("Gender")
    val gender: StateFlow<String> = _gender.asStateFlow()

    fun setGender(gender: String) {
        _gender.value = gender
    }

    private val _name: MutableStateFlow<String> = MutableStateFlow("Name")
    val name: StateFlow<String> = _name.asStateFlow()

    fun setName(name: String) {
        _name.value = name
    }

    private val _location: MutableStateFlow<String> = MutableStateFlow("Location")
    val location: StateFlow<String> = _location.asStateFlow()

    fun setLocation(location: String) {
        _location.value = location
    }

    private val _email: MutableStateFlow<String> = MutableStateFlow("Email")
    val email: StateFlow<String> = _email.asStateFlow()

    fun setEmail(email: String) {
        _email.value = email
    }

    private val _login: MutableStateFlow<String> = MutableStateFlow("Login")
    val login: StateFlow<String> = _login.asStateFlow()

    fun setLogin(login: String) {
        _login.value = login
    }

    private val _dob: MutableStateFlow<String> = MutableStateFlow("Dob")
    val dob: StateFlow<String> = _dob.asStateFlow()

    fun setDob(dob: String) {
        _dob.value = dob
    }

    private val _registered: MutableStateFlow<String> = MutableStateFlow("Registered")
    val registered: StateFlow<String> = _registered.asStateFlow()

    fun setRegistered(registered: String) {
        _registered.value = registered
    }

    private val _phone: MutableStateFlow<String> = MutableStateFlow("Phone")
    val phone: StateFlow<String> = _phone.asStateFlow()

    fun setPhone(phone: String) {
        _phone.value = phone
    }

    private val _cell: MutableStateFlow<String> = MutableStateFlow("Cell")
    val cell: StateFlow<String> = _cell.asStateFlow()

    fun setCell(cell: String) {
        _cell.value = cell
    }

    private val _id: MutableStateFlow<String> = MutableStateFlow("0")
    val id: StateFlow<String> = _id.asStateFlow()

    fun setId(id: String) {
        _id.value = id
    }

    private val _picture: MutableStateFlow<String> = MutableStateFlow("Picture")
    val picture: StateFlow<String> = _picture.asStateFlow()

    fun setPicture(picture: String) {
        _picture.value = picture
    }
    private val _nat: MutableStateFlow<String> = MutableStateFlow("Nat")
    val nat: StateFlow<String> = _nat.asStateFlow()

    fun setNat(nat: String) {
        _nat.value = nat
    }

    var argGender: String = "String"
    var argName: String = "String"
    var argLocation: String = "String"
    var argEmail: String = "String"
    var argLogin: String = "String"
    var argDob: String = "String"
    var argRegistered: String = "String"
    var argPhone: String = "String"
    var argCell: String = "String"
    var argId: String = "String"
    var argPicture: String = "String"
    var argNat: String = "String"

    init {
        if (bundle != null) {
            readBundle(bundle)

            setArgs(
                this.argGender,
                this.argName,
                this.argLocation,
                this.argEmail,
                this.argLogin,
                this.argDob,
                this.argRegistered,
                this.argPhone,
                this.argCell,
                this.argId,
                this.argPicture,
                this.argNat
            )
        }
    }

    override fun onCleared() {

    }

   private fun readBundle(bundle: Bundle){
        this.argGender = bundle.getString(StringValue.StringResource(R.string.gender).asString(context as Context), "Gender")
        this.argName = bundle.getString(StringValue.StringResource(R.string.name).asString(context as Context), "Name")
        this.argLocation = bundle.getString(StringValue.StringResource(R.string.location).asString(context as Context),"Location")
        this.argEmail = bundle.getString(StringValue.StringResource(R.string.email).asString(context as Context), "Email")
        this.argLogin = bundle.getString(StringValue.StringResource(R.string.login).asString(context as Context), "Login")
        this.argDob = bundle.getString(StringValue.StringResource(R.string.dob).asString(context as Context), "Dob")
        this.argRegistered = bundle.getString(StringValue.StringResource(R.string.registered).asString(context as Context), "Registered")
        this.argPhone = bundle.getString(StringValue.StringResource(R.string.phone).asString(context as Context), "Phone")
        this.argCell = bundle.getString(StringValue.StringResource(R.string.cell).asString(context as Context), "Cell")
        this.argId = bundle.getString(StringValue.StringResource(R.string.id).asString(context as Context), "Id")
        this.argPicture = bundle.getString(StringValue.StringResource(R.string.picture).asString(context as Context), "Picture")
        this.argNat = bundle.getString(StringValue.StringResource(R.string.nat).asString(context as Context), "Nat")
    }

    private fun setArgs(gender: String, name: String, location: String, email: String, login: String, dob: String,
                        registered: String, phone: String, cell: String, id: String, picture: String, nat: String) {

        setGender(gender)
        setName(name)
        setLocation(location)
        setEmail(email)
        setLogin(login)
        setDob(dob)
        setRegistered(registered)
        setPhone(phone)
        setCell(cell)
        setId(id)
        setPicture(picture)
        setNat(nat)
    }

}