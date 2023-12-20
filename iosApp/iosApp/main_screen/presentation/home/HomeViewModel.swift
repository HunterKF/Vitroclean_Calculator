//
//  HomeViewModel.swift
//  iosApp
//
//  Created by Hunter Freas on 2023/11/07.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class HomeViewModel: ObservableObject {
    
    private let viewModel: HomeScreenViewModel = HomeScreenViewModel()
    @Published var state: HomeUiState = HomeUiState(showPopUp: false, link: "https://trivitro.com/about/resources/#vitroclean&installation-instructions")
    
    
    func onEvent(event: HomeUiEvent) {
        viewModel.onEvent(event: event)
    }
}
