//
//  HomeButton.swift
//  iosApp
//
//  Created by Hunter Freas on 2023/11/07.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI


struct HomeButton: View {
    let text: String
    let icon: String
    let onClick: () -> Void
    let contentDescription: String
    var color: Color = .primaryColor
    var body: some View {
        ZStack {
            HStack {
                Image(systemName: icon)
                    .foregroundColor(color)
                    .frame(width: 32, height: 32)
                    .padding(.trailing, 18)

                Text(text)
                    .foregroundColor(color)
                    .frame(height: 48)

                Spacer()
            }
            .padding(.vertical, 12)
            .padding(.horizontal, 18)
            .background(Color.background)
            .cornerRadius(8)
        }
            .shadow(radius: 2)
    }
}

struct HomeButton_Previews: PreviewProvider {
    static var previews: some View {
        VStack {
            HomeButton(text: "Caculate by filter", icon: "plus.forwardslash.minus", onClick: {}, contentDescription: "")
            HomeButton(text: "Caculate by cubic feet", icon: "cube", onClick: {}, contentDescription: "")
            HomeButton(text: "Caculate by sand needed", icon: "sand", onClick: {}, contentDescription: "")
            HomeButton(text: "FAQs", icon: "questionmark.circle", onClick: {}, contentDescription: "")
            HomeButton(text: "Contact Us", icon: "message", onClick: {}, contentDescription: "")
        }
        .padding(12)
        .background(Color.background)
        .frame(width: .infinity, height: .infinity)
    }
}
