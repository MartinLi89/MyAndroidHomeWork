package com.sdk.paic.myandroidhomework.classones.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author ex-lisuyang001
 * @date 2017/11/7
 */

public class PartOne_view_1 extends View {

	public PartOne_view_1(Context context) {
		super(context);
	}

	public PartOne_view_1(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
	}

	public PartOne_view_1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	Paint paint = new Paint();

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		paint.setColor(Color.parseColor("#88880000"));
		paint.setAntiAlias(true);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(20);

		canvas.drawCircle(300, 300, 200, paint);
	}
}
