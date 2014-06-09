./gradlew assembleDebug
adb uninstall com.swiggins.today
adb install app/build/apk/app-debug-unaligned.apk
