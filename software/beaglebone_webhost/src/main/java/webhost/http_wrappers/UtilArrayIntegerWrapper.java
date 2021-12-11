package webhost.http_wrappers;

import java.util.ArrayList;
import java.util.List;

public class UtilArrayIntegerWrapper<T> {
    
    private List<T> array;
    
    private Integer integer;
    
    public UtilArrayIntegerWrapper() {
    
        array = null;
        integer = -1;
    }
    
    public UtilArrayIntegerWrapper(ArrayList<T> array, Integer integer) {
        this();
        
        this.setInteger(integer);
        this.setArray(array);
    }
    
    public Integer getInteger() {
        return integer;
    }
    
    public void setInteger(Integer integer) {
        this.integer = integer;
    }
    
    public List<T> getArray() {
        return array;
    }
    
    public void setArray(ArrayList<T> array) {
        this.array = array;
    }
}
