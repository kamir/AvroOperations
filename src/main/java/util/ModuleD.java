package util;

public class ModuleD {

    public static void main(String[] ARGS) {

        SchemaTool st = new SchemaTool();

        /**
         * Test schema compatibility for 2 persisted schemas ...
         */
        try {

            String schemaStringP1 = st.readSchemaString( "Payment2b.avsc" );
            String schemaStringP2 = st.readSchemaString( "Payment2a.avsc"    );

            String isCompatible3 = st.checkCompatibility( schemaStringP1, schemaStringP2 );
            System.out.println( "P2a-P2b : " + isCompatible3 );

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
