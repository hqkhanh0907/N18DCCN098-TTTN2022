package com.example.demo.controller;

import com.example.demo.dto.AccountDto;
import com.example.demo.dto.AccountPage;
import com.example.demo.dto.Password;
import com.example.demo.exception.MailException;
import com.example.demo.exception.UsernameExitException;
import com.example.demo.model.Account;
import com.example.demo.model.utils.PagingHeaders;
import com.example.demo.model.utils.PagingResponse;
import com.example.demo.service.AccountService;
import com.example.demo.service.ImageService;
import com.example.demo.util.AppConstants;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/acc")
public class AccountController {
    private final ImageService imageService;
    private final AccountService accountService;

    @GetMapping("/page")
    public AccountPage getAllUsers(@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo, @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize, @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy, @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
        return accountService.getAllUsersPage(pageNo, pageSize, sortBy, sortDir);
    }

    @PostMapping("/createAcc/{roleId}")
    public ResponseEntity<?> createNewAccount(@RequestBody @Valid Account account, @PathVariable("roleId") int roleId) throws UsernameExitException, MailException {
        return new ResponseEntity<>(accountService.createAccount(account, roleId), HttpStatus.CREATED);
    }
    @PutMapping("/saveImageAccount/{accId}")
    public ResponseEntity<Boolean> saveImageAccount(@RequestParam("image")MultipartFile image, @PathVariable("accId") int accId) {
        return new ResponseEntity<>(accountService.saveImageAcc(image, accId), HttpStatus.OK);
    }

    @GetMapping("/getAccoutByUsername/{username}")
    public ResponseEntity<?> getAccount(@PathVariable String username) {
        return new ResponseEntity<>(accountService.getAccountByUsernameDTO(username), HttpStatus.OK);
    }
    @GetMapping("/getAllAccount")
    public ResponseEntity<?> getAllAccount() {
        return new ResponseEntity<>(accountService.getAllAccounts(), HttpStatus.OK);
    }
    @GetMapping("/getAccById/{accId}")
    public ResponseEntity<?> getAccById(@PathVariable("accId") int id){
        return new ResponseEntity<>(accountService.getAccountById(id), HttpStatus.OK);
    }

    @GetMapping("/getListAccEnabled/{status}")
    public ResponseEntity<?> getAccEnabled(@PathVariable boolean status) {
        return new ResponseEntity<>(accountService.getAccountByEnabled(status), HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<?> editAccountByUsername(@RequestBody AccountDto account) throws UsernameExitException, MailException {

        return new ResponseEntity<>(accountService.editAccountByUsername(account), HttpStatus.OK);
    }

    @DeleteMapping("/deleteAcc/{username}")
    public ResponseEntity<?> deleteAccountByUsername(@PathVariable String username) {
        return new ResponseEntity<>(accountService.deleteAccountByUsername(username), HttpStatus.OK);
    }

    @PostMapping("/checkPassword/{username}")
    public ResponseEntity<?> checkPassword(@RequestBody Password password, @PathVariable String username) {
        Account account = accountService.getAccountByUsername(username);
        return new ResponseEntity<>(accountService.checkPasswordForAccount(account, password.getPassword()), HttpStatus.OK);
    }

    @PostMapping("/changePassword/{username}")
    public ResponseEntity<?> changePassword(@RequestBody Password password, @PathVariable String username) {
        Account account = accountService.getAccountByUsername(username);
        return new ResponseEntity<>(accountService.changePasswordForAccount(account, password.getPassword()), HttpStatus.OK);
    }

    @PostMapping("/forgotPassword/{email}")
    public ResponseEntity<?> forgotPassword(@PathVariable String email) {
        Account account = accountService.getAccountByEmail(email);
        return new ResponseEntity<>(accountService.forgotPassword(account), HttpStatus.OK);
    }

    public HttpHeaders returnHttpHeaders(PagingResponse response) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(PagingHeaders.COUNT.getName(), String.valueOf(response.getCount()));
        headers.set(PagingHeaders.PAGE_SIZE.getName(), String.valueOf(response.getPageSize()));
        headers.set(PagingHeaders.PAGE_OFFSET.getName(), String.valueOf(response.getPageOffset()));
        headers.set(PagingHeaders.PAGE_NUMBER.getName(), String.valueOf(response.getPageNumber()));
        headers.set(PagingHeaders.PAGE_TOTAL.getName(), String.valueOf(response.getPageTotal()));
        return headers;
    }
}
