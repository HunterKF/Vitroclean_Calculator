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
                Text("Welcome to Vitroclean")
                    .font(.title)
                    .foregroundColor(.primaryColor)
                    .bold()
                    .font(.largeTitle)
                Text("Here for all your pool filter needs, Vitroclean strives to make your job easier. Search by your pool filter model or by the cubic feet or sand needed.")
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
