package apps.nb.working.pocmoviesbymvi.ui.viewmodel

import androidx.lifecycle.ViewModel
import apps.nb.working.poccinemamvi.ui.state.AppState

import kotlinx.coroutines.flow.MutableStateFlow
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container


open class BaseViewModel<T : Any> constructor(
    appState: MutableStateFlow<AppState>,
) : ViewModel(), ContainerHost<MutableStateFlow<AppState>, T> {

    override val container = container<MutableStateFlow<AppState>, T>(appState)
}