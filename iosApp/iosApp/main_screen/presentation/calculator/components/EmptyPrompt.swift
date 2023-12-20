//
//  EmptyPrompt.swift
//  iosApp
//
//  Created by Hunter Freas on 2023/12/12.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct EmptyPrompt: View {
    let text: String
    let icon: String
    
    var body: some View {
        ZStack {
            Text(text)
                .font(.system(size: 36).bold())
                
            Image(systemName: icon)
                .resizable()
                .frame(width: 140, height: 140)
                .foregroundColor(.black.opacity(0.08))
        }
        
    }
}

struct EmptyPrompt_Previews: PreviewProvider {
    static var previews: some View {
        EmptyPrompt(text: "Try it now", icon: "magnifyingglass")
    }
}
