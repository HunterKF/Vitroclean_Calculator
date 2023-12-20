//
//  LoadingScreen.swift
//  iosApp
//
//  Created by Hunter Freas on 2023/11/06.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct LoadingScreen: View {
    private let error: NetworkError?
    
    @State private var showErrorAlert: Bool
    
    var onClick: () -> Void
    init(error: NetworkError?, onClick: @escaping () -> Void) {
        self.error = error
        self._showErrorAlert = State(initialValue: error != nil)
        self.onClick = onClick
    }
    
    var body: some View {
        
        VStack {
            Image("logo_vitroclean")
                .resizable()
                .aspectRatio(contentMode: .fit)
            ZStack {
                
            }.padding(32)
            HStack {
                DotView(delay: Double(0.0), state: !showErrorAlert)
                DotView(delay: Double(0.3), state: !showErrorAlert)
                DotView(delay: Double(0.6), state: !showErrorAlert)
            }
            
        }
        .padding(48)
        .alert("Whoops...", isPresented: $showErrorAlert) {
            Button("Retry") {
                onClick()
            }
        } message: {
            if let error {
                Text("An error occurred. Error: \(error.name)")
            }
            
        }
        
    }
}

struct DotView: View {
    var delay: Double
    var state: Bool
    var body: some View {
        Circle()
            .frame(width: 25, height: 25)
            .foregroundColor(.primaryColor)
            .scaleEffect(state ? 1.0 : 0.5)
            .animation(.easeOut(duration: 0.5).repeatForever().delay(delay))
    }
}



struct LoadingScreen_Previews: PreviewProvider {
    static var previews: some View {
        LoadingScreen(error: NetworkError.clientError) {
            
        }
    }
}
