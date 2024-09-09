package util;


public class MutableInt {
    int value;
    public String encoding;
  public MutableInt(int val, String enc){
    this.value = val;
    this.encoding = enc;
  } // note that we start at 1 since we're counting
  public void increment () { ++this.value;      }
  public int  get () { return this.value; }
  public String  getenc () { return this.encoding; }

}
