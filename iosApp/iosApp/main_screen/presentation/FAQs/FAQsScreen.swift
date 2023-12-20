//
//  FAQsScreen.swift
//  iosApp
//
//  Created by Hunter Freas on 2023/12/14.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct FAQsScreen: View {
    let list: [Faq]
    @State var expand: Int
    init(list: [Faq]) {
        self.list = list
        self.expand = -1
    }
    var body: some View {
        ScrollView {
            if !list.isEmpty {
                LazyVStack(spacing: 12) {
                    Text("FAQs")
                        .bold()
                        .font(.largeTitle)
                        .frame(maxWidth: .infinity, alignment: .leading)
                        .foregroundColor(Color.primaryColor)
                        .padding(EdgeInsets(top: 0, leading: 0, bottom: 12, trailing: 0))
                    ForEach(list.indices, id: \.self) { index in
                        FAQsContainer(title: list[index].question, contentText: list[index].answer, isOpen: index == expand, onClick: {
                            if expand != index {
                                expand = index
                            } else {
                                expand = -1
                            }
                        })
                    }
                }.padding(EdgeInsets(top: 0, leading: 12, bottom: 0, trailing: 12))
            } else {
                Text("Whoops, nothing is here....")
            }
            
            
        }
        
        
     }
}

struct FAQsScreen_Previews: PreviewProvider {
    static var previews: some View {
        FAQsScreen(list: [Faq(question: "How do I do this1?", answer: "Lorem ipsum dolor sit amet consectetur. Ut aenean dignissim mauris gravida volutpat etiam. Purus viverra ut in aliquam egestas enim eget in. Diam faucibus diam ullamcorper ac. Sed elit semper vitae tristique velit leo pretium commodo. Sit viverra dui turpis sit ac diam sed pellentesque. Enim ut ut."),Faq(question: "How do I do this2?", answer: "Lorem ipsum dolor sit amet consectetur. Ut aenean dignissim mauris gravida volutpat etiam. Purus viverra ut in aliquam egestas enim eget in. Diam faucibus diam ullamcorper ac. Sed elit semper vitae tristique velit leo pretium commodo. Sit viverra dui turpis sit ac diam sed pellentesque. Enim ut ut."),Faq(question: "How do I do this3?", answer: "Lorem ipsum dolor sit amet consectetur. Ut aenean dignissim mauris gravida volutpat etiam. Purus viverra ut in aliquam egestas enim eget in. Diam faucibus diam ullamcorper ac. Sed elit semper vitae tristique velit leo pretium commodo. Sit viverra dui turpis sit ac diam sed pellentesque. Enim ut ut."),Faq(question: "How do I do this4?", answer: "Lorem ipsum dolor sit amet consectetur. Ut aenean dignissim mauris gravida volutpat etiam. Purus viverra ut in aliquam egestas enim eget in. Diam faucibus diam ullamcorper ac. Sed elit semper vitae tristique velit leo pretium commodo. Sit viverra dui turpis sit ac diam sed pellentesque. Enim ut ut."),Faq(question: "How do I do this5?", answer: "Lorem ipsum dolor sit amet consectetur. Ut aenean dignissim mauris gravida volutpat etiam. Purus viverra ut in aliquam egestas enim eget in. Diam faucibus diam ullamcorper ac. Sed elit semper vitae tristique velit leo pretium commodo. Sit viverra dui turpis sit ac diam sed pellentesque. Enim ut ut."),])
    }
}
