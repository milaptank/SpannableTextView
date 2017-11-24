package com.mpt.android.spannabletextview;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.mpt.android.stv.Slice;
import com.mpt.android.stv.SpannableTextView;
import com.mpt.android.stv.callback.OnTextClick;

public class MainActivity extends AppCompatActivity implements OnTextClick {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SpannableTextView stvAddress = findViewById(R.id.stvAddress);
        SpannableTextView stvMarksTop = findViewById(R.id.stvMarksTop);
        SpannableTextView stvMarksDown = findViewById(R.id.stvMarksDown);
        SpannableTextView stvFormula = findViewById(R.id.stvFormula);
        SpannableTextView stvOffer = findViewById(R.id.stvOffer);
        SpannableTextView stvImageSpan = findViewById(R.id.stvImageSpan);

        stvAddress.addSlice(new Slice.Builder("WebMob Technologies\n1")
                .style(Typeface.BOLD)
                .build());
        stvAddress.addSlice(new Slice.Builder("st")
                .superscript()
                .build());
        stvAddress.addSlice(new Slice.Builder(" floor,111, N Market St. #300,\n")
                .textColor(Color.parseColor("#616161"))
                .build());
        stvAddress.addSlice(new Slice.Builder("San Jose, California ,\n95113, USA")
                .build());
        stvAddress.display();

        stvAddress.addSlice(new Slice.Builder("st")
                .superscript()
                .build());

        stvMarksTop.addSlice(new Slice.Builder("  9.5/10  ")
                .backgroundColor(Color.parseColor("#073680"))
                .textColor(Color.WHITE)
                .build());
        stvMarksTop.addSlice(new Slice.Builder(" excellent! ")
                .backgroundColor(Color.parseColor("#DFF1FE"))
                .textColor(Color.parseColor("#073680"))
                .style(Typeface.BOLD)
                .build());
        stvMarksTop.display();
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


        //***
        stvFormula.addSlice(new Slice.Builder("Area= \u03C0 r")
                .underline()
                .build());
        stvFormula.addSlice(new Slice.Builder("2")
                .superscript()
                .build());

        stvFormula.addSlice(new Slice.Builder("\n\n")
                .build());
        //*****
        stvFormula.addSlice(new Slice.Builder("Water= H")
                .build());
        stvFormula.addSlice(new Slice.Builder("2")
                .subscript()
                .build());
        stvFormula.addSlice(new Slice.Builder("O")
                .build());
        stvFormula.display();

        stvOffer.addSlice(new Slice.Builder("Price is \u20B9 ")
                .build());
        stvOffer.addSlice(new Slice.Builder("1000")
                .strike()
                .build());
        stvOffer.addSlice(new Slice.Builder(" Offer Price is \u20B9 500")
                .style(Typeface.BOLD)
                .textColor(Color.parseColor("#304ffe"))
                .build());

        stvOffer.addSlice(new Slice.Builder("\n\n")
                .build());
        stvOffer.addSlice(new Slice.Builder("http://milaptank.com")
                .setOnTextClick(this)
                .underline()
                .build());

        stvOffer.display();


        stvImageSpan.addSlice(
                new Slice.Builder("Show love by press  ")
                        .textSize(60)
                        .textColor(Color.BLUE)
                        .build());

        stvImageSpan.addSlice(
                new Slice.Builder("star")
                        .setImageResource(R.drawable.ic_star_border)
                        .textSize(40)
                        .build());


        stvImageSpan.display();
    }

    @Override
    public void onTextClick(View view, Slice slice) {
        Toast.makeText(this, "Website Clicked", Toast.LENGTH_LONG).show();
    }
}
