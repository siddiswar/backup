package com.advertise.comparator;
/**
 * @author rahul
 *
 */
import java.util.Comparator;

import com.advertise.model.RowObject;

public class OSVersionComparator implements Comparator<RowObject> {

	@Override
	public int compare(RowObject o1, RowObject o2) {
		return o1.getOsVersion().compareTo(o2.getOsVersion());
	}

}
