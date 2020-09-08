package io.confluent.examples.clients.basicavro;

import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.common.errors.SerializationException;

import java.util.Properties;

public class ProducerExample {

    private static final String TOPIC = "transactions";

    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(final String[] args) {

        final Properties props = new Properties();

        /**

        // read environment properties ...
        envProps = ...

        **/

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.RETRIES_CONFIG, 0);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
        props.put(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");

        /**

        props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, envProps.getProperty("security.protocol"));
        props.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, envProps.getProperty("ssl.truststore.location"));
        props.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, envProps.getProperty("ssl.truststore.password"));
        props.put(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG, envProps.getProperty("ssl.keystore.location"));
        props.put(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG, envProps.getProperty("ssl.keystore.password"));
        props.put(SslConfigs.SSL_KEY_PASSWORD_CONFIG, envProps.getProperty("ssl.key.password"));
        props.put(SslConfigs.SSL_ENDPOINT_IDENTIFICATION_ALGORITHM_CONFIG, envProps.getProperty("ssl.endpoint.identification.algorithm"));

        **/

        try (KafkaProducer<String, Payment> producer = new KafkaProducer<String, Payment>(props)) {

            for (long i = 1000; i < 1012; i++) {
                final String orderId = "id" + Long.toString(i);

                final Payment payment = new Payment(orderId, 1000.00d, "EU" );

                final ProducerRecord<String, Payment> record = new ProducerRecord<String, Payment>(TOPIC, payment.getId().toString(), payment);
                producer.send(record);
                Thread.sleep(1000L);
            }

            producer.flush();
            System.out.printf("Successfully produced 10 messages to a topic called %s%n", TOPIC);

        } catch (final SerializationException e) {
            e.printStackTrace();
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }

    }

}

