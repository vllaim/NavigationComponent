package com.kamaeft.navigationcomponent


import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.kamaeft.navigationcomponent.databinding.FragmentRootBinding


/**
 * The root screen. Can launch [BoxFragment] passing background color as an argument.
 * Корневой экран. Может запускать [BoxFragment], передавая цвет фона в качестве аргумента.
 */
class RootFragment : Fragment(R.layout.fragment_root) {

    private lateinit var binding: FragmentRootBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRootBinding.bind(view)
        binding.openYellowBoxButton.setOnClickListener {
            openBox(Color.rgb(255, 255, 200))
        }
        binding.openGreenBoxButton.setOnClickListener {
            openBox(Color.rgb(200, 255, 200))
        }

        // Listening for the results from BoxFragment
        // Прослушивание результатов из BoxFragment
        parentFragmentManager.setFragmentResultListener(BoxFragment.REQUEST_CODE, viewLifecycleOwner) { _, data ->
            val number = data.getInt(BoxFragment.EXTRA_RANDOM_NUMBER)
            Toast.makeText(requireContext(), getString(R.string.generated_number, number), Toast.LENGTH_SHORT).show()
        }
    }

    private fun openBox(color: Int) {

        // Launch BoxFragment with arguments and additional options
        // Запустить BoxFragment с аргументами и дополнительными опциями

        findNavController().navigate(
            R.id.action_rootFragment_to_boxFragment, // nav action to be executed
            bundleOf(BoxFragment.ARG_COLOR to color), // arguments for the destination
            // optional additional options, example of simple animation:
            navOptions {
                anim {
                    enter = R.anim.enter
                    exit = R.anim.exit
                    popEnter = R.anim.pop_enter
                    popExit = R.anim.pop_exit
                }
            }
        )
    }

}