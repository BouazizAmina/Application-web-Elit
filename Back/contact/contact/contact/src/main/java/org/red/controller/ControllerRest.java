package org.red.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.red.entity.Cotisation;
import org.red.repersitory.ContactERepoersitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Api( description="API pour es opérations CRUD sur les produits.")

@RestController
public class ControllerRest implements Serializable {


    @Autowired
    private ContactERepoersitory    contactERepoersitory;


    @ApiOperation(value = "Récupère un produit grâce à son ID à condition que celui-ci soit en stock!")
    @RequestMapping(value ="/Contact",method = RequestMethod.GET)
    public List<Cotisation> getRestr(){
    return  contactERepoersitory.findAll()
            .stream()
            .distinct()
            .collect(Collectors.toList());

}
    @ApiOperation(value = "Récupère un produit grâce à son ID à condition que celui-ci soit en stock!")
    @RequestMapping(value ="/Contact/{id}",method = RequestMethod.GET)
    public Cotisation getRest(@PathVariable Integer id){
        return  contactERepoersitory.findById(id).orElse(null);
    }



    @RequestMapping(value ="/Contacts",method = RequestMethod.POST)
    public Cotisation cree(@RequestBody Cotisation e ){
    return contactERepoersitory.save(e);
    }


    @RequestMapping(value ="/supeminer/{id}",method = RequestMethod.DELETE)
    public Boolean supp(@PathVariable Integer id){

        contactERepoersitory.deleteById(id);
        return true;
    }

    @RequestMapping(value ="/Update/{id}",method = RequestMethod.PUT)
    public Cotisation update(@PathVariable Integer id,@RequestBody Cotisation e ){
        e.setId(id);
        return contactERepoersitory.save(e);
    }


@RequestMapping(value = "/modifier/{id}",method = RequestMethod.PUT)
    public Cotisation modifier(@PathVariable Integer id , @RequestBody Cotisation cotisation){

        cotisation.setId(id);
     return  contactERepoersitory.save(cotisation);

}


@RequestMapping(value = "/deletecoti/{id}",method = RequestMethod.DELETE)
    public Boolean supprimer(@PathVariable Integer ide){

        try {
            contactERepoersitory.deleteById(ide);
            return true;
        }catch (Exception ex){

            return false;
        }


}




@RequestMapping(value="/create",method = RequestMethod.POST)
public Cotisation crese(@RequestBody Cotisation cotisation){

        return  contactERepoersitory.save(cotisation);

}

    @RequestMapping(value="/creeuneliste/{id}",method = RequestMethod.POST)
    public Cotisation crese(@RequestBody List<Cotisation> listcotisations,@PathVariable Integer id){
    Cotisation re = new Cotisation();

        re = contactERepoersitory.findById(id).get();


 return re;

    }



}
