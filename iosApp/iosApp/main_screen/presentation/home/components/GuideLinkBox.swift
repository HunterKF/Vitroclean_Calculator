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
            VStack {
                Image("photo_pool") // Replace with your image name
                    .resizable()
                    .aspectRatio(contentMode: .fill)
                    .frame(maxWidth: .infinity, maxHeight: 200)
                    .clipped()
                
                HStack {
                    Text("Vitroclean Specifications and Installation")
                        .font(.title2)
                        .foregroundColor(.onSurface)
                }
                .padding(.bottom)
            }
        }
        .background(Color.surface)
        
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
