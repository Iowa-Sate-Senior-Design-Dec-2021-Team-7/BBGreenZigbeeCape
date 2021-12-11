package webhost.http_wrappers;

import java.util.ArrayList;
import java.util.List;

public class UtilStringIntegerWrapper {
    
    private List<String> strings;
    
    private Integer integer;
    
    public UtilStringIntegerWrapper() {
        
        strings = null;
        integer = -1;
    }
    
    public UtilStringIntegerWrapper(ArrayList<String> strings, Integer integer) {
        this();
        
        this.setInteger(integer);
        this.setStrings(strings);
    }
    
    public Integer getInteger() {
        return integer;
    }
    
    public void setInteger(Integer integer) {
        this.integer = integer;
    }
    
    public List<String> getStrings() {
        return strings;
    }
    
    public void setStrings(ArrayList<String> strings) {
        this.strings = strings;
    }
}
