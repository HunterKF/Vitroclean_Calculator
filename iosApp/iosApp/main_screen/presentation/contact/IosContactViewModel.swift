//
//  IosContactViewModel.swift
//  iosApp
//
//  Created by Hunter Freas on 12/19/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class IosContactViewModel: ObservableObject {
    private let viewModel: ContactViewModel = ContactViewModel()
    @Published var state: ContactState = ContactState(email: "", name: "", subject: "", content: "", emailError: false, nameError: false, subjectError: false, contentError: false, error: nil, sent: false, maxMessageCount: 500, maxSubjectCount: 120)
    
    
    private var handle: Kotlinx_coroutines_coreDisposableHandle?
    
    func onEvent(event: ContactUiEvent) {
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
