//
//  HomeScreen.swift
//  iosApp
//
//  Created by Hunter Freas on 2023/11/07.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct HomeScreen: View {
    private var sharedState: SharedUiState
    @State private var showingAlert = false
    @Environment(\.openURL) var openURL
    
    init(sharedState: SharedUiState) {
        self.sharedState = sharedState
    }
    var body: some View {
        NavigationView {
            ScrollView(.vertical){
                VStack {
                    NavigationLink {
                        CalculatorScreen(sharedState: sharedState, mode: "by_filter")
                    } label: {
                        HomeButton(text: "Caculate by filter", icon: "plus.forwardslash.minus", onClick: {}, contentDescription: "")
                            .padding(EdgeInsets(top: 4, leading: 0, bottom: 4, trailing: 0))
                    }
                    NavigationLink {
                        CalculatorScreen(sharedState: sharedState, mode: "by_cubic_feet")
                    } label: {
                        HomeButton(text: "Caculate by cubic feet", icon: "cube", onClick: {}, contentDescription: "")
                            .padding(EdgeInsets(top: 4, leading: 0, bottom: 4, trailing: 0))
                    }
                    NavigationLink {
                        CalculatorScreen(sharedState: sharedState, mode: "by_sand")
                    } label: {
                        HomeButton(text: "Caculate by sand needed", icon: "sand", onClick: {}, contentDescription: "")
                            .padding(EdgeInsets(top: 4, leading: 0, bottom: 4, trailing: 0))
                    }
                    
                    NavigationLink {
                        FAQsScreen(list: sharedState.faqsList)
                    } label: {
                        HomeButton(text: "FAQs", icon: "questionmark.circle", onClick: {}, contentDescription: "")
                            .padding(EdgeInsets(top: 4, leading: 0, bottom: 4, trailing: 0))
                    }
                    NavigationLink {
                        ContactScreen()
                    } label: {
                        HomeButton(text: "Contact Us", icon: "message", onClick: {}, contentDescription: "")
                            .padding(EdgeInsets(top: 4, leading: 0, bottom: 4, trailing: 0))
                    }
                    
                    GuideLinkBox() {
                        showingAlert = true
                    }
                    
                }.padding()
            }
            
            
            .alert(isPresented: $showingAlert) {
                Alert(title: Text("Navigating out of app"), message: Text("You are about to leave the app to go to to the Trivitro website. Do you want to proceed?"), primaryButton: .cancel(Text("No")), secondaryButton: .default(Text("Yes"), action: {
                          openURL(URL(string: "https://trivitro.com/about/resources/#vitroclean")!)
                      }))
                    }
            
            
            
        }.accentColor(.white)
        
    }
}

struct HomeScreen_Previews: PreviewProvider {
    static var previews: some View {
        var sharedState = SharedUiState(poolFilterList: [], faqsList: [Faq(question: "How do I do this", answer: "Lorem ipsum dolor sit amet consectetur. Ut aenean dignissim mauris gravida volutpat etiam. Purus viverra ut in aliquam egestas enim eget in. Diam faucibus diam ullamcorper ac. Sed elit semper vitae tristique velit leo pretium commodo. Sit viverra dui turpis sit ac diam sed pellentesque. Enim ut ut."),Faq(question: "How do I do this", answer: "Lorem ipsum dolor sit amet consectetur. Ut aenean dignissim mauris gravida volutpat etiam. Purus viverra ut in aliquam egestas enim eget in. Diam faucibus diam ullamcorper ac. Sed elit semper vitae tristique velit leo pretium commodo. Sit viverra dui turpis sit ac diam sed pellentesque. Enim ut ut."),Faq(question: "How do I do this", answer: "Lorem ipsum dolor sit amet consectetur. Ut aenean dignissim mauris gravida volutpat etiam. Purus viverra ut in aliquam egestas enim eget in. Diam faucibus diam ullamcorper ac. Sed elit semper vitae tristique velit leo pretium commodo. Sit viverra dui turpis sit ac diam sed pellentesque. Enim ut ut."),Faq(question: "How do I do this", answer: "Lorem ipsum dolor sit amet consectetur. Ut aenean dignissim mauris gravida volutpat etiam. Purus viverra ut in aliquam egestas enim eget in. Diam faucibus diam ullamcorper ac. Sed elit semper vitae tristique velit leo pretium commodo. Sit viverra dui turpis sit ac diam sed pellentesque. Enim ut ut."),Faq(question: "How do I do this", answer: "Lorem ipsum dolor sit amet consectetur. Ut aenean dignissim mauris gravida volutpat etiam. Purus viverra ut in aliquam egestas enim eget in. Diam faucibus diam ullamcorper ac. Sed elit semper vitae tristique velit leo pretium commodo. Sit viverra dui turpis sit ac diam sed pellentesque. Enim ut ut."),], isLoading: false, loaded: true, error: nil)
        HomeScreen(sharedState: sharedState)
    }
}
