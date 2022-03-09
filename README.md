# Gemischte Tüte - Component Library

"Gemischte Tüte" Component Library is a set of [Jetpack Compose](https://developer.android.com/jetpack/compose) components for Android developer. 
This Library has no intention of beeing a full - feature complete Component Library. It is more like a random set of sometimes usefull components which are missing in the default libraries. 

## Compose versions

In Future releases we want to build different versions for different jetpack compose Major Versions. But at the moment the components are fixed to Compose 1.1.

| Compose Version | Library Version |
| :---: | :-: |
| Compose 1.1 | [![](https://jitpack.io/v/VanFanelia/gemischte-tuete-component-library.svg)](https://jitpack.io/#VanFanelia/gemischte-tuete-component-library) |

## How to use this library

**Step 1. Add the JitPack repository to your build file**

Add it in your root build.gradle at the end of repositories:

```
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```

**Step 2. Add the dependency for the component you need**

Every component has it own dependency, so you pick just the components you need and add the dependency in your app/module build.gradle file
```
dependencies {
  implementation 'com.github.VanFanelia:gemischte-tuete-component-library:<Component>:<Version>'
}
```

If you use **maven**, **sbt**, **leiningen** see [HowTo on JitPack.io](https://jitpack.io/#VanFanelia/gemischte-tuete-component-library#howto)

## Why the name

In urban regions of germany small kids often buy sweets and candy from kiosk. To get a mixed bag of random brands you need to order a "gemischte Tüte" (mixed bag). This component library has no intention to be a complete design system or a Component Library like Bootstrap or Material UI. It is just a collection of beautiful components without a relation to each other. The creators wants to share these components and find a single place to present them. This is "Gemischte Tüte Component Library". A mixed bag of components for different tastes.

# Components

## Plasma background

Todo: add description here


---

# Contributions
Please contribute! we will glady review any pull request and want to add your component if you like.

# Build for Release
Change Version number in the root gradle.build file. Commit to main. Tag Version in Github to force jitpack to build the new version.

# Lizenz

```
MIT License

Copyright (c) 2022 VanFanelia

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
