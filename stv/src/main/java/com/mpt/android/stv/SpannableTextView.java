package com.mpt.android.stv;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.AppCompatTextView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.UnderlineSpan;
import android.util.AttributeSet;
import android.view.View;

import com.mpt.android.stv.span.RoundedBackgroundSpan;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Milap Tank
 * @Email milaptank@gmail.com
 * @Project SpannableTextView - Android
 * @desc SpannableTextView.java  is for extra power to #TextView
 * @since 23/11/17  3:48 PM
 */

public class SpannableTextView extends AppCompatTextView {


    private List<Slice> sliceList;
    Context context;

    private void init() {
        sliceList = new ArrayList<>();
        Slice.DEFAULT_ABSOLUTE_TEXT_SIZE = (int) getTextSize();
    }

    public SpannableTextView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public SpannableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public SpannableTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();

    }

    /**
     * Use this method to add a {@link Slice} to a SuperTextView.
     * Each {@link Slice } is added sequentially, so the
     * order you call this method matters.
     *
     * @param slice the Slice
     */
    public void addSlice(Slice slice) {
        sliceList.add(slice);
    }

    /**
     * Adds a Slice at this specific location. The underlying data structure is a
     * {@link java.util.List}, so expect the same type of behaviour.
     *
     * @param slice    the Slice to add.
     * @param location the index at which to add.
     */
    public void addSlice(Slice slice, int location) {
        sliceList.add(location, slice);
    }

    /**
     * Replaces the Slice at the specified location with this new Slice. The underlying data
     * structure is a {@link java.util.List}, so expect the same type of behaviour.
     *
     * @param newSlice the Slice to insert.
     * @param location the index at which to insert.
     */
    public void replaceSliceAt(int location, Slice newSlice) {
        sliceList.set(location, newSlice);
    }

    /**
     * Removes the Slice at this specified location. The underlying data structure is a
     * {@link java.util.List}, so expect the same type of behaviour.
     *
     * @param location the index of the Slice to remove
     */
    public void removeSlice(int location) {
        sliceList.remove(location);
    }


    /**
     * Get a specific {@link Slice} in position index.
     *
     * @param location position of Piece (0 based)
     * @return Slice o null if invalid index
     */
    public Slice getSlice(int location) {
        if (location >= 0 && location < sliceList.size()) {
            return sliceList.get(location);
        }
        return null;
    }

    /**
     * Call this method when you're done adding {@link Slice}s
     * and want this TextView to display the final, styled version of it's String contents.
     * <p>
     * You MUST also call this method whenever you make a modification to the text of a Slice that
     * has already been displayed.
     */
    public void display() {

        // generate the final string based on the pieces
        StringBuilder builder = new StringBuilder();
        for (Slice slice : sliceList) {
            builder.append(slice.getText());
        }

        // apply spans
        int cursor = 0;
        SpannableString finalString = new SpannableString(builder.toString());
        for (Slice slice : sliceList) {
            applySpannableTo(slice, finalString, cursor, cursor + slice.getText().length());
            cursor += slice.getText().length();
        }

        // set the styled text
        setText(finalString);
    }

    private void applySpannableTo(final Slice slice, SpannableString finalString, int start, int end) {


        if (slice.isSubscript()) {
            finalString.setSpan(new SubscriptSpan(), start, end,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }


        if (slice.isSuperscript()) {
            finalString.setSpan(new SuperscriptSpan(), start, end,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        if (slice.isStrike()) {
            finalString.setSpan(new StrikethroughSpan(), start, end,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        if (slice.isUnderline()) {
            finalString.setSpan(new UnderlineSpan(), start, end,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        // set click on text
        if (slice.getOnTextClick() != null) {
            ClickableSpan clickableSpan = new ClickableSpan() {

                @Override
                public void updateDrawState(TextPaint ds) {
                    ds.setColor(Color.TRANSPARENT);
                    ds.setUnderlineText(slice.isUnderline());
                }

                @Override
                public void onClick(View view) {
                    view.invalidate();
                    slice.getOnTextClick().onTextClick(view, slice);
                }
            };

            finalString.setSpan(clickableSpan, start, end,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            setMovementMethod(LinkMovementMethod.getInstance());
        }


        // TODO: 27/08/17 QuoteSpan Spannable


        if (slice.getImageResource() != 0) {
            finalString.setSpan(new ImageSpan(context, slice.getImageResource())
                    , start
                    , end
                    , Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            new Slice.Builder("").setImageResource(0);

        }


        if (slice.isRounded())
            finalString.setSpan(new RoundedBackgroundSpan(slice.getBackgroundColor(), slice.getTextColor(),
                    slice.getCornerRadius()), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        //https://stackoverflow.com/a/33336650/1293313 for rounded corner
        //url span

       /* if (slice.isUrl())
            finalString.setSpan(new URLSpan(finalString.toString()), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
*/
        // style
        finalString.setSpan(new StyleSpan(slice.getStyle()), start, end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // absolute text size
        finalString.setSpan(new AbsoluteSizeSpan(slice.getTextSize()), start, end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // relative text size
        finalString.setSpan(new RelativeSizeSpan(slice.getTextSizeRelative()), start, end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // text color
        finalString.setSpan(new ForegroundColorSpan(slice.getTextColor()), start, end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // background color
        if (slice.getBackgroundColor() != -1) {
            finalString.setSpan(new BackgroundColorSpan(slice.getBackgroundColor()), start, end,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }


    }

    /**
     * Resets the styling of this view and sets it's content to an empty String.
     */
    public void reset() {
        sliceList = new ArrayList<>();
        setText("");
    }

    /**
     * Change text color of all pieces of textview.
     */
    public void changeTextColor(int textColor) {
        for (Slice slice : sliceList) {
            slice.setTextColor(textColor);
        }
        display();
    }


}
