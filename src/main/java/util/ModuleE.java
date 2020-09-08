package util;

public class ModuleE {

    public static void main(String[] ARGS) {

        SchemaTool st = new SchemaTool();

        String schema1 = null;
        String schema2 = null;
        String schema3 = null;

        try {
            Class c1 = io.confluent.examples.clients.basicavro.Payment.class;
            schema1 = st.persistSchema(c1);
        }
        catch ( Exception ex ) {
            ex.printStackTrace();
        }
/*
        try {
            Class c2 = io.confluent.examples.clients.basicavro.Payment2a.class;
            schema2 = st.persistSchema(c2);
        }
        catch ( Exception ex ) {
            ex.printStackTrace();
        }

        try {
            Class c3 = io.confluent.examples.clients.basicavro.Payment2b.class;
            schema3 = st.persistSchema(c3);
        }
        catch ( Exception ex ) {
            ex.printStackTrace();
        }*/



        /**
         * Test schema generation with 3 internally defined classes ...
         */
        try {

            String isCompatible1 = st.checkCompatibility( schema1, schema2 );
            System.out.println( "1->1 : " + isCompatible1 );

            String isCompatible2 = st.checkCompatibility( schema2, schema3 );
            System.out.println( "2->3 : " + isCompatible2 );
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * Test schema compatibility for 2 persisted schemas ...
         */
        try {

            String schemaStringP1 = st.readSchemaString( "generated.data" );
            String schemaStringP2 = st.readSchemaString( "mmpojo.avsc"    );

            String isCompatible3 = st.checkCompatibility( schemaStringP1, schemaStringP2 );
            //System.out.println( "P1-P2 : " + isCompatible3 );

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * Test schema compatibility for 2 persisted schemas ...
         */
        try {

            String schemaStringP1 = st.readSchemaString( "generated.data" );
            String schemaStringP2 = st.readSchemaString( "modified.data"    );

            String isCompatible3 = st.checkCompatibility( schemaStringP1, schemaStringP2 );
            //System.out.println( "PG-PM : " + isCompatible3 );

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
