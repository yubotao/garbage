import java.util.*;

public class Main {
    
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        while(input.hasNext()){
            double money = input.nextDouble();
            System.out.println(method(money));
        }
    }
    
    public static String method(double money){
        StringBuilder sb = new StringBuilder();
        sb.append("人民币");
        int t = 0;
        double temp = money;
        while(temp > 1){
            t++;
            temp /= 10;
        }
        String[] units = {"","元","拾","佰","仟","万","拾","佰","仟","亿","拾","佰","仟","万","拾","佰"};
        String[] nums = {"零","壹","贰","叁","肆","伍","陆","柒","捌","玖"};
        while(t>0){
            int divisor = 1;
            for(int i=0; i<t-1; i++){
                divisor *= 10;
            }
            int num = (int) money/divisor;
            money = money%divisor;
            if(num>0){
                sb.append(nums[num]).append(units[t]);
            } else {
                String tempString = sb.toString();
                if(!"零".equals(tempString.substring(tempString.length() - 1)))
                    sb.append(nums[num]);
            }
            t--;
        }
        
        int decimal = (int) ((money+0.001) * 100) % 100;
        int tenth = decimal/10;
        int hundredth = decimal % 10;
        if(tenth == 0 && hundredth == 0){
            if(money == 0){
                sb.append("零元整");
            }else {
                sb.append("整");
            }
        }else {
            if(tenth == 0){
                sb.append(nums[hundredth]).append("分");
            }else {
                sb.append(nums[tenth]).append("角");
                if(hundredth > 0){
                    sb.append(nums[hundredth]).append("分");
                }
            }
        }
        String result = sb.toString();
        if("壹拾".equals(result.substring(3,5))){
            result = result.substring(0,3) + result.substring(4);
        }
        return result;
    }
    
}
