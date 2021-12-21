package org.red;

import org.red.entity.Cotisation;
import org.red.entity.Reconposations;
import org.red.repersitory.ContactERepoersitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@EnableSwagger2
@SpringBootApplication
public class ContactApplication implements CommandLineRunner {



    @Autowired
    private ContactERepoersitory contactERepoersitory;


    private Cotisation re = new Cotisation();
    public static void main(String[] args) { SpringApplication.run(ContactApplication.class, args);}





    @Override
    public void run(String... args) throws Exception {


        re.setTaux("12454");
        List<String> rer = new ArrayList<>();
        contactERepoersitory.save(re);

        re = new Cotisation();
        List<Reconposations> listre = new ArrayList<>();
        Reconposations   recon = new Reconposations();
        Reconposations   reco = new Reconposations();



    }







}