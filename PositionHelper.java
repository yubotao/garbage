import java.util.ArrayList;
import java.util.List;

/**
 * @Auther:
 * @Description: 向列表中插入数据并自动排序，保证不会出现两条记录的position相同
 * @Date: Created in 17:10 2019/5/21
 * @Modified By:
 */
public class PositionHelper {

    public static void main(String[] args) {
        List<T> original = new ArrayList<>();
        original.add(new T("One", 1));
        original.add(new T("Two", 2));
        original.add(new T("Three", 3));
        original.add(new T("Four", 4));
        original.add(new T("Five", 5));

        System.out.println(original);

        T newT = new T("Six", 2);
        autoPosition(newT, original);
        System.out.println(original);
    }

    public static <S extends U> void autoPosition(S s, List<S> sList){
        // 找到该条数据处于队列中的哪个位置
        int index = 0;
        for (S peerT : sList){
            // 新增的条目与队列中的原有数据位置相同
            if (s.getPosition() == peerT.getPosition()){
                // 修改原有数据的位置
                peerT.setPosition(s.getPosition() + 1);
                // 存储操作
                sList.add(index++, s);
                break;
            }
            index++;
        }
        int size = sList.size();
        // 比对到倒数第二个元素，如果相同，最后一个元素会修改值
        for (int i = index; i < size - 1; i++){
            // 如果检测到队列中不再存在相同位置的条目，则退出循环，完成所有操作
            if(!positionPlus(sList.get(i), sList.get(i+1))) break;
        }
    }

    private static <S extends U> boolean positionPlus(S s, S nextS){
        boolean flag = false;
        if (s.getPosition() == nextS.getPosition()){
            nextS.setPosition(s.getPosition() + 1);
            flag = true;
        }
        return flag;
    }

}

class T extends U{
    String name;

    T(String name, int position){
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "T{" +
                "name='" + name + '\'' +
                ", position=" + position +
                '}';
    }
}

class U {
    public int position;

    int getPosition(){
        return position;
    }
    void setPosition(int position){
        this.position = position;
    }
}
