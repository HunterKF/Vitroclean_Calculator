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
    @State var scales: [CGFloat] = DATA.map { _ in return 0 }
    var animation = Animation.easeInOut.speed(0.5)
    let onClick: () -> Void
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
                        DotView(scale: .constant(scales[0]))
                        DotView(scale: .constant(scales[1]))
                        DotView(scale: .constant(scales[2]))
                    }
                    .onAppear {
                        animateDots() // Not defined yet
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
    struct AnimationData {
        var delay: TimeInterval
    }

    static let DATA = [
        AnimationData(delay: 0.0),
        AnimationData(delay: 0.2),
        AnimationData(delay: 0.4),
    ]
    func animateDots() {
        for (index, data) in Self.DATA.enumerated() {
            DispatchQueue.main.asyncAfter(deadline: .now() + data.delay) {
                animateDot(binding: $scales[index], animationData: data)
            }
        }

        //Repeat
        DispatchQueue.main.asyncAfter(deadline: .now() + 1.0) {
            animateDots()
        }
    }

    func animateDot(binding: Binding<CGFloat>, animationData: AnimationData) {
        withAnimation(animation) {
            binding.wrappedValue = 1
        }

        DispatchQueue.main.asyncAfter(deadline: .now() + 0.4) {
            withAnimation(animation) {
                binding.wrappedValue = 0.2
            }
        }
    }
}

private struct DotView: View {
    
    @Binding var scale: CGFloat

    var body: some View {
        Circle()
            .scale(scale)
            .fill(Color.primaryColor.opacity(scale >= 0.9 ? scale : scale - 0.1))
            .frame(width: 40, height: 40, alignment: .center)
    }
   
}




struct LoadingScreen_Previews: PreviewProvider {
    static var previews: some View {
        LoadingScreen(error: nil) {
            
        }
    }
}
