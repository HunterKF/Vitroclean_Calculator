//
//  CalculatorScreen.swift
//  iosApp
//
//  Created by Hunter Freas on 2023/12/12.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct CalculatorScreen: View {
    
    let mode: String
    private var sharedState: SharedUiState
    @ObservedObject var calculatorVM: IosCalculatorViewModel
    
    init(sharedState: SharedUiState, mode: String) {
        self.sharedState = sharedState
        self.calculatorVM = IosCalculatorViewModel()
        self.mode = mode
       
    }
    
    var body: some View {
        VStack(alignment: .leading) {
            switch calculatorVM.state.mode {
            case .byFilter:
                ByFilter(
                    poolFilterList: calculatorVM.state.poolFilterList,
                    selectedFilter: calculatorVM.state.selectedFilter,
                    manufacturerText: calculatorVM.state.manufacturerText,
                    filterText: calculatorVM.state.filterText,
                    isChoosingManufacturer: calculatorVM.state.isChoosingManufacturer,
                    isChoosingModel: calculatorVM.state.isChoosingFilter,
                    onEvent: {item in calculatorVM.onEvent(event: item)}
                )
            case .byCubicFeet:
                ByNumber(
                    displayText: Binding(get: { calculatorVM.state.input }, set: { value in
                        calculatorVM.onEvent(event: CalculateUiEvent.OnNumberChange(value: value))
                    }),
                    subtext: "cubic feet",
                    selectedFilter: calculatorVM.state.selectedFilter,
                    onCalculatorEvent: { event in calculatorVM.onEvent(event: event) }
                )
            case .bySand:
                ByNumber(
                    displayText: Binding(get: { calculatorVM.state.input }, set: { value in
                        
                        calculatorVM.onEvent(event:CalculateUiEvent.OnNumberChange(value: value))
                    }),
                    subtext: "sand needed",
                    selectedFilter: calculatorVM.state.selectedFilter,
                    onCalculatorEvent: { event in calculatorVM.onEvent(event: event) }
                )
                
            default: Text(/*@START_MENU_TOKEN@*/"Hello, World!"/*@END_MENU_TOKEN@*/)
            }
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .topLeading)
        .onAppear {
            if !sharedState.poolFilterList.isEmpty {
                calculatorVM.onEvent(event: CalculateUiEvent.AddList(list: sharedState.poolFilterList))
            }
            calculatorVM.onEvent(event: CalculateUiEvent.ChangeMode(mode: mode))
            calculatorVM.startObserving()
        }
        .onDisappear {
            calculatorVM.dispose()
        }
        
            
        
    }
        
}

private struct ByFilter: View {
    
    let poolFilterList: [PoolFilter]
    let selectedFilter: PoolFilter?
    let manufacturerText: String?
    let filterText: String?
    let isChoosingManufacturer: Bool
    let isChoosingModel: Bool
    let onEvent: (CalculateUiEvent) -> Void
    
    @State private var toast: Toast? = nil
    
    var body: some View {
        VStack {
            TopBarCalc {
                VStack(spacing: 18) {
                    DropDownBox(
                        list: Array(poolFilterList.unique { $0.manufacturer}.map { $0.manufacturer}),
                        currentItem: manufacturerText,
                        showDropDown: isChoosingManufacturer,
                        defaultText: "Select manufacturer", onEvent: { item in onEvent(CalculateUiEvent.SelectManufacturer(text: item))},
                        onToggleEvent: {onEvent(CalculateUiEvent.ToggleManufacturerDropdown())}
                    ).accessibilityIdentifier("select manufacturer")
                    DropDownBox(
                        list: Array(poolFilterList.filter { $0.manufacturer == manufacturerText }.map { $0.model}),
                        currentItem: filterText,
                        showDropDown: isChoosingModel,
                        defaultText: "Select model",
                        onEvent: { item in onEvent(CalculateUiEvent.SelectFilter(filter: item))},
                        onToggleEvent: {
                            if manufacturerText == nil || manufacturerText == ""  {
                                toast = Toast(message: "Select a filter first.")
                            } else {
                                
                                    print("YO22")
                                onEvent(CalculateUiEvent.ToggleFilterDropdown())
                            }
                           
                        }
                    )
                    .accessibilityIdentifier("select model")
                }
                
            }
            VStack {
                if selectedFilter != nil {
                    StatDisplayColumn(selectedFilter: selectedFilter!, padding: 12)
                    
                } else {
                    Spacer()
                    EmptyPrompt(text: "Search for a filter", icon: "magnifyingglass")
                    Spacer()
                }
            }
           
        }
        .background(Color.surface)
        .toastView(toast: $toast)
        
        
    }
}

