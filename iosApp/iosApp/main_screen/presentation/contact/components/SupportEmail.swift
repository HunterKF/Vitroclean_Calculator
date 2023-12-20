//
//  SupportEmail.swift
//  iosApp
//
//  Created by Hunter Freas on 12/20/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import UIKit
import SwiftUI

struct SupportEmail {
    let toAddress: String = "hunter.krez@gmail.com"
    let subject: String
    var body: String
    
    func send(openURL: OpenURLAction) {
        let urlString = "mailto:\(toAddress)?subject=TriVitro Inquiry - \(subject.addingPercentEncoding(withAllowedCharacters: .urlPathAllowed) ?? "")&body=\(body.addingPercentEncoding(withAllowedCharacters: .urlPathAllowed) ?? "")"
        guard let url = URL(string: urlString) else { return }
        openURL(url) { accepted in
            if !accepted {
                print("""
                This device does not support email
                \(subject)
                \(body)
                """
                )
            }
        }
    }
    
}
