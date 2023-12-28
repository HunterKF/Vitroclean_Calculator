//
//  DropdownBox.swift
//  iosApp
//
//  Created by Hunter Freas on 2023/12/11.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct DropDownBox: View {
    
    let list: [String]? 
    let currentItem: String?
    let showDropDown: Bool
    let defaultText: String
    let onEvent: (String) -> Void
    let onToggleEvent: () -> Void
    
    init(list: [String], currentItem: String? = nil, showDropDown: Bool, defaultText: String, onEvent: @escaping (String) -> Void, onToggleEvent: @escaping () -> Void) {
        UIScrollView.appearance().bounces = false // Disable bounce
        self.list = list
        self.currentItem = currentItem
        self.showDropDown = showDropDown
        self.defaultText = defaultText
        self.onEvent = onEvent
        self.onToggleEvent = onToggleEvent
    }
    
    var body: some View {
        VStack {
            VStack(spacing: 0) {
                
                Button(action: {
                    withAnimation {
                        self.onToggleEvent()
                    }
                }) {
                    HStack {
                        Text(currentItem ?? defaultText)
                            .font(currentItem == nil ? .body : .title3)
                            .foregroundColor(currentItem != nil ? .primary : .secondary)
                        Spacer()
                        Image(systemName: "arrowtriangle.down.fill")
                            .rotationEffect(.degrees(showDropDown ? 180 : 0))
                            .foregroundColor(Color.primaryColor)
                    }
                    .padding()
                }
                if showDropDown, let list {
                    ScrollView(.vertical, showsIndicators: true) {
                        VStack {
                            Divider()
                            ForEach(list, id: \.self) { item in
                                Button(action: {
                                    withAnimation {
                                        self.onEvent(item)
                                    }
                                }) {
                                    HStack {
                                        Text(item)
                                            .font(.body)
                                            .foregroundColor(Color.onSurface)
                                        Spacer()
                                    }
                                    .padding()
                                }
                                if item != list.last {
                                    Divider()
                                }
                            }
                        }
                    }
                    .frame(maxHeight: 150)
                }
            }
            .background(Color.background)
                .fixedSize(horizontal: false, vertical: true)
            .cornerRadius(5)

        }
    }
}

struct DropdownBox_Previews: PreviewProvider {
    
    
    
    static var previews: some View {
        VStack {
            DropDownBox(list: ["TR40", "TR50", "TR60", "TR40", "TR50", "TR60"], currentItem: nil, showDropDown: false, defaultText: "Select model", onEvent: { item in }, onToggleEvent: {})
            DropDownBox(list: ["TR40", "TR50", "TR60", "TR10", "TR20", "TR30"], currentItem: nil, showDropDown: true, defaultText: "Select model", onEvent: { item in }, onToggleEvent: {})
            
            DropDownBox(list: ["TR40"], currentItem: "TR50", showDropDown: true, defaultText: "Select model", onEvent: { item in }, onToggleEvent: {})
        }
        .padding(12)
        .background(Color.primaryColor)
        
    }
}
