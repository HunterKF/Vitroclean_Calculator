//
//  OnboardingScreen.swift
//  iosApp
//
//  Created by Hunter Freas on 4/3/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct OnboardingScreen: View {

    let onClick: () -> Void
    var body: some View {
        VStack {
            VStack {
                Image("onboarding_screen")
                    .resizable()
                    .aspectRatio(contentMode: .fit)
                Text("Welcome")
                    .font(.title)
                    .foregroundColor(.primaryColor)
                    .bold()
                    .font(.largeTitle)
                Text("Welcome to Vitroclean's calculator, your seamless solution for precisely determining the right amount of glass filter media for your pool, tailored by filter model, sand requirements, or cubic feet.")
                    .multilineTextAlignment(.center)
                
            
            }
            Button(action: {
                onClick()
            }, label: {
                Text("Get Started")
                    .padding()
                    .font(.title3)
                    .foregroundColor(.onPrimary)
            })
            .frame(maxWidth: .infinity)
            .background(Color.primaryColor)
            .cornerRadius(5)
            
        }
        .padding()
    }
}

#Preview {
    OnboardingScreen(onClick: {})
}
