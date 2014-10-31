package com.yqfan.simplemvc.util;

import java.util.Comparator;

import com.yqfan.simplemvc.model.MyUser;

public class UserTotalVoteComp implements Comparator<MyUser> {

	@Override
	public int compare(MyUser o1, MyUser o2) {
		if (o1.getTotalVotes() > o2.getTotalVotes()) return -1;
		else if (o1.getTotalVotes() < o2.getTotalVotes()) return 1;
		else return 0;
	}

}
