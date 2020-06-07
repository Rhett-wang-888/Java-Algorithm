import java.util.ArrayList;
import java.util.List;

class TimingWheel{
    public String test="ok";
    TimingWheel(){

    }
    //new instance
    TimingWheel instace = EnumInstance.INSTANCE.getInstace();
}
public enum EnumInstance {
    INSTANCE;
    private TimingWheel instance;
    EnumInstance(){
        instance= new TimingWheel();
    }
    public TimingWheel getInstace(){
        return instance;
    }
}
class Test{

    public static void main(String[] args) {
        TimingWheel first = new TimingWheel();

        // advanceClock
        String test = first.instace.test;
        List<String> list = new ArrayList<String>();
    }
}
