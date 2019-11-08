package com.advertise.comparator;
/**
 * @author rahul
 *
 */
import java.util.Comparator;

import com.advertise.model.RowObject;

public class PlatformComparator implements Comparator<RowObject> {

	@Override
	public int compare(RowObject o1, RowObject o2) {
		return o1.getPlatform().compareTo(o2.getPlatform());
	}

}
