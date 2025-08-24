package com.example.applicationrandomuser.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.applicationrandomuser.ItemApplication
import com.example.applicationrandomuser.databinding.FragmentDetailBinding
import com.example.applicationrandomuser.presentation.viewmodel.DetailViewModel

class DetailFragment : Fragment() {

    // Creates a new fragment given parameters
    // DetailFragment.newInstance()
    companion object {

        private const val GENDER = "Gender"
        private const val NAME = "Name"
        private const val LOCATION = "Location"
        private const val EMAIL = "Email"
        private const val LOGIN = "Login"
        private const val DOB = "Dob"
        private const val REGISTERED = "Registered"
        private const val PHONE = "Phone"
        private const val CELL = "Cell"
        private const val ID = "Id"
        private const val PICTURE = "Picture"
        private const val NAT = "Nat"

        fun newInstance(
            gender: String?,
            name: String?,
            location: String?,
            email: String?,
            login: String?,
            dob: String?,
            registered: String?,
            phone: String?,
            cell: String?,
            id: String?,
            picture: String?,
            nat: String?
        ): DetailFragment {
            val fragmentDetail = DetailFragment()
            val args = Bundle()

            args.putString(GENDER, gender)
            args.putString(NAME, name)
            args.putString(LOCATION, location)
            args.putString(EMAIL, email)
            args.putString(LOGIN, login)
            args.putString(DOB, dob)
            args.putString(REGISTERED, registered)
            args.putString(PHONE, phone)
            args.putString(CELL, cell)
            args.putString(ID, id)
            args.putString(PICTURE, picture)
            args.putString(NAT, nat)

            fragmentDetail.arguments = args
            return fragmentDetail
        }
    }

    // Define the events that the fragment will use to communicate
    interface OnItemCloseListener{
        // This can be any number of events to be sent to the activity
        fun onCloseItem()
    }

    // Define the listener of the interface type
    // listener will the activity instance containing fragment
    private var listenerClose: OnItemCloseListener? = null

    lateinit var detailViewModel: DetailViewModel

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)

        listenerClose = context as OnItemCloseListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity?.application as ItemApplication).appComponent.inject(this)

        detailViewModel = ViewModelProvider(this , object: ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return DetailViewModel(getArguments(), listenerClose) as T
            }
        }
        ).get(DetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        _binding = FragmentDetailBinding.inflate( inflater, container, false)
        _binding!!.lifecycleOwner = viewLifecycleOwner
        _binding!!.detailViewModel = detailViewModel

        initializationComposeViewDetail()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onDetach() {
        super.onDetach()
        listenerClose = null
    }

    // Now we can fire the event when the user selects something in the fragment
    private fun onCloseClicked() {
        listenerClose?.onCloseItem()
    }

    private fun initializationComposeViewDetail() {
        binding.composeViewDetail.apply {
            // Dispose of the Composition when the view's LifecycleOwner is destroyed
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                Surface(modifier = Modifier.fillMaxSize()) {
                    DetailScreen()
                }
            }
        }
    }

    @Composable
    fun DetailScreen(modifier: Modifier = Modifier) {
        Row(modifier
            .fillMaxWidth()
            .border(width = 1.dp, color = Color.Black)
            .padding(10.dp)
        ) {

            Box(modifier = Modifier.fillMaxSize()) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentHeight()
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    val gender = detailViewModel.gender.collectAsState()
                    val name = detailViewModel.name.collectAsState()
                    val location = detailViewModel.location.collectAsState()
                    val email = detailViewModel.email.collectAsState()
                    val login = detailViewModel.login.collectAsState()
                    val dob = detailViewModel.dob.collectAsState()
                    val registered = detailViewModel.registered.collectAsState()
                    val phone = detailViewModel.phone.collectAsState()
                    val id = detailViewModel.id.collectAsState()
                    val picture = detailViewModel.picture.collectAsState()
                    val nat = detailViewModel.nat.collectAsState()

                    Text(text = "Detail screen")

                    OutlinedTextField(
                        value = gender.value,
                        onValueChange = { detailViewModel.setGender(it) },
                        label = { Text(text = "gender") }
                    )

                    OutlinedTextField(
                        value = name.value,
                        onValueChange = { detailViewModel.setName(it) },
                        label = { Text(text = "name") }
                    )

                    OutlinedTextField(
                        value = location.value,
                        onValueChange = { detailViewModel.setLocation(it) },
                        label = { Text(text = "location") }
                    )

                    OutlinedTextField(
                        value = email.value,
                        onValueChange = { detailViewModel.setEmail(it) },
                        label = { Text(text = "email") }
                    )

                    OutlinedTextField(
                        value = login.value,
                        onValueChange = { detailViewModel.setLogin(it) },
                        label = { Text(text = "login") }
                    )

                    OutlinedTextField(
                        value = dob.value,
                        onValueChange = { detailViewModel.setDob(it) },
                        label = { Text(text = "dob") }
                    )

                    OutlinedTextField(
                        value = registered.value,
                        onValueChange = { detailViewModel.setRegistered(it) },
                        label = { Text(text = "registered") }
                    )

                    OutlinedTextField(
                        value = phone.value,
                        onValueChange = { detailViewModel.setPhone(it) },
                        label = { Text(text = "phone") }
                    )

                    OutlinedTextField(
                        value = id.value,
                        onValueChange = { detailViewModel.setId(it) },
                        label = { Text(text = "id") }
                    )

                    OutlinedTextField(
                        value = picture.value,
                        onValueChange = { detailViewModel.setPicture(it) },
                        label = { Text(text = "picture") }
                    )

                    OutlinedTextField(
                        value = nat.value,
                        onValueChange = { detailViewModel.setNat(it) },
                        label = { Text(text = "nat") }
                    )

                    Button(onClick = {
                        onCloseClicked()
                    }) {
                        Text(text = "Закрыть")
                    }

                }
            }
        }
    }

}