//
//  FAQsContainer.swift
//  iosApp
//
//  Created by Hunter Freas on 2023/12/14.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct FAQsContainer: View {
    let title: String
    let contentText: String
    let isOpen: Bool
    let onClick: () -> Void
    
    var body: some View {
        VStack {
            HStack {
                Text(title)
                    .font(.title3)
                    .bold()
                Spacer()
                Image(systemName: "arrowtriangle.down.fill")
                    .rotationEffect(.degrees(isOpen ? 180 : 0))
            }
            VStack {
                if isOpen {
                    Text(contentText)
                        .frame(maxWidth: .infinity, alignment: .leading)
                        .multilineTextAlignment(.leading)
                        .padding(EdgeInsets(top: 12, leading: 0, bottom: 0, trailing: 0))
                        .font(.body)
                        .frame(height: isOpen ? nil : 0, alignment: .top)
                        .clipped()
                   
                }
            }
            
        }
        
        .padding(18)
        .foregroundColor(isOpen ? Color.onPrimary : Color.primaryColor)
        
        .transition(.slide)
        .animation(.easeInOut, value: isOpen)
        .cornerRadius(5)
        .shadow(radius: 5)
        .onTapGesture {
            onClick()
        }
        
    }
}

struct FAQsContainer_Previews: PreviewProvider {
    
    static var previews: some View {
        VStack {
            FAQsContainer(title: "How do I do this?", contentText: "Lorem ipsum2", isOpen: false) {
                
            }
            
            FAQsContainer(title: "How do I do this?", contentText: "Lorem ipsum", isOpen: true) {
                
            }
        }
        
    }
}
