package com.example.madlevel5task2.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.madlevel5task2.R
import com.example.madlevel5task2.databinding.FragmentAddGameBinding
import com.example.madlevel5task2.model.Game
import com.example.madlevel5task2.model.GameViewModel

class AddGameFragment : Fragment() {
    private var _binding: FragmentAddGameBinding? = null
    private val binding get() = _binding!!

    private val viewModel: GameViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabSave.setOnClickListener {
            saveGame()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun saveGame() {
        val title = binding.etTitle.text.toString()
        val platform = binding.etPlatform.text.toString()
        val date =
            "${binding.etDateYear.text.toString()} ${binding.etDateMonth.text.toString()} ${binding.etDateDay.text.toString()}"

        val game = Game(title, platform, date)
        if (validateGame(game)) {
            viewModel.insertGame(game)
            findNavController().popBackStack()
        }
    }

    private fun validateGame(game: Game) : Boolean {
        if (game.title.isBlank()) {
            return showErrorMessage(R.string.error_empty_title)
        }
        if (game.platform.isBlank()) {
            return showErrorMessage(R.string.error_empty_platform)
        }
        if (game.releaseDate.isBlank()) {
            return showErrorMessage(R.string.error_date)
        }

        return true
    }

    private fun showErrorMessage(msg_string_id: Int) : Boolean {
        Toast.makeText(activity, msg_string_id, Toast.LENGTH_SHORT).show()
        return false
    }
}