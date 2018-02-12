package com.jsdroid.codeview;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;

/**
 * Created by Administrator on 2018/2/11.
 */

public class CodeEditor extends HorizontalScrollView {
	ScrollView mScrollView;
	CodeText mCodeText;
	int mCodeTextMinHeight;
	int mCodeTextMinWidth;

	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	public CodeEditor(Context context) {
		super(context);
		init();
	}

	public CodeEditor(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		setBackgroundColor(0XFF333333);
		mScrollView = new ScrollView(getContext()) {
			@Override
			protected void onScrollChanged(int l, int t, int oldl, int oldt) {
				super.onScrollChanged(l, t, oldl, oldt);
				mCodeText.postInvalidate();
			}
		};
		android.view.ViewGroup.LayoutParams params = new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		mScrollView.setLayoutParams(params);
		mCodeText = new CodeText(getContext());
		mCodeText.scrollView = mScrollView;
		mScrollView.addView(mCodeText);
		addView(mScrollView);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		int codeWidth = getWidth() - getPaddingLeft() - getPaddingRight();
		int codeHeight = getHeight() - getPaddingTop() - getPaddingBottom();
		if (mCodeTextMinHeight != codeHeight || mCodeTextMinWidth != codeWidth) {
			mCodeTextMinWidth = codeWidth;
			mCodeTextMinHeight = codeHeight;
			mCodeText.setMinWidth(mCodeTextMinWidth);
			mCodeText.setMinHeight(mCodeTextMinHeight);
			invalidate();
		}
		super.onDraw(canvas);
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		//mCodeText.postInvalidate();
	}

	public CodeText getCodeText() {
		return mCodeText;
	}

}
