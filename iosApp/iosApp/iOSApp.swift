import SwiftUI
import shared

@main
struct iOSApp: App {
    private var appModule: any AppModule = AppModuleImpl()
    #if DEBUG
    init() {
        if CommandLine.arguments.contains("isUiTesting") {
            self.appModule = TestAppModule()
        }
    }
    #endif
	var body: some Scene {
		WindowGroup {
            ContentView(appModule: appModule)
		}
	}
}
