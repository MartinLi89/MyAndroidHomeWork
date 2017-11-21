package com.sdk.paic.myandroidhomework.classones.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
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


//		draw1(canvas);
//		draw2(canvas);
//		draw3(canvas);
		draw4(canvas);
	}

	private void draw4(Canvas canvas) {
		paint.reset();
		paint.setColor(Color.parseColor("#88880000"));
		paint.setAntiAlias(true);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(2);
		Path aaa = new Path();
		int width = getWidth();
		int height = getHeight();
		int x = width / 2 / 2 + 10;
		int y = height / 2 / 2 + 30;
		int r = Math.min(x, y) - 50;
		if (r < 0) {
			r = 0;
		}

		aaa.addCircle(x, y, r, Path.Direction.CW);
		aaa.addCircle(x, y, 10, Path.Direction.CCW);
		int x2 = width - x;
		int y2 = y;
		aaa.addCircle(x2, y2, r, Path.Direction.CW);
		aaa.addCircle(x2, y2, 10, Path.Direction.CCW);
//		drawArc(x - 130, y - 100, x + 130, y + 100, 90, 60, false, paint)
		aaa.addArc(width / 2 - 130, height / 2 - 100, width / 2 + 130, height / 2 + 100, 90, 60);
		canvas.drawPath(aaa, paint);


	}

	private void draw2(Canvas canvas) {
		int width = getWidth();
		int height = getHeight();
		int x = width / 2 / 2 + 10;
		int y = height / 2 / 2 + 30;


		int x2 = width - x;
		int y2 = y;
		int r = Math.min(x2, y2) - 50;
		if (r < 0) {
			r = 0;
		}
		int r2 = r;

		paint.reset();
		paint.setStrokeWidth(60);
		paint.setStyle(Paint.Style.STROKE);
		paint.setARGB(100, 100, 100, 100);
		canvas.drawCircle(x2, y2, r2, paint);
	}

	private void draw1(Canvas canvas) {
		paint.setColor(Color.parseColor("#88880000"));
		paint.setAntiAlias(true);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(20);

		int width = getWidth();
		int height = getHeight();

		//确定圆心位置
		int x = width / 2 / 2 + 10;
		int y = height / 2 / 2 + 30;
		int r = Math.min(x, y) - 50;
		if (r < 0) {
			r = 0;
		}

		canvas.drawCircle(x, y, r, paint);
	}

	/**
	 * 画嘴
	 *
	 * @param canvas
	 */
	private void draw3(Canvas canvas) {
		int width = getWidth();
		int height = getHeight();
		paint.reset();
		paint.setStyle(Paint.Style.STROKE);
//		paint.setStyle(Paint.Style.FILL);
		paint.setStrokeWidth(10);

		paint.setColor(Color.parseColor("#88009900"));
		int x = width / 2;
		int y = height / 2 + 100;
		canvas.drawArc(x - 130, y - 100, x + 130, y + 100, 90, 60, false, paint);

//		canvas.drawArc(x - 100, y - 100, x + 100, y + 100,20,120,true,paint);

	}
}
