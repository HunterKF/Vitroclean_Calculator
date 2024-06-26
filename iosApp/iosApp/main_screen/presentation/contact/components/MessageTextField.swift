//
//  ContactTextField.swift
//  iosApp
//
//  Created by Hunter Freas on 12/19/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI


struct MessageTextField: View {
    @State var text: String
    let defaultText: String
    let onEvent: (String) -> Void
    
    
    
    var body: some View {
        ZStack(alignment: .leading) {
                if text.isEmpty {
                    Text(defaultText)
                        .padding(EdgeInsets(top: 8, leading: 0, bottom: 8, trailing: 0))
                        .frame(height: 200, alignment: .top)
                }
                
                TextEditor(text: $text)
                    .padding(EdgeInsets(top: 0, leading: -4,    bottom: 0, trailing: 0))
                    .frame(height: 200, alignment: .top)
                    .foregroundColor(.primaryColor)
                    .textEditorBackground(Color.background.opacity(text.isEmpty ? 0.8 : 1))
                    .onChange(of: text, perform: { value in
                        onEvent(value)
                    })
                    .accessibilityIdentifier("Message Textfield")
            
        }
        .padding(EdgeInsets(top: 8, leading: 16, bottom: 8, trailing: 4))
        .background(Color.background)
        .cornerRadius(5)
        .shadow(radius: 2)
        
            
    }
}

#Preview {
    
    VStack {
        MessageTextField(text: "", defaultText: "Message", onEvent: { string in })
    }
    .padding(12)
    .frame(height: .infinity)
    
}
