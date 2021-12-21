package org.red.controller;

import org.red.entity.Cotisation;
import org.red.entity.Panne;
import org.red.entity.Recepteur;
import org.red.entity.Reconposations;
import org.red.load.request.LoginRequest;
import org.red.load.request.SignupRequest;
import org.red.load.response.JwtResponse;
import org.red.load.response.MessageResponse;
import org.red.repersitory.ContactERepoersitory;
import org.red.repersitory.PanneRepository;
import org.red.repersitory.RecompositionRepository;
import org.red.repersitory.UserRepository;
import org.red.security.jwt.JwtUtils;
import org.red.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.inject.Named;
import java.io.Serializable;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/auth")
@RestController
@Named
public class ControllerRest  implements Serializable {
    @Autowired
    private ContactERepoersitory    contactERepoersitory;

    @Autowired
    private RecompositionRepository recompositionRepository;

    @Autowired
    private PanneRepository pannerepository;

    @RequestMapping(value ="/ListCotisation",method = RequestMethod.GET)
    public List<Cotisation> getCotis(){
        System.out.println(contactERepoersitory.findAll().stream().distinct().collect(Collectors.toList()));
    return  contactERepoersitory.findAll().stream().distinct().collect(Collectors.toList());
    }

    @RequestMapping(value ="/ListRecomposation",method = RequestMethod.GET)
    public List<Reconposations> getRecom(){
        return  recompositionRepository.findAll().stream().distinct().collect(Collectors.toList());
    }

    @RequestMapping(value ="/supprimer/{id}",method = RequestMethod.DELETE)
    public Boolean supp(@PathVariable Integer id){
        contactERepoersitory.deleteById(id);
        return true;
    }

    @RequestMapping(value ="/AddCotisation",method = RequestMethod.POST)
    public Cotisation creeCotisation(@RequestBody Cotisation e ){
        return contactERepoersitory.save(e);
    }

    @RequestMapping(value ="/AddPanne",method = RequestMethod.POST)
    public Panne creePanne(@RequestBody Panne e ){
        System.out.println(pannerepository.save(e));
        return pannerepository.save(e);
    }

    @RequestMapping(value ="/AddRecomponse",method = RequestMethod.POST)
    public Reconposations creeRecomponse(@RequestBody Reconposations r ){
        return recompositionRepository.save(r);
    }

    //1
    @RequestMapping(value ="/FindCotisationbyTaux",method = RequestMethod.POST)
    public List<Cotisation> findCotisbyTaux(@RequestBody String taux ){
        return contactERepoersitory.findCotisationByTaux(taux);
    }
    //2
    @RequestMapping(value ="/FindTauxCotisationbyId",method = RequestMethod.POST)
    public String findCotisbyId(@RequestBody Integer id ){
        String taux = contactERepoersitory.findCotisationById(id).get().getTaux();
        return taux;
    }
    //3
    @RequestMapping(value ="/ExistCotisationbyTaux",method = RequestMethod.POST)
    public boolean existCotis(@RequestBody String taux ){
        boolean exist;
        if (contactERepoersitory.findCotisationByTaux(taux).isEmpty()){exist=false;}
        else {
            exist=true;
        }
        return exist;
    }
    //4
//    @RequestMapping(value ="/FindCotisationbyTypere",method = RequestMethod.POST)
//    public List<Reconposations> findCotisationbyTypere(@RequestBody String typere ){
//        return recompositionRepository.findCotisationsByType(typere);
//    }
    //5
    @RequestMapping(value ="/FindRecomponsebyTypere",method = RequestMethod.POST)
    public List<Reconposations> findRecomponsebyTypere(@RequestBody String typere ){
        System.out.println(recompositionRepository.findCotisationsByType(typere));
        return recompositionRepository.findCotisationsByType(typere);
    }
    //6
//    @RequestMapping(value ="/FindRecomponsedeCotisation",method = RequestMethod.POST)
//    public String findRecomponsedeCotisation(@RequestBody Integer id ){
//        return recompositionRepository.findTyperedeCotisation(id).getTypere();
//    }

    //    @RequestMapping(value ="/Contact/id/{id}",method = RequestMethod.GET)
//    public Cotisation getRest(@PathVariable Integer id){
//        return  contactERepoersitory.findById(id).orElse(null);
//    }
//
//    @RequestMapping(value ="/Contact/taux",method = RequestMethod.GET)
//    public List<Cotisation> findCot(@RequestBody String taux){
//        return  contactERepoersitory.findCotisationsByTaux(taux);
//    }
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
//            System.out.println("taken username");
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
//            System.out.println("taken email");
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }
        // Create new user's account
        Recepteur recepteur = new Recepteur(signUpRequest.getUsername(), signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));
        userRepository.save(recepteur);
//        System.out.println("nice");
//        System.out.println("recepteur"+signUpRequest);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;

    @GetMapping(value = "/signin/")
    public ResponseEntity<?> authenticateUser( @RequestParam String username,@RequestParam String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(
                jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail()));
    }
}
