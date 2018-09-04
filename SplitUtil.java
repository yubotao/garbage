import java.util.List;

/**
 * @Auther: yubt
 * @Description:
 * @Date: Created in 16:13 2018/9/4
 * @Modified By:
 */
public class SplitUtil {

    public String arrayToString(String[] array, String sign){
        StringBuffer union = new StringBuffer();
        if (array.length > 0){
            union.append(array[0]);
        }
        for (int i = 1; i < array.length; i++){
            union.append(sign + array[i]);
        }

        return  union.toString();
    }

    public String listToString(List<String> list, String sign){
        StringBuffer union = new StringBuffer();
        int size = list.size();
        if (size > 0){
            union.append(list.get(0));
        }
        for (int i = 1; i < size; i++){
            union.append(sign + list.get(i));
        }

        return union.toString();
    }

    public String[] stringToArray(String input, String sign){
        return input.split(sign);
    }

}
