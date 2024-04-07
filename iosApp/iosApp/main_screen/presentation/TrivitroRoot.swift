//
//  SwiftUIView.swift
//  iosApp
//
//  Created by Hunter Freas on 2023/11/06.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct TrivitroRoot: View {
    private var appModule: AppModule
    
    @ObservedObject var sharedVM: IosSharedViewModel
    
    init(appModule: AppModule) {
        self.appModule = appModule
        self.sharedVM = IosSharedViewModel(getFilters: appModule.getFilters, getFaqs: appModule.getFaqs, getOnboarding: appModule.getOnboarding, toggleOnboarding: appModule.toggleOnboarding)
        
    }
    
    var body: some View {
        if sharedVM.state.showOnboarding {
            
            let _ = print("here is the current state: \(sharedVM.state)!")
            OnboardingScreen(onClick: {
                print("Toggling has happened!")
                sharedVM.onEvent(event: SharedUiEvent.ToggleOnboarding())
            })
        } else if  sharedVM.state.isLoading && !sharedVM.state.loaded && !sharedVM.state.showOnboarding {
            LoadingScreen(error: sharedVM.state.error, onClick: { sharedVM.onEvent(event: SharedUiEvent.OnRetry())}).onAppear {
                sharedVM.onEvent(event: SharedUiEvent.LoadData())
                sharedVM.startObserving()
            }
        } else {
            HomeScreen(sharedState: sharedVM.state)
        }
            
            
    }
    
}
