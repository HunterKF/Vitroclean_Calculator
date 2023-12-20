//
//  TopBarCalc.swift
//  iosApp
//
//  Created by Hunter Freas on 2023/12/11.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct TopBarCalc<Content: View>: View {
     
    @ViewBuilder let content: Content
    var body: some View {
        VStack {
            content
        }
        
        .padding(EdgeInsets(top: 30, leading: 12, bottom: 30, trailing: 12))
        
        .frame(
            minWidth: 0,
            maxWidth: .infinity,
            minHeight: 0,
            alignment: .center
          )
        .background(Color.primaryColor)
        
        
    }
}

struct TopBarCalc_Previews: PreviewProvider {
    static var previews: some View {
        VStack {
            TopBarCalc(content: {
                VStack(spacing: 22) {
                    DropDownBox(list: ["TR40", "TR50", "TR60", "TR10", "TR20", "TR30"], currentItem: "Pentair", showDropDown: false, defaultText: "Select manufacturer", onEvent: { item in }, onToggleEvent: {})
                    DropDownBox(list: ["TR40", "TR50", "TR60", "TR10", "TR20", "TR30"], currentItem: nil, showDropDown: true, defaultText: "Select model", onEvent: { item in }, onToggleEvent: {})
                }
                    
                
            })
            Spacer()
            
        }.frame(width: .infinity, height: .infinity, alignment: .top)
        
    }
}
