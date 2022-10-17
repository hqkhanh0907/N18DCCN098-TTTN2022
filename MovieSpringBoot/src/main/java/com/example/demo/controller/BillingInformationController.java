package com.example.demo.controller;

import com.example.demo.dto.BillingInformationDto;
import com.example.demo.model.Key.BillingInformationKey;
import com.example.demo.service.BillingInformationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/bill")
public class BillingInformationController {
    private final BillingInformationService billingInformationService;
    @GetMapping("/getAllBill")
    public ResponseEntity<?> getAllBill(){
        return new ResponseEntity<>(billingInformationService.getAll(), HttpStatus.OK);
    }
    @PutMapping("/changeStatus")
    public ResponseEntity<?> changeBillStatus(@RequestBody BillingInformationDto billingInformationDto) {
        return new ResponseEntity<>(billingInformationService.updateBillInformation(billingInformationDto), HttpStatus.OK);
    }
    @GetMapping("/checkpay/{accountId}/{movieId}")
    public ResponseEntity<?> checkPay(@PathVariable("accountId") Integer accountId, @PathVariable("movieId") Integer movieId){
        return new ResponseEntity<>(billingInformationService.checkPay(accountId, movieId), HttpStatus.OK);
    }
    @GetMapping("/checkExitPay/{accountId}/{movieId}")
    public ResponseEntity<?> checkExitPay(@PathVariable("accountId") Integer accountId, @PathVariable("movieId") Integer movieId){
        return new ResponseEntity<>(billingInformationService.checkExitPay(accountId, movieId), HttpStatus.OK);
    }
    @GetMapping("/getBill/{accId}/{movieId}")
    public ResponseEntity<?> getBill(@PathVariable("accId") Integer accId, @PathVariable("movieId") Integer movieId){
        BillingInformationKey billingInformationKey = new BillingInformationKey(accId, movieId);
        return new ResponseEntity<>(billingInformationService.getBill(billingInformationKey),HttpStatus.OK);
    }
}
