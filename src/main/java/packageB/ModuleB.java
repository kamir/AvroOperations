package packageB;

import util.SchemaTool;


public class ModuleB {

    public static void main(String[] ARGS) {

        SchemaTool st = new SchemaTool();

        Class c3 = DataEntityB1.class;

        st.persistSchema( c3 );

    }

}

