//
//  CalculatorViewModel.swift
//  iosApp
//
//  Created by Hunter Freas on 2023/12/11.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class IosCalculatorViewModel: ObservableObject {
    private let viewModel: CalculatorViewModel = CalculatorViewModel()
    @Published var state: CalculatorState = CalculatorState(input: "", poolFilterList: [], selectedFilter: nil, manufacturerText: nil, filterText: nil, isChoosingFilter: false, isChoosingManufacturer: false, mode: .byFilter)
    
    func onEvent(event: CalculateUiEvent) {
        viewModel.onEvent(event: event)
    }
    
    private var handle: DisposableHandle?
    
    func startObserving() {
        handle = viewModel.state.subscribe(
            onCollect: { state in
                if let state = state {
                    self.state = state
                }
            })
    }
    
    func dispose() {
        handle?.dispose()
    }
    
}
