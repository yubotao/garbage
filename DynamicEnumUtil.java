package ybt;

import sun.reflect.ConstructorAccessor;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * @Auther:ybt
 * @Description: 通过外部数据源，创建一个动态的Enum数组;  实际上，也可以实现向已有enum追加元素，不过更加复杂
 *                参考资料：https://blog.gotofinal.com/java/diorite/breakingjava/2017/06/24/dynamic-enum.html
 * @Date: Created in 14:31  2020/09/29
 * @Modified By:
 */
public class DynamicEnumUtil {

    public enum EventType {
        ;

        String value;
        String label;

        EventType(String value, String label) {
            this.value = value;
            this.label = label;
        }
    }

    class EventTypeObj{
        String name;
        String value;
        String label;

        public EventTypeObj(String name, String value, String label){
            this.name = name;
            this.value = value;
            this.label = label;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    private static ArrayList getList(){
        ArrayList enumList = new ArrayList();
        enumList.add(new DynamicEnumUtil().new EventTypeObj("zy","1", "占压"));
        enumList.add(new DynamicEnumUtil().new EventTypeObj("sg","2", "施工"));
        enumList.add(new DynamicEnumUtil().new EventTypeObj("wz","3", "违章用水"));
        enumList.add(new DynamicEnumUtil().new EventTypeObj("wx","4", "维修"));
        enumList.add(new DynamicEnumUtil().new EventTypeObj("tx","5", "塌陷"));
        enumList.add(new DynamicEnumUtil().new EventTypeObj("qt","6", "其它"));
        return enumList;
    }

    private static <E extends Enum<E>> Enum<E>[] getDynamicEnumSet(Class<E> enumClass, ArrayList<EventTypeObj> arrayList) throws Exception {
        Constructor<?> constructor = enumClass.getDeclaredConstructors()[0];
        constructor.setAccessible(true);
        Field constructorAccessorField = Constructor.class.getDeclaredField("constructorAccessor");
        constructorAccessorField.setAccessible(true);
        ConstructorAccessor ca = (ConstructorAccessor) constructorAccessorField.get(constructor);
        if (ca == null) {
            Method acquireConstructorAccessorMethod = Constructor.class.getDeclaredMethod("acquireConstructorAccessor");
            acquireConstructorAccessorMethod.setAccessible(true);
            ca = (ConstructorAccessor) acquireConstructorAccessorMethod.invoke(constructor);
        }
        int size = arrayList.size();
        Enum<E>[] enumArray = new Enum[size];
        for(int i=0; i<size; i++){
            Object[] params = new Object[4];
            params[0] = arrayList.get(i).getName();
            params[1] = i+1;
            params[2] = arrayList.get(i).getValue();
            params[3] = arrayList.get(i).getLabel();
            enumArray[i] = enumClass.cast(ca.newInstance(params));
        }
        return enumArray;
    }

    public static void main(String[] args) throws Exception{
        Enum<EventType>[] enumArray = getDynamicEnumSet(EventType.class, getList());
        for (int i=0; i<enumArray.length; i++){
            EventType eventType = (EventType) enumArray[i];
            System.out.print("name :" + eventType.name());
            System.out.print(", label : " + eventType.label);
            System.out.println(", value : " + eventType.value);
        }
    }

}
