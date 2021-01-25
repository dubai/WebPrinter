package printtool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import printtool.entity.PosRequestPO;
import printtool.service.PosPrinterService;

@RestController
@RequestMapping(value = "/print")
public class PosPrintController {

    @Autowired
    private PosPrinterService service;

    @PostMapping(value = "/posprint")
    public void posPrint(@RequestBody PosRequestPO posRequestPO){
        service.PosPrint(posRequestPO);
    }
}
