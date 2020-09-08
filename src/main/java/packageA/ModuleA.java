package packageA;

import org.apache.avro.reflect.AvroDefault;
import org.apache.avro.reflect.AvroMeta;
import util.SchemaTool;

public class ModuleA {

    public static void main(String[] ARGS) {

        SchemaTool st = new SchemaTool();

        DataEntityA1 e1 = new DataEntityA1();
        st.persistSchema( e1.getClass() );

        DataEntityA2 e2 = new DataEntityA2();
        st.persistSchema( e2.getClass() );

    }

}

/**
 * Simple types only ...
 */
class DataEntityA1 {

    public Double d1;
    public double d2;

    public Integer i1;
    public Integer i2;

    public Byte[] b1;
    public byte[] b2;

    /** DOCU **/   /** This DOCU comment is ignored  **/
    public String[] s1;

    @AvroMeta(key="doc", value="some document REFERENCE")   /** This ANNOTATION is ignored  ... WHY ??? **/
    @AvroDefault("\"SOME VALUE\"")
    public String s2;

    private int x;

    public int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
}

/**
 * references to other classes ...
 */
class DataEntityA2 {

    // external reference
    public DataEntityA1 data1;

    // recursive reference
    DataEntityA2 data2;

}

