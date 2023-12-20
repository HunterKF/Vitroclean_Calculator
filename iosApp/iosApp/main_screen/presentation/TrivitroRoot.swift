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
        self.sharedVM = IosSharedViewModel(getFilters: appModule.getFilters, getFaqs: appModule.getFaqs)
    }
    
    var body: some View {
        if sharedVM.state.isLoading && !sharedVM.state.loaded {
            LoadingScreen(error: sharedVM.state.error, onClick: { sharedVM.onEvent(event: SharedUiEvent.OnRetry())}).onAppear {
                sharedVM.onEvent(event: SharedUiEvent.LoadData())
                sharedVM.startObserving()
            }
        } else if !sharedVM.state.isLoading && !sharedVM.state.loaded && sharedVM.state.error != nil {
                    Text("An error occurred")
                    Text("FaqsList: \(sharedVM.state.description())")
                    Text("FilterList: ")
                } else if !sharedVM.state.isLoading && sharedVM.state.loaded {
                    HomeScreen(sharedState: sharedVM.state)
                } 
    }
}
//
//struct TrivitroRoot_Previews: PreviewProvider {
//    static var previews: some View {
//        TrivitroRoot()
//    }
//}
