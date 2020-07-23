package wanna.cu.covid19_.mainFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import wanna.cu.covid19_.R


class SexFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sex, container, false)
    }

    companion object {
        private var instance: SexFragment? = null

        @JvmStatic
        fun newInstance() =
            instance ?: synchronized(this) {
                instance ?: SexFragment().also { instance = it }
            }
    }
}