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
    
    @FocusState var isNameFocused:Bool
    
    
    var body: some View {
        ZStack {
            TextEditor(text: $text)
                .onChange(of: text, perform: { value in
                    onEvent(value)
                })
                .foregroundColor(self.text == defaultText ? .gray.opacity(0.5) : .primaryColor)
                .onTapGesture {
                            if self.text == defaultText {
                              self.text = ""
                            } else if self.text == "" {
                                self.text = defaultText
                            }
                          }
                .frame(height: 200)
            
        }
        .onAppear {
            if self.text == "" {
                self.text = defaultText
            }
        }
        .padding(10)
        .background(Color.onBackground)
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
