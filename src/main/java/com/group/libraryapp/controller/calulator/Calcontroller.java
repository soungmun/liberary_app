package com.group.libraryapp.controller.calulator;

import com.group.libraryapp.dto.calaulator.request.CalCulatorReq;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Calcontroller {

  @GetMapping("/add")
      public int get( CalCulatorReq dto){
      System.out.println("테스트");
      return dto.getNumber1()+ dto.getNumber2();
  }
  @PostMapping("/multply")
    public int miAnInt(@RequestBody CalCulatorReq dto ){
      return dto.getNumber1()* dto.getNumber2();
    }
}
