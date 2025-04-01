# Openterface Mini-KVM

Welcome to the **Openterface Mini-KVM** project! Our crowdfunding campaign is live on [Crowd Supply](https://www.crowdsupply.com/techxartisan/openterface-mini-kvm). Please consider supporting us by backing our project!

![Pre-launch Poster](https://pbs.twimg.com/media/GInpcabbYAAsP9J?format=jpg&name=medium)

## Overview

Openterface Mini-KVM is an open-source hardware and software solution designed to provide basic KVM (Keyboard, Video, Mouse) functionality for controlling devices via an Android-based interface. This repository contains the Android application source code, build configurations, and supporting scripts to set up and deploy the project.

We are committed to open hardware and open-source software, licensed under the [GNU Affero General Public License v3](LICENSE).

## Features

- **Basic KVM Operations**: Control mouse movement, perform left/right clicks via touchscreen, and input text using an on-screen keyboard.
- **Absolute Mouse Control**: Fully supported (relative mouse control is under development).
- **Function Key Support**: Use function keys for enhanced control.
- **Image Adjustments**: Adjust brightness, contrast, and hue of the video output.
- **Open Source**: All code and configurations are freely available under the AGPLv3 license.

## Prerequisites

To build and run the Openterface Mini-KVM Android app, ensure you have the following:

- **Android SDK**: Installed and configured (e.g., `C:\Users\YourUser\AppData\Local\Android\Sdk`).
- **Java Development Kit (JDK)**: Version 8 or higher, with `JAVA_HOME` set in your environment.
- **Gradle**: Version 7.1.2 or compatible (managed via the Gradle Wrapper).
- **Android Device**: Minimum API 19 (Android 4.4 KitKat), with USB debugging enabled.

## Installation

### 1. Clone the Repository

```bash
git clone https://github.com/techxartisan/openterface-mini-kvm.git
cd openterface-mini-kvm
```

### 2. Configure Local Properties

Create or edit the `local.properties` file in the project root to specify your Android SDK path and signing keys (if applicable). Example:

```properties
sdk.dir=C:\\Users\\YourUser\\AppData\\Local\\Android\\Sdk
keystore.jks=C:\\path\\to\\your\\keystore.jks
SIGNING_STORE_PASSWORD=your_store_password
SIGNING_KEY_ALIAS=your_key_alias
SIGNING_KEY_PASSWORD=your_key_password
```

**Note**: Do not commit `local.properties` to version control as it contains sensitive information.

### 3. Build the Project

Use the Gradle Wrapper to build the project. On Unix-based systems (Linux/macOS):

```bash
./gradlew build
```

On Windows:

```cmd
gradlew.bat build
```

This will compile the app, including the `:libuvccamera` and `:app` modules, and generate the APK.

### 4. Install the APK

Connect your Android device via USB and install the built APK:

```bash
./gradlew installDebug
```

Alternatively, locate the APK in `app/build/outputs/apk/debug/` and install it manually using ADB:

```bash
adb install app-debug.apk
```

## Configuration

### Gradle Settings

The project uses Gradle for build management. Key configurations are defined in the following files:

- **`gradle-wrapper.properties`**: Specifies the Gradle version (e.g., 7.1.2).
- **`settings.gradle`**: Includes the `:libuvccamera` and `:app` modules.
- **`build.gradle` (root)**: Defines repositories, dependencies (e.g., `com.android.tools.build:gradle:7.1.2`), and build variables:
    - `versionCompiler = 34`
    - `versionTarget = 34`
    - `versionMin = 19`
    - Java compatibility: `VERSION_1_8`
- **`gradle.properties`**: JVM arguments and build optimizations:
    - `org.gradle.jvmargs=-Xmx2048m -XX:+HeapDumpOnOutOfMemoryError -Dfile.encoding=UTF-8`
    - `org.gradle.daemon=true`
    - `org.gradle.parallel=true`
    - `org.gradle.caching=true`
    - AndroidX and Jetifier enabled.

### Publishing to Maven

The project supports publishing to Maven repositories (e.g., Sonatype OSSRH). Configure the `build.gradle` in your module as shown in the provided example. Set `ossrhUsername` and `ossrhPassword` in your root project’s `gradle.properties` or as environment variables.

Example:

```gradle
publishing {
    publications {
        release(MavenPublication) {
            artifactId = 'openterface-mini-kvm'
            from components.release
            artifact sourcesJar
        }
    }
    repositories {
        maven {
            url = version.endsWith('SNAPSHOT') ? 'https://s01.oss.sonatype.org/content/repositories/snapshots/' : 'https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/'
            credentials {
                username = "${sonatypeUsername}"
                password = "${sonatypePassword}"
            }
        }
    }
}
signing {
    sign publishing.publications
}
```

Run the following to publish:

```bash
./gradlew publish
```

### Ignoring Files

The `.gitignore` file ensures build artifacts and sensitive files are excluded from version control. Key exclusions include:

- Build outputs: `build/`, `out/`, `*.apk`
- Local configs: `local.properties`
- IDE files: `.idea/`, `*.iml`
- Gradle caches: `.gradle/`

## Usage

1. **Connect the Device**: Plug your target device into the Mini-KVM hardware and connect the Mini-KVM to your Android device via USB.
2. **Launch the App**: Open the Openterface Mini-KVM app on your Android device.
3. **Control the Target**: Use the touchscreen for mouse control, on-screen keyboard for text input, and adjust image settings as needed.

## Contributing

We welcome contributions! Check out our [Contributing Guide](https://openterface.com/contributing/) for details. To get involved:

- Fork the repository.
- Create a feature branch (`git checkout -b feature/your-feature`).
- Commit your changes (`git commit -m "Add your feature"`).
- Push to the branch (`git push origin feature/your-feature`).
- Open a Pull Request.

For questions or collaboration, email us at [info@techxartisan.com](mailto:info@techxartisan.com).

## License

This project is licensed under the [GNU Affero General Public License v3](LICENSE). See the full license text for details.

## Acknowledgments

- **UVCCamera Library**: Integrates UVC webcam support on non-rooted Android devices under the Apache License 2.0.
- **Crowd Supply Backers**: Thank you for supporting our campaign!

Stay tuned for updates as we refine the code and prepare for the official release!