package com.dimonkiv.savingstracker.presentation.utils.extensions

import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.dimonkiv.savingstracker.presentation.utils.delegate.FragmentViewLifecycleAwareDelegate

fun <T : ViewBinding> Fragment.viewBinding(viewBindingFactory: (View) -> T) =
    FragmentViewLifecycleAwareDelegate(this, viewBindingFactory)