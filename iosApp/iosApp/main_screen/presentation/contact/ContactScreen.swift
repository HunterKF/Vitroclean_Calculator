//
//  ContactScreen.swift
//  iosApp
//
//  Created by Hunter Freas on 12/19/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ContactScreen: View {
    @ObservedObject var contactViewModel: IosContactViewModel
    @Environment(\.openURL) var openURL
    
    init() {
        self.contactViewModel = IosContactViewModel()
    }
   
    
    var body: some View {
        VStack(alignment: .leading) {
            TopBarCalc(content:{
                HStack(content: {
                    Text("Contact Us")
                        .font(.largeTitle)
                        .bold()
                        .foregroundColor(.white)
                        .frame(maxWidth: .infinity, alignment: .leading)
                })
                .padding(EdgeInsets(top: 0, leading: 12, bottom: 0, trailing: 12))
                
            }
            )
            VStack {
                SubjectTextfield(
                    text: Binding(get: { contactViewModel.state.subject }, set: { value in contactViewModel.onEvent(event: ContactUiEvent.OnSubjectChange(value: value))
                    }),
                    defaultText: "Subject"
                )
                .accentColor(.primaryColor)
                .overlay {
                    if contactViewModel.state.subjectError {
                    RoundedRectangle(cornerRadius: 5)
                        .stroke(.red, lineWidth: 2)}
                }
                .padding(EdgeInsets(top: 12, leading: 0, bottom: 12, trailing: 0))
                
                MessageTextField(text: contactViewModel.state.subject, defaultText: "Message", onEvent:{ item in
                    print(item )
                    print(contactViewModel.state)
                    contactViewModel.onEvent(event: ContactUiEvent.OnContentChange(value: item))}
                                 
                )
                .overlay {
                    if contactViewModel.state.contentError {
                    RoundedRectangle(cornerRadius: 5)
                        .stroke(.red, lineWidth: 2)}
                }
                .accentColor(.primaryColor)
                Spacer()
                Button(action: {
                    contactViewModel.onEvent(event: ContactUiEvent.AttemptToSend(intent: { SupportEmail(subject: contactViewModel.state.subject, body: contactViewModel.state.content).send(openURL: openURL) }))
                }, label: {
                    Text("Send")
                        .padding()
                        .font(.title3)
                        .foregroundColor(.onPrimary)
                })
                .disabled(contactViewModel.state.subject.isEmpty || contactViewModel.state.content.isEmpty || contactViewModel.state.content == "Message")
                .frame(maxWidth: .infinity)
                .background(accentColor)
                .cornerRadius(5)
            }
            .padding()
            
        }
        .frame(
                maxWidth: .infinity,
                maxHeight: .infinity,
                alignment: .topLeading
            )
        .onAppear(perform: { contactViewModel.startObserving()})
        .onDisappear(perform: {
            contactViewModel.onDispose()
        })
    }
    var accentColor: Color {
            if contactViewModel.state.subject.isEmpty || contactViewModel.state.content.isEmpty || contactViewModel.state.content == "Message" {
                return Color.gray
            } else {
                return Color.primaryColor
            }
        }
}

#Preview {
    ContactScreen()
}
