package packageC;

import util.SchemaTool;

public class ModuleC {

    public static void main(String[] ARGS) {

        SchemaTool st = new SchemaTool();

        data.MMPojo e2 = new data.MMPojo();

        st.persistSchema( e2.getClass() );

    }

}

