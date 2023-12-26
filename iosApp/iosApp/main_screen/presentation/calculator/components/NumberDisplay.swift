//
//  NumberDisplay.swift
//  iosApp
//
//  Created by Hunter Freas on 2023/12/12.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared
import Combine

struct NumberDisplay: View {
    
    @Binding var vmText: String
    
    @State var text: String
    //Had to include vmText, which is the same value as text. This was done because when I used a regular @Binding, the local state wouldn't reflect the actual state of the text. With this work around, the vmText is updated seperately and the local text displays properly.
    let subtext: String
    let onClick: (CalculateUiEvent) -> Void
    enum FocusedField {
            case calculator
        }
    @FocusState private var focusedField: FocusedField?
    init(text: String, vmText: Binding<String>, subtext: String, onClick: @escaping (CalculateUiEvent) -> Void, focusedField: FocusedField? = nil) {
        self._vmText = vmText
        self._text = State(initialValue: text)
        self.subtext = subtext
        self.onClick = onClick
        self.focusedField = focusedField
    }
    
    var body: some View {
        VStack {
            TextField("0", text: $text)
                //numbersOnly filters to numbers and takes the first 3
                .numbersOnly($text)
                .onChange(of: text) {
                    newValue in
                    //Here we are updating the vmText
                    print(newValue)
                    print("newValue")
                    vmText = newValue
                }
                .multilineTextAlignment(.center)
                .font(.title).padding()
                .focused($focusedField, equals: .calculator)
            Divider()
            Text(subtext).padding()
        }
        .background()
        .cornerRadius(5)
        .toolbar {
            ToolbarItem(placement: .keyboard) {
                Spacer()
            }
            ToolbarItem(placement: .keyboard) {
                Button {
                    focusedField = nil
                } label: {
                    Image(systemName: "keyboard.chevron.compact.down")
                }
            }
        }
    }
        
}

struct NumberDisplay_Previews: PreviewProvider {
    static var previews: some View {
        VStack(alignment: .center) {
            TopBarCalc {
                NumberDisplay(text: "", vmText: Binding(get: { "0"}, set: { value in
                }), subtext: "sand needed", onClick: { item in })
            }
            
        }
        
        
    }
}
