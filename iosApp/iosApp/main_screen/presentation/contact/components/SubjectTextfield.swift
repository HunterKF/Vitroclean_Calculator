//
//  EmailTextfield.swift
//  iosApp
//
//  Created by Hunter Freas on 12/19/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct SubjectTextfield: View {
    @Binding var text: String
    let defaultText: String
    var body: some View {
        ZStack {
            TextField(defaultText, text: $text)
                .keyboardType(.default)
                .foregroundColor(.primaryColor)
                .accessibilityIdentifier("Subject Textfield")
        }
        .padding()
        .background(Color.background)
        .cornerRadius(5)
        .shadow(radius: 2)
    }
}

#Preview {
    SubjectTextfield(text: Binding(get: { "" }, set: { value in
        
    }), defaultText: "Message")
}
