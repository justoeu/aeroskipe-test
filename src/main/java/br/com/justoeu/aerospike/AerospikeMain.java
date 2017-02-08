package br.com.justoeu.aerospike;

import com.aerospike.client.*;
import com.aerospike.client.policy.ClientPolicy;
import com.aerospike.client.policy.WritePolicy;

/**
 * Created by valmir.justo on 06/02/17.
 */
public class AerospikeMain {

    protected static final int DEFAULT_TIMEOUT_MS = 1000;


    public static void main(String[] args) {

        ClientPolicy policy = new ClientPolicy();
//        policy.user = params.user;
//        policy.password = params.password;
//        policy.tlsPolicy = params.tlsPolicy;

//        params.policy = policy.readPolicyDefault;
//        params.writePolicy = policy.writePolicyDefault;

        Host[] hosts = Host.parseHosts("127.0.0.1", 3000);

        try {
            AerospikeClient client = new AerospikeClient(policy, hosts);

            WritePolicy writePolicy = new WritePolicy();
            policy.timeout = 50;

            for (int i = 1; i < 10000; i++) {

                Key key = new Key("test", "teste", "putgetkey"); //delbinkey //putgetkey

                Bin bin1 = new Bin("bin"+i,i);
                Bin bin2 = new Bin("bin"+i, i * 8);

//                Bin bin1 = Bin.asNull("bin1"); // Set bin value to null to drop bin.
//                Bin bin2 = Bin.asNull("bin2"); // Set bin value to null to drop bin.
//                client.put(writePolicy, key, bin1);

                // Write a record
                client.put(null, key, bin1,bin2);

                // Read a record
//                Record record = client.get(null, key);
//
//                System.out.println("Position i = " + i + " record = " + record.toString());

            }
            client.close();
        } catch (AerospikeException e){
            e.printStackTrace();
        }
    }

}