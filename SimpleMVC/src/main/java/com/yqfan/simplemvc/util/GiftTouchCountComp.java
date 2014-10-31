package com.yqfan.simplemvc.util;

import java.util.Comparator;

import com.yqfan.simplemvc.model.Gift;

public class GiftTouchCountComp implements Comparator<Gift> {

	@Override
	public int compare(Gift o1, Gift o2) {
		if (o1.getTouchCount() > o2.getTouchCount()) return -1;
		else if (o1.getTouchCount() < o2.getTouchCount()) return 1;
		else return 0;
	}

}
