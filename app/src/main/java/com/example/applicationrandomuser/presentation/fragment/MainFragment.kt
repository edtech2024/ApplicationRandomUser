package com.example.applicationrandomuser.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.applicationrandomuser.ItemApplication
import com.example.applicationrandomuser.R
import com.example.applicationrandomuser.databinding.FragmentMainBinding
import com.example.applicationrandomuser.domain.iusecase.*
import com.example.applicationrandomuser.domain.model.ItemModel
import com.example.applicationrandomuser.presentation.mapper.ModelToUIMapper
import com.example.applicationrandomuser.presentation.uistate.ItemUI
import com.example.applicationrandomuser.presentation.viewmodel.ListViewModel
import javax.inject.Inject

class MainFragment : Fragment() {

    // Creates a new fragment given parameters
    // MainFragment.newInstance()
    companion object {

        fun newInstance(): MainFragment {
            val fragmentMain = MainFragment()
            return fragmentMain
        }
    }

    // Define the events that the fragment will use to communicate
    interface OnItemOpenListener {
        // This can be any number of events to be sent to the activity
        fun onOpenItem(open: Bundle)
    }

    // Define the listener of the interface type
    // listener will the activity instance containing fragment
    private var listenerOpen: OnItemOpenListener? = null

    lateinit var listViewModel: ListViewModel

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var useCaseCreateItem: IUseCaseCreateItem
    @Inject
    lateinit var useCaseUpdateItem: IUseCaseUpdateItem
    @Inject
    lateinit var useCaseRequestItems: IUseCaseRequestItems
    @Inject
    lateinit var useCaseQueryLocalItems: IUseCaseQueryLocalItems
    @Inject
    lateinit var useCaseRefreshItems: IUseCaseRefreshItems
    @Inject
    lateinit var modelToUI: ModelToUIMapper


    override fun onAttach(context: Context) {
        super.onAttach(context)
        listenerOpen = context as OnItemOpenListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity?.application as ItemApplication).appComponent.inject(this)

        listViewModel = ViewModelProvider(this, object: ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                return ListViewModel(
                    useCaseCreateItem, useCaseUpdateItem,
                    useCaseRequestItems, useCaseQueryLocalItems, useCaseRefreshItems,
                    modelToUI
                ) as T
            }
        }
        ).get(ListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate( inflater, container, false)
        _binding!!.lifecycleOwner = this
        _binding!!.listViewModel = listViewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializationComposeViewMain(listViewModel)
        initializationEditTextSearch()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onDetach() {
        super.onDetach()
        listenerOpen = null
    }


    fun initializationEditTextSearch() {
        binding.etSearch.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                listViewModel.applyFilterMethod()
            }
        })
    }

    private fun initializationComposeViewMain(viewModel: ListViewModel) {
        binding.composeViewMain.apply {
            // Dispose of the Composition when the view's LifecycleOwner is destroyed
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Navigation(viewModel)
                }
            }
        }
    }

    @Composable
    fun Navigation(viewModel: ListViewModel = viewModel()) {

        val data = viewModel.itemsList.observeAsState(emptyList())

        MainScreen(modifier = Modifier,
            data = data as State<List<ItemUI>>,
            onClick = { itemUI -> onItemClicked(itemUI) } ,
            refresh = { viewModel.refresh() }
        )

    }

    @Composable
    fun MainScreen(modifier: Modifier = Modifier,
                   data: State<List<ItemUI>>,
                   onClick: (ItemUI) -> Unit,
                   refresh: () -> Unit) {

        Column(modifier
            .fillMaxWidth()
            .border(width = 1.dp, color = Color.Black)
            .padding(10.dp)
        ) {

            Button(onClick = { refresh() }) {
                Text(text = "Refresh", fontSize = 25.sp)
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(count = 1)
            ) {

                items(data.value, key = { itemUI -> itemUI.id } ) {
                    ListItem(it, onClick = onClick )
                }
            }
        }
    }

    @Composable
    fun ListItem(data: ItemUI,
                 modifier: Modifier = Modifier,
                 onClick: (ItemUI) -> Unit) {
        Surface() {
            Row(modifier
                .fillMaxWidth()
                .border(width = 1.dp, color = Color.Black)
                .padding(10.dp)
                .clickable {
                    onClick(data)
                    Toast.makeText(activity?.applicationContext, "Click", Toast.LENGTH_SHORT).show()
                }
            ) {

                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(data.picture)
                        .crossfade(true)
                        .build(),
                    error = painterResource(R.drawable.ic_baseline_broken_image_24),
                    placeholder = painterResource(R.drawable.loading_img),
                    contentDescription = stringResource(R.string.item_photo),
                    contentScale = ContentScale.Crop
                )

                Text(text = data.gender)
                Text(text = data.name)
                Text(text = data.location)
                Text(text = data.email)
                Text(text = data.login)
                Text(text = data.dob)
                Text(text = data.registered)
                Text(text = data.phone)
                Text(text = data.cell)
                Text(text = data.id)
                Text(text = data.picture)
                Text(text = data.nat)
            }
        }
    }

    // Now we can fire the event when the user selects something in the fragment
    private fun onOpenClicked(bundle: Bundle){
        listenerOpen?.onOpenItem(bundle)
    }

    private fun onItemClicked(item: ItemUI){

        val args = Bundle()

        args.putString(getString(R.string.gender), item.gender)
        args.putString(getString(R.string.name), item.name)
        args.putString(getString(R.string.location), item.location)
        args.putString(getString(R.string.email), item.email)
        args.putString(getString(R.string.login), item.login)
        args.putString(getString(R.string.dob), item.dob)
        args.putString(getString(R.string.registered), item.registered)
        args.putString(getString(R.string.phone), item.phone)
        args.putString(getString(R.string.cell), item.cell)
        args.putString(getString(R.string.id), item.id)
        args.putString(getString(R.string.picture), item.picture)
        args.putString(getString(R.string.nat), item.nat)

        onOpenClicked(args)
    }

}