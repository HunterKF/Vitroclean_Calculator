//
//  StatDisplayColumn.swift
//  iosApp
//
//  Created by Hunter Freas on 2023/12/13.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct StatDisplayColumn: View {
    
    let selectedFilter: PoolFilter
    let padding: CGFloat
    
    var body: some View {
        ScrollView {
            LazyVStack(spacing: 20) {
                if selectedFilter.model != "" && selectedFilter.manufacturer != "" {
                    VStack {
                        Text(selectedFilter.model)
                            .font(.title2)
                        Text(selectedFilter.manufacturer)
                            .font(.body)
                    }
                } else {
                    Spacer(minLength: 12)
                }
                
                StatDisplay(text: "\(selectedFilter.recommendedSandLoad)", subtext: "Recommend Sand Load Lbs.")
                StatDisplay(text: "\(selectedFilter.recommendedVitrocleanVfaLoad)", subtext: "Recommended Vitroclean (VF) Load Lbs.")
                StatDisplay(text: "\(selectedFilter.recommendedPebble)", subtext: "Recommended Vitroclean Pebble (VFP) Load Lbs.")
                StatDisplay(text: "\(selectedFilter.fiftyBagVitroclean)", subtext: "50 lb. Bags of Vitroclean VF")
                StatDisplay(text: "\(selectedFilter.fiftyBagPebble)", subtext: "50 lb. Bags of Pebble (VFP).")
                Spacer(minLength: 12)
            }.padding(EdgeInsets(top: 0, leading: padding, bottom: 0, trailing: padding))
            
        }
    }
}

struct StatDisplayColumn_Previews: PreviewProvider {
    static var previews: some View {
        StatDisplayColumn(selectedFilter: PoolFilter(manufacturer: "Pentair", model: "TR40", recommendedSandLoad: 1, recommendedVitrocleanVfaLoad: 2, recommendedPebble: 3, fiftyBagVitroclean: 4, fiftyBagPebble: 5), padding: 12  )
            
    }
}
