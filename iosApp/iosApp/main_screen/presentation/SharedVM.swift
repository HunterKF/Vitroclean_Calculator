//
//  File.swift
//  iosApp
//
//  Created by Hunter Freas on 2023/11/06.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared



@MainActor class IosSharedViewModel: ObservableObject {
    
    private var getFilters: GetFilters
    private var getFaqs: GetFaqs
    private var getOnboarding: GetOnboarding
    private var toggleOnboarding: ToggleOnboarding
    
    private let viewModel: SharedViewModel
    @Published var state: SharedUiState = SharedUiState(poolFilterList: [], faqsList: [], isLoading: true, loaded: false, showOnboarding: false, error: nil)
    
    private var handle: Kotlinx_coroutines_coreDisposableHandle?
    
    init(getFilters: GetFilters, getFaqs: GetFaqs, getOnboarding: GetOnboarding, toggleOnboarding: ToggleOnboarding) {
        self.getFilters = getFilters
        self.getFaqs = getFaqs
        self.getOnboarding = getOnboarding
        self.toggleOnboarding = toggleOnboarding
        self.viewModel = SharedViewModel(getFilters: getFilters, getFaqs: getFaqs, getOnboarding: getOnboarding, toggleOnboarding: toggleOnboarding, coroutineScope: nil)
    }
    
    func onEvent(event: SharedUiEvent) {
        viewModel.onEvent(event: event)
    }
    
    func startObserving() {
        handle = viewModel.state.subscribe(
            onCollect: { state in
                if let state = state {
                    self.state = state
                }
            }
        )
    }
    
    func onDispose() {
        handle?.dispose()
    }
}
    

