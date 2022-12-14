package com.example.madlevel5task2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel5task2.R
import com.example.madlevel5task2.databinding.FragmentGamesBinding
import com.example.madlevel5task2.model.Game
import com.example.madlevel5task2.model.GameViewModel
import com.google.android.material.snackbar.Snackbar


class GamesFragment : Fragment() {
    private var _binding: FragmentGamesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: GameViewModel by viewModels()

    private val games = arrayListOf<Game>()
    private val gameAdaptor = GameAdaptor(games)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGamesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        observeViewModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initViews() {
        val recyclerView = binding.rvGames
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        recyclerView.adapter = gameAdaptor
        createItemTouchHelper().attachToRecyclerView(recyclerView)

        binding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.action_gamesFragment_to_addGameFragment)
        }
    }

    private fun observeViewModel() {
        viewModel.games.observe(viewLifecycleOwner, Observer { games ->
            this@GamesFragment.games.clear()
            this@GamesFragment.games.addAll(games)
            this@GamesFragment.games.sortBy { it.releaseDate }
            gameAdaptor.notifyDataSetChanged()
        })
    }

    private fun createItemTouchHelper() : ItemTouchHelper {
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ) : Boolean { return false }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                viewModel.deleteGame(games[position])

                val snackbar = Snackbar.make(binding.rvGames, R.string.success_delete, Snackbar.LENGTH_LONG)
                snackbar.setAction(R.string.undo, View.OnClickListener{
                    viewModel.undoDeleteGame()
                })
                snackbar.show()
            }
        }
        return ItemTouchHelper(callback)
    }
}