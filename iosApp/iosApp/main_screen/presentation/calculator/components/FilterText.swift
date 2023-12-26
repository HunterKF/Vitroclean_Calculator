//
//  FilterText.swift
//  iosApp
//
//  Created by Hunter Freas on 12/22/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import Combine

struct FilterTextModifier: ViewModifier {
    @Binding var text: String
    func body(content: Content) -> some View {
        content
            .keyboardType(.numberPad)
            .onChange(of: text) { newValue in
                            let numbers = "0123456789"
                            let filtered = newValue.filter { numbers.contains($0) }.prefix(3)
                            if filtered != newValue {
                                self.text = String(filtered)
                            }
                        }
    }
}

extension View {
    func numbersOnly(_ text: Binding<String>) -> some View {
        self.modifier(FilterTextModifier(text: text))
    }
}
