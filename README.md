# Shimmer for Android

Shimmer-android is an Android port of [Facebook Shimmer library for iOS](https://github.com/facebook/Shimmer).

[![ScreenShot](shimmer.gif)](http://youtu.be/7EOsegp4J2o)

[http://youtu.be/7EOsegp4J2o](http://youtu.be/7EOsegp4J2o)

Examples of usage:
- show a loading indicator
- show a highlighted `TextView`.

## How to use

Gradle dependency:
```groovy
compile 'com.romainpiel.shimmer:library:1.2.0@aar'
```

Add a `ShimmerTextView` to your layout:
```xml
<com.romainpiel.shimmer.ShimmerTextView
    android:id="@+id/shimmer_tv"
    android:text="@string/shimmer"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:textColor="#444"
    android:textSize="50sp"/>
```

To start the animation:
```java
shimmer = new Shimmer();
shimmer.start(myShimmerTextView);
```

You may want to keep track of the shimmer instance after the animation is started if you want to stop it.

To stop it:
```java
shimmer.cancel();
```

## Customization

### Customizing the view

You can change the color of the reflection using the custom attribute `reflectionColor`:

```xml
<com.romainpiel.shimmer.ShimmerTextView
    android:id="@+id/shimmer_tv"
    android:text="@string/shimmer"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:textColor="#444"
    android:textSize="50sp"
    app:reflectionColor="#f00"/>
```

### Customizing the animation

The animation can be tweaked like a usual `ObjectAnimator`:
```java
// DON'T COPY THIS CODE TO YOUR PROJECT! It is just an example
shimmer.setRepeatCount(0)
    .setDuration(500)
    .setStartDelay(300)
    .setDirection(Shimmer.ANIMATION_DIRECTION_RTL)
    .setAnimatorListener(new Animator.AnimatorListener(){});
```

### Custom Shimmer view

Shimmer also includes a [`ShimmerButton`](https://github.com/RomainPiel/Shimmer-android/blob/master/library/src/main/java/com/romainpiel/shimmer/ShimmerButton.java). It works exactly the same way as a `ShimmerTextView`.
Have a look at how it's implemented and you can apply the same effect on your custom view if you need it.

## Sample

See the [sample](https://github.com/RomainPiel/Shimmer-android/tree/master/sample) for a common use of this library.

## License
```
Copyright 2014 Romain Piel

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

