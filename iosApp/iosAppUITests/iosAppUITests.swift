//
//  iosAppUITests.swift
//  iosAppUITests
//
//  Created by Hunter Freas on 12/27/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import XCTest

final class iosAppUITests: XCTestCase {

    private var app: XCUIApplication!
    
    override func setUpWithError() throws {
        // Put setup code here. This method is called before the invocation of each test method in the class.

        // In UI tests it is usually best to stop immediately when a failure occurs.
        continueAfterFailure = false

        // In UI tests it’s important to set the initial state - such as interface orientation - required for your tests before they run. The setUp method is a good place to do this.
        app = XCUIApplication()
        app.launchArguments = ["isUiTesting"]
        app.launch()
    }

    override func tearDownWithError() throws {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
    }

    func testLoadHomeScreen() {
        XCTAssert(app.staticTexts["Contact Us"].exists)
        app.buttons["Contact Us"].tap()
        XCTAssert(app.staticTexts["Contact Us"].exists)
        app.textFields["Subject"].tap()
        
    }
    func testGoToFilterAndSelect() {
        //Home Screen
        XCTAssert(app.staticTexts["by filter"].exists)
        app.staticTexts["by filter"].tap()
        //By Filter Screen
        XCTAssert(app.buttons["select manufacturer"].exists)
        XCTAssert(app.buttons["select model"].exists)
        //Select manufacturer
        app.buttons["select manufacturer"].tap()
        XCTAssert(app.buttons["Pentair"].exists)
        app.buttons["Pentair"].tap()
        //Select model
        app.buttons["select model"].tap()
        app.buttons["TR40"].tap()
        XCTAssert(app.staticTexts["Recommend Sand Load Lbs."].exists)
        XCTAssert(app.staticTexts["Recommended Vitroclean (VFA) Load Lbs."].exists)
        XCTAssert(app.staticTexts["Recommended Vitroclean Pebble (VF8) Load Lbs."].exists)
        XCTAssert(app.staticTexts["50 lb. Bags of Vitroclean VFA"].exists)
        XCTAssert(app.staticTexts["50 lb. Bags of Pebble (VF8)."].exists)
    }
    func testGoToFiltersAndSelectModelFirst() {
        //Home Screen
        XCTAssert(app.staticTexts["by filter"].exists)
        app.staticTexts["by filter"].tap()
        //By Filter Screen
        XCTAssert(app.buttons["select manufacturer"].exists)
        XCTAssert(app.buttons["select model"].exists)
        app.buttons["select model"].tap()
        XCTAssert(app.staticTexts["Select a filter first."].exists)
    }
    
    func testGoToBySandTypeInNumber() {
        //Home Screen
        XCTAssert(app.staticTexts["by sand"].exists)
        app.staticTexts["by sand"].tap()
        //Calc - Sand
        XCTAssert(app.staticTexts["Start calculating now!"].exists)
        XCTAssert(app.textFields["number display"].exists)
        app.textFields["number display"].tap()
        app.textFields["number display"].typeText("50")
        XCTAssertEqual(app.textFields["number display"].value as! String, "50")
        
        XCTAssert(app.staticTexts["Recommend Sand Load Lbs."].exists)
        XCTAssert(app.staticTexts["Recommended Vitroclean (VFA) Load Lbs."].exists)
        XCTAssert(app.staticTexts["Recommended Vitroclean Pebble (VF8) Load Lbs."].exists)
        XCTAssert(app.staticTexts["50 lb. Bags of Vitroclean VFA"].exists)
        XCTAssert(app.staticTexts["50 lb. Bags of Pebble (VF8)."].exists)
    }
    
