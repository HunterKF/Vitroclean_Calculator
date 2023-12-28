//
//  ToastView.swift
//  iosApp
//
//  Created by Hunter Freas on 12/27/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct ToastView: View {
  
  var message: String
  var width = CGFloat.infinity
  var onCancelTapped: (() -> Void)
  
  var body: some View {
    HStack(alignment: .center, spacing: 12) {
      Text(message)
        .font(Font.caption)
        .foregroundColor(Color.onBackground)
      
      Spacer(minLength: 10)
      
      Button {
        onCancelTapped()
      } label: {
        Image(systemName: "xmark")
              .foregroundColor(Color.onBackground)
      }
    }
    .padding()
    .frame(minWidth: 0, maxWidth: width)
    .cornerRadius(8)
    .overlay(
      RoundedRectangle(cornerRadius: 8)
        .opacity(0.3)
    )
    .padding(.horizontal, 16)
  }
}

struct Toast: Equatable {
  var message: String
  var duration: Double = 3
  var width: Double = .infinity
}

struct ToastModifier: ViewModifier {
  
  @Binding var toast: Toast?
  @State private var workItem: DispatchWorkItem?
  
  func body(content: Content) -> some View {
    content
      
      .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .bottom)
      .overlay(
        ZStack {
          mainToastView()
            .offset(y: 32)
        }.animation(.spring(), value: toast)
      )
      .onChange(of: toast) { value in
        showToast()
      }
  }
  
  @ViewBuilder func mainToastView() -> some View {
    if let toast = toast {
      VStack {
          Spacer()
        ToastView(
          message: toast.message,
          width: toast.width
        ) {
          dismissToast()
        }.padding(EdgeInsets(top: 0, leading: 0, bottom: 28, trailing: 0))
          
      }
    }
  }
  
  private func showToast() {
    guard let toast = toast else { return }
    
    UIImpactFeedbackGenerator(style: .light)
      .impactOccurred()
    
    if toast.duration > 0 {
      workItem?.cancel()
      
      let task = DispatchWorkItem {
        dismissToast()
      }
      
      workItem = task
      DispatchQueue.main.asyncAfter(deadline: .now() + toast.duration, execute: task)
    }
  }
  
  private func dismissToast() {
    withAnimation {
      toast = nil
    }
    
    workItem?.cancel()
    workItem = nil
  }
}

extension View {

  func toastView(toast: Binding<Toast?>) -> some View {
    self.modifier(ToastModifier(toast: toast))
  }
}
