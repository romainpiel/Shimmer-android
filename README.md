# Shimmer for Android

Shimmer-android is an Android port of [Facebook Shimmer library for iOS](https://github.com/facebook/Shimmer).

[![ScreenShot](shimmer.png)](http://youtu.be/7EOsegp4J2o)

[http://youtu.be/7EOsegp4J2o](http://youtu.be/7EOsegp4J2o)

## How to use

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
Shimmer.animate(tv, new Animator.AnimatorListener() {
    @Override
    public void onAnimationStart(final Animator animation) {
        shimmerAnimator = animation;
    }

    @Override
    public void onAnimationEnd(Animator animation) {
        shimmerAnimator = null;
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }
});
```

You may want to keep track of the animator after the animation is started if you want to stop it.

To stop it:

```java
shimmerAnimator.cancel();
```

## Customization

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

