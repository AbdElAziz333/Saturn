<img src="src/main/resources/logo.png" width="128">

# Saturn for Minecraft Forge

![Github License](https://img.shields.io/github/license/AbdElAziz333/Saturn)
![Github Issues](https://img.shields.io/github/issues/AbdElAziz333/Saturn)
![Curseforge Downloads](https://cf.way2muchnoise.eu/670986.svg)

Saturn is a free and open-source performance mod for Minecraft Forge designed to optimize Minecraft's memoy usage, It works on both the **client and server**, and **doesn't require the mod to be installed on both sides, However, the benefits of running Saturn are pretty small as of the moment.

### License

Canary is licensed under GNU LGPLv3, a free and open-source license. For more information, please see the
[license file](LICENSE.txt).

### Issues and Feature Requests
If you'd like to get help with the mod, feel free to open an issue here on GitHub, and if you want to propose new features or otherwise contribute to the mod, we will gladly accept pull requests, as well!

### Building from sources
Support is not provided for setting up build environments or compiling the mod. We ask that users who are looking to get their hands dirty with the code have a basic understanding of compiling Java/Gradle projects. The basic overview is provided here for those familiar.

#### Requirements
JDK 17
You can either install this through a package manager such as Chocolatey on Windows or SDKMAN! on other platforms. If you'd prefer to not use a package manager, you can always grab the installers or packages directly from AdoptOpenJDK.
Gradle 7 or newer (optional)
The Gradle wrapper is provided in this repository can be used instead of installing a suitable version of Gradle yourself. However, if you are building many projects, you may prefer to install it yourself through a suitable package manager as to save disk space and to avoid many Gradle daemons sitting around in memory.
Building with Gradle
Canary uses a typical Gradle project structure and can be built by simply running the default build task. After Gradle finishes building the project, you can find the build artifacts (typical mod binaries, and their sources) in build/libs.

Tip: If this is a one-off build, and you would prefer the Gradle daemon does not stick around in memory afterwards, try adding the --no-daemon flag to ensure that the daemon is torn down after the build is complete. However, subsequent builds of the project will start more slowly if the Gradle daemon is not available to be re-used.