    func testGoToBySandTypeInNumberClearInput() {
        //Home Screen
        XCTAssert(app.staticTexts["by sand"].exists)
        app.staticTexts["by sand"].tap()
        //Calc - Sand
        XCTAssert(app.staticTexts["Start calculating now!"].exists)
        XCTAssert(app.textFields["number display"].exists)
        //Add numbers to number display
        app.textFields["number display"].tap()
        app.textFields["number display"].typeText("50")
        XCTAssertEqual(app.textFields["number display"].value as! String, "50")
        //Check that the stats are displayed after numbers
        XCTAssert(app.staticTexts["Recommend Sand Load Lbs."].exists)
        XCTAssert(app.staticTexts["Recommended Vitroclean (VFA) Load Lbs."].exists)
        XCTAssert(app.staticTexts["Recommended Vitroclean Pebble (VF8) Load Lbs."].exists)
        XCTAssert(app.staticTexts["50 lb. Bags of Vitroclean VFA"].exists)
        XCTAssert(app.staticTexts["50 lb. Bags of Pebble (VF8)."].exists)
        // Remove numbers from number display
        app.textFields["number display"].tap()
        app.textFields["number display"].doubleTap()

        // Press the delete key
        let deleteKey = app.keys["delete"]
        if deleteKey.exists {
            deleteKey.tap()
        } else {
            // If the delete key is not found, try sending a delete string
            let deleteString = String(repeating: XCUIKeyboardKey.delete.rawValue, count: 50) // Assuming maximum 50 characters
            app.textFields["number display"].typeText(deleteString)
        }

        XCTAssertEqual(app.textFields["number display"].value as! String, "0")
        //Check that the stats are displayed after numbers
        XCTAssert(!app.staticTexts["Recommend Sand Load Lbs."].exists)
        XCTAssert(!app.staticTexts["Recommended Vitroclean (VFA) Load Lbs."].exists)
        XCTAssert(!app.staticTexts["Recommended Vitroclean Pebble (VF8) Load Lbs."].exists)
        XCTAssert(!app.staticTexts["50 lb. Bags of Vitroclean VFA"].exists)
        XCTAssert(!app.staticTexts["50 lb. Bags of Pebble (VF8)."].exists)
        XCTAssert(app.staticTexts["Start calculating now!"].exists)
    }
    func testGoToFaqsAndClickOnFaqs() {
        //Home Screen
        XCTAssert(app.staticTexts["FAQs"].exists)
        app.staticTexts["FAQs"].tap()
        //FAQs Screen
        XCTAssert(app.staticTexts["FAQs"].exists)
        XCTAssert(app.staticTexts["How do I install this?"].exists)
        app.staticTexts["How do I install this?"].tap()
        XCTAssert(app.staticTexts["Why is this good"].exists)
        app.staticTexts["Why is this good"].tap()
        app.staticTexts["Why is this good"].tap()
    }
    
    func testGoToContactScreenEnterEmail() {
        //Home Screen
        XCTAssert(app.staticTexts["Contact Us"].exists)
        app.staticTexts["Contact Us"].tap()
        //Contact Us Screen
        XCTAssert(app.staticTexts["Contact Us"].exists)
        XCTAssert(app.textFields["Subject Textfield"].exists)
        XCTAssert(app.textViews["Message Textfield"].exists)
        XCTAssert(app.buttons["Send"].exists)
        XCTAssert(!app.buttons["Send"].isEnabled)
        app.textFields["Subject Textfield"].tap()
        app.textFields["Subject Textfield"].typeText("This is my subject test.")
        XCTAssertEqual(app.textFields["Subject Textfield"].value as! String, "This is my subject test.")
        app.textViews["Message Textfield"].tap()
        app.textViews["Message Textfield"].typeText("This is my message test.")
        XCTAssertEqual(app.textViews["Message Textfield"].value as! String, "This is my message test.")
        XCTAssert(app.buttons["Send"].isEnabled)
        app.buttons["Send"].tap()
    }
    
    func testTapToLeaveAppToTrivitroSite() {
        //Home Screen
        XCTAssert(app.buttons["link"].exists)
        app.buttons["link"].tap()
        //Contact Us Screen
        XCTAssert(app.staticTexts["Navigating out of app"].exists)
        XCTAssert(app.buttons["No"].exists)
        XCTAssert(app.buttons["Yes"].exists)
        app.buttons["No"].tap()
        
    }
    
    func testGoThroughEntireApp() {
        //Home Screen
        XCTAssert(app.staticTexts["by filter"].exists)
        app.staticTexts["by filter"].tap()
        //Calc - Filter mode
        XCTAssert(app.buttons["select manufacturer"].exists)
        XCTAssert(app.buttons["select model"].exists)
        app.navigationBars.buttons.element(boundBy: 0).tap()
        //Home screen
        XCTAssert(app.staticTexts["by cubic feet"].exists)
        app.staticTexts["by cubic feet"].tap()
        //Calc - Cubic feet mode
        XCTAssert(app.staticTexts["cubic feet"].exists)
        app.navigationBars.buttons.element(boundBy: 0).tap()
        //Home screen
        XCTAssert(app.staticTexts["by sand"].exists)
        app.staticTexts["by sand"].tap()
        //Calc - Cubic feet mode
        XCTAssert(app.staticTexts["sand needed"].exists)
        app.navigationBars.buttons.element(boundBy: 0).tap()
        //Home screen
        XCTAssert(app.staticTexts["FAQs"].exists)
        app.staticTexts["FAQs"].tap()
        //FAQs
        XCTAssert(app.staticTexts["FAQs"].exists)
        app.navigationBars.buttons.element(boundBy: 0).tap()
        //Home screen
        XCTAssert(app.staticTexts["Contact Us"].exists)
        app.staticTexts["Contact Us"].tap()
        //FAQs
        XCTAssert(app.staticTexts["Contact Us"].exists)
        app.navigationBars.buttons.element(boundBy: 0).tap()
    }
    

    func testLaunchPerformance() throws {
        if #available(macOS 10.15, iOS 13.0, tvOS 13.0, watchOS 7.0, *) {
            // This measures how long it takes to launch your application.
            measure(metrics: [XCTApplicationLaunchMetric()]) {
                XCUIApplication().launch()
            }
        }
    }
}
