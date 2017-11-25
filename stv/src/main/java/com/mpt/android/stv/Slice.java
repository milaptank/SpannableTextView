package com.mpt.android.stv;

import android.graphics.Color;
import android.graphics.Typeface;

import com.mpt.android.stv.callback.OnTextClick;

public class Slice {

    private String text;
    private int textColor;
    private final int textSize;
    private final int backgroundColor;
    private final float textSizeRelative;
    private final int style;
    private final boolean underline;
    private final boolean superscript;
    private final boolean strike;
    private final boolean subscript;
    private final int sliceId;
    private final int imageResource;
    private final OnTextClick onTextClick;
    public static int DEFAULT_ABSOLUTE_TEXT_SIZE;
    public static float DEFAULT_RELATIVE_TEXT_SIZE = 1;
    private final boolean isRounded;
    private final boolean isCircle;
    private int cornerRadius;
    private int circleRadius;


    public Slice(Builder builder) {
        this.text = builder.text;
        this.textSize = builder.textSize;
        this.textColor = builder.textColor;
        this.backgroundColor = builder.backgroundColor;
        this.textSizeRelative = builder.textSizeRelative;
        this.style = builder.style;
        this.underline = builder.underline;
        this.superscript = builder.superscript;
        this.subscript = builder.subscript;
        this.strike = builder.strike;
        this.onTextClick = builder.onTextClick;
        this.sliceId = builder.sliceId;
        this.imageResource = Builder.imageResource;
        this.isRounded = builder.isRounded;
        this.isCircle = builder.isCircle;
        this.cornerRadius = builder.cornerRadius;
        this.circleRadius = builder.circleRadius;
    }

    public boolean isRounded() {
        return isRounded;
    }

    public boolean isCircle() {
        return isCircle;
    }

    public int getCornerRadius() {
        return cornerRadius;
    }

    public int getCircleRadius() {
        return circleRadius;
    }


    public static class Builder {


        // required
        private final String text;

        // optional
        private static int imageResource;
        private int textSize = DEFAULT_ABSOLUTE_TEXT_SIZE;
        private int textColor = Color.BLACK;
        private int backgroundColor = -1;
        private float textSizeRelative = DEFAULT_RELATIVE_TEXT_SIZE;
        private int style = Typeface.NORMAL;
        private boolean underline = false;
        private boolean strike = false;
        private boolean superscript = false;
        private boolean subscript = false;
        private OnTextClick onTextClick;
        private int sliceId;
        private boolean isRounded;
        private boolean isCircle;
        private int cornerRadius;
        private int circleRadius;

        /**
         * Creates a new Builder for this Piece.
         *
         * @param text the text of this Piece
         */
        public Builder(String text) {
            this.text = text;
        }


        /**
         * Sets the absolute text size.
         *
         * @param sliceId text sliceId
         * @return a Builder
         */
        public Builder setSliceId(int sliceId) {
            this.sliceId = sliceId;
            return this;
        }

        public Builder setCornerRadius(int cornerRadius) {
            this.isRounded = true;
            this.cornerRadius = cornerRadius;
            return this;
        }

        /**
         * Sets the absolute text size.
         *
         * @param imageResource text imageResource
         * @return a Builder
         */
        public Builder setImageResource(int imageResource) {
            Builder.imageResource = imageResource;
            return this;
        }


        /**
         * Sets the absolute text size.
         *
         * @param textSize text size in pixels
         * @return a Builder
         */
        public Builder textSize(int textSize) {
            this.textSize = textSize;
            return this;
        }

        /**
         * Sets the text color.
         *
         * @param textColor the color
         * @return a Builder
         */
        public Builder textColor(int textColor) {
            this.textColor = textColor;
            return this;
        }

        /**
         * Sets the background color.
         *
         * @param backgroundColor the color
         * @return a Builder
         */
        public Builder backgroundColor(int backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }

        /**
         * Sets the relative text size.
         *
         * @param textSizeRelative relative text size
         * @return a Builder
         */
        public Builder textSizeRelative(float textSizeRelative) {
            this.textSizeRelative = textSizeRelative;
            return this;
        }

        /**
         * Sets the text click event.
         *
         * @param onTextClick text click
         * @return a Builder
         */
        public Builder setOnTextClick(OnTextClick onTextClick) {
            this.onTextClick = onTextClick;
            return this;
        }

        /**
         * Sets a style to this Slice.
         *
         * @param style see {@link android.graphics.Typeface}
         * @return a Builder
         */
        public Builder style(int style) {
            this.style = style;
            return this;
        }

        /**
         * Underlines this Piece.
         *
         * @return a Builder
         */
        public Builder underline() {
            this.underline = true;
            return this;
        }

        /**
         * Strikes this Piece.
         *
         * @return a Builder
         */
        public Builder strike() {
            this.strike = true;
            return this;
        }

        /**
         * Sets this Piece as a superscript.
         *
         * @return a Builder
         */
        public Builder superscript() {
            this.superscript = true;
            return this;
        }

        /**
         * Sets this Piece as a subscript.
         *
         * @return a Builder
         */
        public Builder subscript() {
            this.subscript = true;
            return this;
        }

        /**
         * Creates a with the customized
         * parameters.
         *
         * @return a Slice
         */
        public Slice build() {
            return new Slice(this);
        }

        public Builder setCircleRadius(int circleRadius) {
            this.circleRadius = circleRadius;
            this.isCircle = true;
            return this;
        }
    }


    /**
     * Sets the text color of this Piece. If you're creating a new Slice, you should do so using it's
     * <p>
     * Use this method if you want to change the text color of an existing Slice that is already
     * displayed. After doing so, you MUST call {@code display()} for the changes to show up.
     *
     * @param textColor of text (it is NOT android Color resources ID, use getResources().getColor(R.color.colorId) for it)
     */
    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    /**
     * Sets the text of this Slice. If you're creating a new Slice, you should do so using it's
     * <p>
     * Use this method if you want to modify the text of an existing Slice that is already
     * displayed. After doing so, you MUST call {@code display()} for the changes to show up.
     *
     * @param text the text to display
     */
    public void setText(String text) {
        this.text = text;
    }


    public String getText() {
        return text;
    }

    public int getTextColor() {
        return textColor;
    }

    public int getTextSize() {
        return textSize;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public float getTextSizeRelative() {
        return textSizeRelative;
    }

    public int getStyle() {
        return style;
    }

    public boolean isUnderline() {
        return underline;
    }

    public boolean isSuperscript() {
        return superscript;
    }

    public boolean isStrike() {
        return strike;
    }

    public boolean isSubscript() {
        return subscript;
    }

    public OnTextClick getOnTextClick() {
        return onTextClick;
    }

    public int getSliceId() {
        return sliceId;
    }

    public int getImageResource() {
        return imageResource;
    }


}