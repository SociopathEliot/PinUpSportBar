import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.pinup.barapp.databinding.DialogHelpBinding
import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import com.pinup.barapp.R
import eightbitlab.com.blurview.BlurView
import eightbitlab.com.blurview.RenderScriptBlur


class HelpDialogFragment : DialogFragment() {
    private var _binding: DialogHelpBinding? = null
    private val binding get() = _binding!!

    private fun showBlur(show: Boolean) {
        val blurView = requireActivity().findViewById<BlurView>(R.id.blurView)
        if (show) {
            // Настраиваем blurView для overlay
            val rootView = requireActivity().window.decorView.findViewById<ViewGroup>(android.R.id.content).getChildAt(0) as ViewGroup
            blurView.setupWith(rootView)
//                .setBlurAlgorithm(RenderScriptBlur(requireContext()))
                .setBlurRadius(16f)
                .setOverlayColor(0x99FFFFFF.toInt())
//                .setHasFixedTransformationMatrix(true)
            blurView.visibility = View.VISIBLE

        } else {
            blurView.visibility = View.GONE
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = DialogHelpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        showBlur(true)
        dialog?.window?.setLayout(
            (resources.displayMetrics.widthPixels * 0.85).toInt(),
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog?.window?.setGravity(Gravity.CENTER)
    }

    override fun onDismiss(dialog: DialogInterface) {
        showBlur(false)
        super.onDismiss(dialog)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
