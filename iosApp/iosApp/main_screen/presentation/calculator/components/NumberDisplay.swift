//
//  NumberDisplay.swift
//  iosApp
//
//  Created by Hunter Freas on 2023/12/12.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct NumberDisplay: View {
    
    @Binding var text: String
    
    let subtext: String
    let onClick: (CalculateUiEvent) -> Void
    enum FocusedField {
            case calculator
        }
    @FocusState private var focusedField: FocusedField?
    
    var body: some View {
        VStack {
            TextField("0", text: $text)
                .keyboardType(.numberPad)
                .frame(width: .infinity, height: 50, alignment: .center)
                .multilineTextAlignment(.center)
                .font(.title).padding()
                .focused($focusedField, equals: .calculator)
            Divider()
            Text(subtext).padding()
        }
        
        .background()
        .frame(width: .infinity)
        .cornerRadius(5)
        
        
    }
}

struct NumberDisplay_Previews: PreviewProvider {
    static var previews: some View {
        VStack(alignment: .center) {
            TopBarCalc {
                NumberDisplay(text: Binding(
                    get: { "0" },
                    set: { value in }
                ), subtext: "sand needed", onClick: { item in })
            }
            
        }
        
        
    }
}
