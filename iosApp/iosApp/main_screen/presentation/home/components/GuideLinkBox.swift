//
//  GuideLinkBox.swift
//  iosApp
//
//  Created by Hunter Freas on 2023/11/11.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct GuideLinkBox: View {
    let onClick: () -> Void

    var body: some View {
        Button(action: onClick) {
            ZStack(alignment: .bottomLeading) {
                Image("photo_pool")
                    .resizable()
                    .aspectRatio(contentMode: .fill)
                    .frame(maxWidth: .infinity, maxHeight: 200)
                    .clipped()
                
                HStack {
                    Text("Vitroclean Specifications and Installation")
                        .font(.title2)
                        .foregroundColor(.white)
                        .frame(maxWidth: .infinity, alignment: .leading)
                }
                
                .padding(EdgeInsets(top: 0, leading: 12, bottom: 12, trailing: 12))
                .frame(maxWidth: .infinity)
                .background(
                    LinearGradient(
                        colors: [.black.opacity(0.0), .black.opacity(0.4)],
                        startPoint: .top,
                        endPoint: .bottom
                    )
                )
            }
        }
        .accessibilityIdentifier("link")
        .background(Color.background)
        
        .frame(maxWidth: .infinity)
        .cornerRadius(10) // Optional for rounded corners
        .shadow(radius: 2)
        
    }
}

struct GuideLinkBox_Previews: PreviewProvider {
    static var previews: some View {
        VStack {
            GuideLinkBox() {}
        }.padding()
        
    }
}
