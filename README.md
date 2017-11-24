<p align="center">
<img alt="PRDownloader" src=https://raw.githubusercontent.com/milaptank/SpannableTextView/master/resources/spannabletextview.png />
</p>

### SpannableTextView is a custom `TextView` which lets you customize the styling of slice of your text or statment via `Spannables`, but without the hassle of having to deal directly with Spannable themselves.

[ ![Download](https://api.bintray.com/packages/milaptank/SpannableTextView/com.milaptank%3Aspannabletextview/images/download.svg) ](https://bintray.com/milaptank/SpannableTextView/com.milaptank%3Aspannabletextview/_latestVersion)

Add this in your build.gradle
```groovy
compile 'com.milaptank:stv:1.0.0'
```
## What can I do with SpannableTextView?
Examples of different styles. Each one is a **single** TextView.

<img src=https://raw.githubusercontent.com/milaptank/SpannableTextView/master/resources/device-2017-11-23-180045.png width=360 height=640 /><img src=https://raw.githubusercontent.com/milaptank/SpannableTextView/master/resources/device-2017-11-23-180122.png width=360 height=640 />


Example
--------
Generate the following style:

<img src=https://raw.githubusercontent.com/milaptank/SpannableTextView/master/resources/device-2017-11-23-18012222.png width=320 height=90/>

With the code below:

```java
SpannableTextView stvMarksDown = findViewById(R.id.stvMarksDown);
 stvMarksDown.addSlice(new Slice.Builder("  3.5/10  ")
                .backgroundColor(Color.parseColor("#800736"))
                .textColor(Color.WHITE)
                .setCornerRadius(13)
                .build());
        stvMarksDown.addSlice(new Slice.Builder(" horrible! ")
                .textColor(Color.parseColor("#073680"))
                .style(Typeface.BOLD)
                .build());
        stvMarksDown.display();
```



What kind of styles can I add?
--------
Right now you can customize the style of your text by using the following methods, which internally
get converted to the corresponding `Spannables`

| SpannableTextView         | Internal Span           |
| ------------- |:-------------:|
| textSize      | AbsoluteSizeSpan |
| textColor      | ForegroundColorSpan      |
| textSizeRelative | RelativeSizeSpan      |
| backgroundColor | BackgroundColorSpan      |
| style | StyleSpan      |
| underline | UnderlineSpan      |
| strike | StrikethroughSpan      |
| superscript | SuperscriptSpan      |
| subscript | SubscriptSpan      |
| setCornerRadius | RoundedBackgroundSpan(Custom)|
| setImageResource | ImageSpan|

What else can I do?
--------
Once you've created and displayed your SpannableTextView, you can modify the text contents of each
`Slice` individually. For example, if we want to change the `3.5/10` from the above example into
`6/10`, we could do the following:

```java
// grab the Slice at position 1
Slice slice = stvMarksDown.getSlice(1);
// modify it's text
slice.setText("6/10");
// you must always call display after you alter a Slice's text
stvMarksDown.display();
```


### Contributing to SpannableTextView
All pull requests are welcome, make sure to follow the [contribution guidelines](CONTRIBUTING.md)
when you submit pull request.
