package com.larson.technology.controller;

import com.larson.technology.models.Persona;
import com.larson.technology.services.PersonaService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class PersonaController {

    @Autowired
    private PersonaService service;

    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public ResponseEntity<?> listarPersonas(){
        JSONObject response = new JSONObject();

        try{
            response.put("code", 200);
            response.put("success", true);
            response.put("personas", service.findAll());

            return new ResponseEntity<>(response.toString(), HttpStatus.OK);

        }catch (Exception e){

            response.put("code", 2000);
            response.put("success", false);
            response.put("personas", e.getMessage());

            return new ResponseEntity<>(response.toString(), HttpStatus.OK);

        }
    }

    @RequestMapping(value = "/buscar/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> buscarPersona(@PathVariable Long id){
        JSONObject response = new JSONObject();
        JSONObject perosonaJson = new JSONObject();
        try{
            Optional<Persona> personaOptional = service.findById(id);

            if(personaOptional.isPresent()){

                Persona persona = personaOptional.get();

                perosonaJson.put("nombre", persona.getNombre());
                perosonaJson.put("apellidoPaterno", persona.getApellidoPaterno());
                perosonaJson.put("apellidoMaterno", persona.getApellidoMaterno());
                perosonaJson.put("email", persona.getEmail());

                response.put("code", 201);
                response.put("success", true);
                response.put("persona", perosonaJson);

                return new ResponseEntity<>(response.toString(), HttpStatus.CREATED);
            }else{
                response.put("code", 404);
                response.put("success", false);
                response.put("mensaje", "Persona no encontrada");

                return new ResponseEntity<>(response.toString(), HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            response.put("code", 2000);
            response.put("success", false);
            response.put("personas", e.getMessage());

            return new ResponseEntity<>(response.toString(), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/registrar", method = RequestMethod.POST)
    public ResponseEntity<?> registrarPersona(@RequestBody @Valid Persona persona){
        JSONObject response = new JSONObject();
        try{
            service.createPersona(persona);
            response.put("code", 201);
            response.put("success", true);
            response.put("mensaje", "Persona creada correctamente!");

            return new ResponseEntity<>(response.toString(), HttpStatus.CREATED);

        }catch (Exception e){
            response.put("code", 2000);
            response.put("success", false);
            response.put("personas", e.getMessage());

            return new ResponseEntity<>(response.toString(), HttpStatus.OK);
        }
    }



    @RequestMapping(value = "/actualizar", method = RequestMethod.PUT)
    public ResponseEntity<?> actualizarPersona(@RequestBody @Valid Persona persona){
        JSONObject response = new JSONObject();
        try{
            if(Objects.nonNull(service.findById(persona.getId()))){

                service.editPersona(persona);
                response.put("code", 200);
                response.put("success", true);
                response.put("mensaje", "Persona actualizada correctamente!");

                return new ResponseEntity<>(response.toString(), HttpStatus.OK);
            }else{
                response.put("code", 404);
                response.put("success", false);
                response.put("mensaje", "Persona no encontrada");

                return new ResponseEntity<>(response.toString(), HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            response.put("code", 2000);
            response.put("success", false);
            response.put("personas", e.getMessage());

            return new ResponseEntity<>(response.toString(), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/eliminar", method = RequestMethod.DELETE)
    public ResponseEntity<?> eliminarPersona(@RequestParam Long id){
        JSONObject response = new JSONObject();
        try{
            if(Objects.nonNull(service.findById(id))){

                service.deletePersonaById(id);
                response.put("code", 200);
                response.put("success", true);
                response.put("mensaje", "Persona eliminada correctamente!");

                return new ResponseEntity<>(response.toString(), HttpStatus.OK);
            }else{
                response.put("code", 404);
                response.put("success", false);
                response.put("mensaje", "Persona no encontrada");

                return new ResponseEntity<>(response.toString(), HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            response.put("code", 2000);
            response.put("success", false);
            response.put("personas", e.getMessage());

            return new ResponseEntity<>(response.toString(), HttpStatus.OK);
        }
    }
}
