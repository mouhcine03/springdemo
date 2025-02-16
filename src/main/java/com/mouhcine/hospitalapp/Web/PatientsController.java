package com.mouhcine.hospitalapp.Web;

import com.mouhcine.hospitalapp.Entity.Patient;
import com.mouhcine.hospitalapp.Repository.PatientRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PatientsController {
    @Autowired
    private PatientRepository patientRepository;


    //avant pagination

    /*@GetMapping("/index")
    public String index(Model model){
        List<Patient> patients = patientRepository.findAll();
        model.addAttribute("patients", patients);
        return "patients";
    }*/


    //gestion de pagination
    @GetMapping("/index")
    public String index(Model model ,
                        @RequestParam(name = "page",defaultValue = "0") int page,
                        @RequestParam(name = "size", defaultValue = "6") int size,
                        @RequestParam(name = "keyword", defaultValue = "") String keyword){
        //Page<Patient> pagepatients = patientRepository.findAll(PageRequest.of(page, size));
        Page<Patient> pagepatients =patientRepository.findByNomContainsIgnoreCase(keyword,PageRequest.of(page,size));
        model.addAttribute("patients", pagepatients.getContent());
        model.addAttribute("pages",new int[pagepatients.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "patients";
    }
    @GetMapping("/deletePatient")
    public String deletePatient(@RequestParam("id") Long idPatient,int page,String keyword){
        patientRepository.deleteById(idPatient);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }
    @GetMapping("/formPatients")
    public String formPatients(Model model){
        model.addAttribute("patient",new Patient());
        return "formPatients";
    }
    @PostMapping(path = "/save")
    public String savePatient(Model model , @ModelAttribute @Valid Patient patient, BindingResult bindingResult,
                              @RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "") String keyword){
        if(bindingResult.hasErrors()){ return "formPatients"; }
        patientRepository.save(patient);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/editPatient")
    public String editPatient(Model model, Long id ,String keyword,int page ){
        Patient patient = patientRepository.findById(id).orElse(null);
        if(patient == null) throw new RuntimeException("patient not found");
        model.addAttribute("patient",patient);
        model.addAttribute("keyword",keyword);
        model.addAttribute("page",page);
        return "editPatient";
    }



}