private struct ByNumber: View {
    @Binding var displayText: String
    let subtext: String
    let selectedFilter: PoolFilter?
    let onCalculatorEvent: (CalculateUiEvent) -> Void
    
    var body: some View {
        VStack {
            TopBarCalc {
                NumberDisplay(text: $displayText.wrappedValue, vmText: $displayText, subtext: subtext, onClick: { item in })
            }
            VStack {
                if selectedFilter != nil {
                    StatDisplayColumn(selectedFilter: selectedFilter!, padding: 12)
                    
                } else {
                    Spacer()
                    EmptyPrompt(text: "Start calculating now!", icon: "magnifyingglass")
                    Spacer()
                }
            }
        }
        .background(Color.surface)
    }
}



struct CalculatorScreen_ByFilter_Previews: PreviewProvider {
    static var previews: some View {
        CalculatorScreen(sharedState: SharedUiState(poolFilterList: [PoolFilter(manufacturer: "Pentair", model: "TR10", recommendedSandLoad: 1, recommendedVitrocleanVfaLoad: 2, recommendedPebble: 3, fiftyBagVitroclean: 4, fiftyBagPebble: 5),PoolFilter(manufacturer: "Pentair", model: "TR20", recommendedSandLoad: 1, recommendedVitrocleanVfaLoad: 2, recommendedPebble: 3, fiftyBagVitroclean: 4, fiftyBagPebble: 5),PoolFilter(manufacturer: "Pentair", model: "TR30", recommendedSandLoad: 1, recommendedVitrocleanVfaLoad: 2, recommendedPebble: 3, fiftyBagVitroclean: 4, fiftyBagPebble: 5),PoolFilter(manufacturer: "Pentair", model: "TR40", recommendedSandLoad: 1, recommendedVitrocleanVfaLoad: 2, recommendedPebble: 3, fiftyBagVitroclean: 4, fiftyBagPebble: 5),PoolFilter(manufacturer: "Pentair", model: "TR50", recommendedSandLoad: 1, recommendedVitrocleanVfaLoad: 2, recommendedPebble: 3, fiftyBagVitroclean: 4, fiftyBagPebble: 5),PoolFilter(manufacturer: "Pentair", model: "TR60", recommendedSandLoad: 1, recommendedVitrocleanVfaLoad: 2, recommendedPebble: 3, fiftyBagVitroclean: 4, fiftyBagPebble: 5),PoolFilter(manufacturer: "Pentair", model: "TR70", recommendedSandLoad: 1, recommendedVitrocleanVfaLoad: 2, recommendedPebble: 3, fiftyBagVitroclean: 4, fiftyBagPebble: 5)], faqsList: [], isLoading: false, loaded: true, showOnboarding: false, error: nil), mode: "by_filter")
       
        
    }
}
struct CalculatorScreen_ByNumber_Previews: PreviewProvider {
    static var previews: some View {
        CalculatorScreen(sharedState: SharedUiState(poolFilterList: [PoolFilter(manufacturer: "Pentair", model: "TR10", recommendedSandLoad: 1, recommendedVitrocleanVfaLoad: 2, recommendedPebble: 3, fiftyBagVitroclean: 4, fiftyBagPebble: 5),PoolFilter(manufacturer: "Pentair", model: "TR20", recommendedSandLoad: 1, recommendedVitrocleanVfaLoad: 2, recommendedPebble: 3, fiftyBagVitroclean: 4, fiftyBagPebble: 5),PoolFilter(manufacturer: "Pentair", model: "TR30", recommendedSandLoad: 1, recommendedVitrocleanVfaLoad: 2, recommendedPebble: 3, fiftyBagVitroclean: 4, fiftyBagPebble: 5),PoolFilter(manufacturer: "Pentair", model: "TR40", recommendedSandLoad: 1, recommendedVitrocleanVfaLoad: 2, recommendedPebble: 3, fiftyBagVitroclean: 4, fiftyBagPebble: 5),PoolFilter(manufacturer: "Pentair", model: "TR50", recommendedSandLoad: 1, recommendedVitrocleanVfaLoad: 2, recommendedPebble: 3, fiftyBagVitroclean: 4, fiftyBagPebble: 5),PoolFilter(manufacturer: "Pentair", model: "TR60", recommendedSandLoad: 1, recommendedVitrocleanVfaLoad: 2, recommendedPebble: 3, fiftyBagVitroclean: 4, fiftyBagPebble: 5),PoolFilter(manufacturer: "Pentair", model: "TR70", recommendedSandLoad: 1, recommendedVitrocleanVfaLoad: 2, recommendedPebble: 3, fiftyBagVitroclean: 4, fiftyBagPebble: 5)], faqsList: [], isLoading: false, loaded: true, showOnboarding: false, error: nil), mode: "by_sand")
       
        
    }
}
