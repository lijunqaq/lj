package suanfa;

/**
 * 功能描述
 *
 * @author Lj
 * @date 2020/5/7
 */
public class Page {
    private int id = -1;
    private int count;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getCount() {
        return count;
    }
    public void inc() {
        count ++;
    }
    public void setCount(int count) {
        this.count = count;
    }
}