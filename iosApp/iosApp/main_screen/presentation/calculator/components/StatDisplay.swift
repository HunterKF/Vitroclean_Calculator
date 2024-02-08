//
//  StatDisplay.swift
//  iosApp
//
//  Created by Hunter Freas on 2023/12/12.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct StatDisplay: View {
    let text: String
    let subtext: String
    
    var body: some View {
        VStack(alignment: .center) {
            Text(text)
                .font(.title2)
                .padding()
            ZStack {
                Text(subtext)
                    .foregroundColor(.onSecondary)
            }
            .frame(
                minWidth: 0,
                maxWidth: .infinity,
                minHeight: 0,
                alignment: .center
              )
            .padding(EdgeInsets(top: 12, leading: 0, bottom: 12, trailing: 0))
            .background(Color.secondarySeaGreen)
        }
        .background(Color.background)
        .cornerRadius(5)
        .shadow(radius: 5)
    }
}

struct StatDisplay_Previews: PreviewProvider {
    static var previews: some View {
        VStack {
            StatDisplay(text: "165", subtext: "Recommended Sand Load Lbs.")
            StatDisplay(text: "165", subtext: "Recommended Sand Load Lbs.")
            StatDisplay(text: "165", subtext: "Recommended Sand Load Lbs.")
            StatDisplay(text: "165", subtext: "Recommended Sand Load Lbs.")
        }
        .padding(12)
    }
}
