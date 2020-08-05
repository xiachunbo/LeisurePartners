package com.drops.tabbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.HashMap;

@SuppressLint("NewApi")
public class TabItem extends RelativeLayout implements Checkable {
	private ArrayList<Checkable> chechableList = new ArrayList<Checkable>();
	private HashMap<View, StateListDrawable> stateListDrawableMap = new HashMap<View, StateListDrawable>();

	private static final int[] CHECKED_STATE_SET = { android.R.attr.state_checked };



	public TabItem(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	public TabItem(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public TabItem(Context context) {
		super(context);
		init();
	}

	private void init() {
		super.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onChecked();
				if (OnClickListener != null)
					OnClickListener.onClick(v);
			}
		});
	}

	@Override
	public void addView(View child, int index,
			ViewGroup.LayoutParams params) {
		super.addView(child, index, params);
		setStates(child);
	}

	private void setStates(View child) {
		child.setClickable(false);
		Drawable drawable = child.getBackground();
		if (drawable != null && drawable instanceof StateListDrawable) {
			stateListDrawableMap.put(child, (StateListDrawable) drawable);
		}
		if (child instanceof Checkable) {
			chechableList.add((Checkable) child);
		}
		if (child instanceof ViewGroup) {
			final ViewGroup group = (ViewGroup) child;
			if (group.getChildCount() > 0) {
				for (int i = 0; i < group.getChildCount(); i++) {
					setStates(group.getChildAt(i));
				}
			}
		}
	}

	OnClickListener OnClickListener;

	@Override
	public void setOnClickListener(OnClickListener l) {
		OnClickListener = l;
	}

	boolean isChecked = false;

	@Override
	public void setChecked(boolean checked) {
		if (isChecked == checked)
			return;
		for (Checkable ca : chechableList) {
			ca.setChecked(checked);
		}
		if (checked) {
			for (View v : stateListDrawableMap.keySet()) {
				StateListDrawable drawable = stateListDrawableMap.get(v);
				drawable.setState(CHECKED_STATE_SET);
				v.setBackground(drawable.getCurrent());
			}
		} else {
			for (View v : stateListDrawableMap.keySet()) {
				v.setBackground(stateListDrawableMap.get(v));
			}
		}
		isChecked = checked;
	}

	private void onChecked() {
		if (getParent() instanceof TabGroup) {
			final TabGroup group = (TabGroup) getParent();
			group.check(getId());
		}
	}

	@Override
	public boolean isChecked() {
		return isChecked;
	}

	@Override
	public void setPressed(boolean pressed) {
		super.setPressed(pressed);
		if (!pressed) {
			setChecked(true);
		}
	}

	@Override
	public void toggle() {
		setChecked(!isChecked);
	}

}
