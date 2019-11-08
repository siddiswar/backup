package com.advertise.comparator;
/**
 * @author rahul
 *
 */
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.advertise.model.RowObject;

public class RowObjectComparator implements Comparator<RowObject> {
 
    private List<Comparator<RowObject>> listComparators;
 
    @SafeVarargs
    public RowObjectComparator(Comparator<RowObject>... comparators) {
        this.listComparators = Arrays.asList(comparators);
    }
 
    @Override
    public int compare(RowObject obj1, RowObject obj2) {
        for (Comparator<RowObject> comparator : listComparators) {
            int result = comparator.compare(obj1, obj2);
            if (result != 0) {
                return result;
            }
        }
        return 0;
    }
}