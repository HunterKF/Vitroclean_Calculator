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
    let image: String?
    let icon: String
    let onClick: () -> Void
    let contentDescription: String
    var color: Color = .primaryColor
    var body: some View {
        ZStack {
            HStack {
                if (image != nil) {
                    Image(image!)
                        .resizable()
                        .foregroundColor(color)
                        .frame(width: 32, height: 32)
                        .padding(.trailing, 18)
                } else {
                    Image(systemName: icon)
                        .resizable()
                        .foregroundColor(color)
                        .frame(width: 32, height: 32)
                        .padding(.trailing, 18)
                }
               

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
            HomeButton(text: "Caculate by filter", image: nil,icon: "plus.forwardslash.minus", onClick: {}, contentDescription: "")
            HomeButton(text: "Caculate by cubic feet", image: nil,icon: "cube", onClick: {}, contentDescription: "")
            HomeButton(text: "Caculate by sand needed", image: nil,icon: "sand", onClick: {}, contentDescription: "")
            HomeButton(text: "FAQs", image: nil,icon: "questionmark.circle", onClick: {}, contentDescription: "")
            HomeButton(text: "Contact Us", image: nil,icon: "message", onClick: {}, contentDescription: "")
        }
        .padding(12)
        .background(Color.background)
        .frame(width: .infinity, height: .infinity)
    }
}
