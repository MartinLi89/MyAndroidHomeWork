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

		int width = getWidth();
		int height = getHeight();

		//确定圆心位置
		int x = width / 2 / 2+10;
		int y = height / 2 / 2+30;
		int r = Math.min(x, y) - 50;
		if (r < 0) {
			r = 0;
		}

		canvas.drawCircle(x, y, r, paint);

		int x2 = width - x;
		int y2 = y;
		int r2 = r;

		paint.reset();
		paint.setStrokeWidth(60);
		paint.setStyle(Paint.Style.STROKE);
		paint.setARGB(100,100,100,100);
		canvas.drawCircle(x2, y2, r2, paint);
	}
}
