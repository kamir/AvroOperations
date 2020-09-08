package util;

import java.nio.charset.*;

import org.apache.commons.io.*;


import com.fasterxml.jackson.dataformat.avro.AvroMapper;
import com.fasterxml.jackson.dataformat.avro.AvroSchema;
import com.fasterxml.jackson.dataformat.avro.schema.AvroSchemaGenerator;

import junit.framework.Assert;

import org.apache.avro.Schema;
import org.apache.avro.SchemaCompatibility;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class SchemaTool {

    public String readSchemaString(String s) throws Exception {
        return readFile( "src/main/avro/" + s );
    }

    public String readFile(String fn) throws Exception {
        File file = new File(fn);
        return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
    }

    public String checkCompatibility(String  newSchemaS, String oldSchemaS) {
        try {

            Schema oldSchema = new Schema.Parser().parse( oldSchemaS );

            Schema newSchema = new Schema.Parser().parse( newSchemaS );

            SchemaCompatibility.SchemaPairCompatibility compatResult =
                    SchemaCompatibility.checkReaderWriterCompatibility(newSchema, oldSchema);

            System.out.println( compatResult );

            //Assert.assertTrue(SchemaCompatibility.schemaNameEquals(newSchema, oldSchema));
            //Assert.assertNotNull(compatResult);
            //Assert.assertEquals(SchemaCompatibility.SchemaCompatibilityType.COMPATIBLE, compatResult.getType());

            return compatResult.getType()+"";
        }
        catch(Exception ex) {
            //ex.printStackTrace();
            return "Error";
        }
    }

    public String persistSchema( Class c ) {

        String fn = c.getCanonicalName();
        fn.replace(".","_");
        fn.concat(".avsc");
        "data/out/".concat( fn );

        String schema = null;

        try {

            schema = getSchemaString(c);

            storeSchemaString(fn, schema, true);
        }
        catch (Exception ex){
            ex.printStackTrace();
        };

        return schema;

    };

    public void storeSchemaString(String fn, String schema, boolean overwrite) {
        File f = new File( fn );
        if( f.exists() ) {
            if (overwrite) {
                writeSchemaToFile( f , schema );
            }
            else return;
        }
        else {
            writeSchemaToFile( f , schema );
        }
    }

    public void writeSchemaToFile(File f, String schema) {

        try {
            FileOutputStream fout = new FileOutputStream(f);
            BufferedOutputStream bfout = new BufferedOutputStream( fout );
            bfout.write(schema.getBytes());
            bfout.flush();
            bfout.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public String getSchemaString(Class c) throws Exception {

        System.out.println( "----------------------------------------------------" );
        System.out.println( "Class: " + c.getCanonicalName() );
        System.out.println( "----------------------------------------------------" );

        AvroMapper mapper = new AvroMapper();
        AvroSchemaGenerator gen = new AvroSchemaGenerator();
        mapper.acceptJsonFormatVisitor( c, gen );

        AvroSchema schema = gen.getGeneratedSchema();

        String json = schema.getAvroSchema().toString(true);

        System.out.println( json );
        System.out.println( "----------------------------------------------------\n\n\n" );

        return  json;

    }

}



