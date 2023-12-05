package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.services.EmpleadoServiceImplBD;
@Controller
public class EmpleadoController {
  
  @Autowired
  private EmpleadoServiceImplBD empleadoService;
  @GetMapping("/")
  public String showList(@RequestParam(required = false) Integer pag, Model model) {
    int ultPag = empleadoService.getTotalPaginas() - 1;
    if (pag == null || pag < 0 || pag > ultPag) pag = 0;
    Integer pagSig = ultPag > pag ? pag + 1 : ultPag;
    Integer pagAnt = pag > 0 ? pag - 1 : 0;


    // if(ultPag > pag) pagSig = pag + 1; else pagSig = ultPag;
    model.addAttribute("listaEmpleados", empleadoService.getEmpleadosPaginados(pag));
    model.addAttribute("pagSiguiente", pagSig);
    model.addAttribute("pagAnterior", pagAnt);
    model.addAttribute("pagFinal", ultPag);
    return "listView";
  }  
}
